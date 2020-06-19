package com.project.railway.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.project.railway.data.entity.*;
import com.project.railway.data.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    TicketStatusRepository ticketStatusRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    Environment environment;

    @Autowired
    StationRepository stationRepository;

    public List<Seat> findSeatIdByTrainIdAndPurchaseDate(String TrainID, String purchaseDate){
        Train train = trainRepository.findTrainById(Long.parseLong(TrainID));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        List<Seat> reservedSeats = new ArrayList<Seat>();

        try{
            List<Ticket> allTickets = ticketRepository.findAllByTrainAndPurchaseDate(train, sdf.parse(purchaseDate));
            for(int i=0;i<allTickets.size();i++){
                System.out.println(allTickets.get(i).getSeat().getId());
                if(allTickets.get(i).getTicketStatus().getStatus().equals("unverified") || allTickets.get(i).getTicketStatus().getStatus().equals("validated")){
                    reservedSeats.add(allTickets.get(i).getSeat());
                }
            }

        }catch(ParseException ex){
            System.out.println("ParseException");
        }

        System.out.println(reservedSeats.size());

        return reservedSeats;
    }

    public List<Seat> findReservedSeatsForCompartment(String TrainID, String purchaseDate, String compartmentType){
        List<Seat> reservedSeats = findSeatIdByTrainIdAndPurchaseDate(TrainID, purchaseDate);

        List<Seat> reservedSeatsForThisCompartment = new ArrayList<Seat>();

        for(int i=0;i<reservedSeats.size();i++){
            if(reservedSeats.get(i).getCompartmentType().equals(compartmentType)){
                reservedSeatsForThisCompartment.add(reservedSeats.get(i));
            }
        }

        return reservedSeatsForThisCompartment;
    }

    public void reserveTicket(BookingData bookingData){
        if(bookingData.getClient().getId() == null){
            Optional<Client> foundClient = clientRepository.findByEmailAndFirstNameAndLastNameAndPhoneNumber(bookingData.getClient().getEmail(),
                                                                                                                bookingData.getClient().getFirstName(),
                                                                                                                bookingData.getClient().getLastName(),
                                                                                                                bookingData.getClient().getPhoneNumber());
            if(!foundClient.isPresent()){
                clientRepository.save(bookingData.getClient());
            }
        }

        Ticket ticket = new Ticket();
        ticket.setClient(clientRepository.findByEmailAndFirstNameAndLastNameAndPhoneNumber(bookingData.getClient().getEmail(),
                bookingData.getClient().getFirstName(),
                bookingData.getClient().getLastName(),
                bookingData.getClient().getPhoneNumber()).get());
        ticket.setArrivalTime(bookingData.getRoute().getArrivalTime());
        ticket.setDepartureTime(bookingData.getRoute().getDepartureTime());
        try{
            SimpleDateFormat newDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date myDate = newDateFormat.parse(bookingData.getRoute().getDepartureDate());
            newDateFormat.applyPattern("yyyy-MM-dd");
            String departureDateFormatted = newDateFormat.format(myDate);
            ticket.setPurchaseDate(new SimpleDateFormat("yyyy-MM-dd").parse(departureDateFormatted));

        }catch(ParseException ex){
            System.out.println("ParseException");
        }

        ticket.setPrice(bookingData.getPrice());
        ticket.setDepartureStation(bookingData.getRoute().getDepartureStation());
        ticket.setArrivalStation(bookingData.getRoute().getArrivalStation());
        ticket.setSeat(seatRepository.findBySeatNameAndCompartmentTypeAndTrain(bookingData.getSpaceName(), bookingData.getCompartmentType(), trainRepository.findTrainById(bookingData.getRoute().getTrain().getId())));
        ticket.setTrain(bookingData.getRoute().getTrain());
        ticket.setTicketStatus(ticketStatusRepository.findTicketStatusByStatus("unverified"));
        ticketRepository.save(ticket);

        Long ticketID = ticketRepository.findIdByClientIdAndTicketStatusAndTrainAndPurchaseDate(bookingData.getClient(),
                ticketStatusRepository.findTicketStatusByStatus("unverified"),
                bookingData.getRoute().getTrain(),
                ticket.getPurchaseDate(),
                stationRepository.findStationByLatinStationName(ticket.getDepartureStation().getLatinStationName()),
                stationRepository.findStationByLatinStationName(ticket.getArrivalStation().getLatinStationName()));
        System.out.println(ticketID);
        try{
            generateQRCode("http://localhost:8080/ticket/confirmation?id="+ticketID, 100, 100,"./src/main/resources/static/qr/" + bookingData.getClient().getEmail()+bookingData.getRoute().getTrain().getId()+bookingData.getRoute().getDepartureDate() + ".png");
        }catch(IOException ex){
            System.out.println(ex);
        }
        catch(WriterException ex){
            System.out.println(ex);
        }

        sendTicketEmail(bookingData.getClient().getEmail(), bookingData);
    }

    public List<Ticket> findTicketByClientAndTicketStatus(Long clientID, String ticketStatus){

        List<Ticket> ticketsFromRepository = ticketRepository.findTicketByClientIdAndTicketStatus(clientID, ticketStatusRepository.findTicketStatusByStatus(ticketStatus));

        return ticketsFromRepository;
    }

    public static void generateQRCode(String text, int width, int height, String filePath)
            throws WriterException, IOException{
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

    public void sendTicketEmail(String userMail, BookingData bookingData){
        Locale locale = LocaleContextHolder.getLocale();

        try{
            JavaMailSenderImpl sender  = new JavaMailSenderImpl();
            sender.setHost(environment.getProperty("spring.mail.host"));
            MimeMessage mimeMessage = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            helper.setFrom(environment.getProperty("spring.mail.username"));
            helper.setTo(userMail);

            switch(locale.toString()) {
                case "bg":
                    helper.setSubject("БДЖ Резервация онлайн - вашият билет!");
                    helper.setText("<html><body><h5>Благодарим ви за резервацията. В този e-mail ще откриете информация за вашият билет.</h5>" +
                            "<h5>Представете кода на кондуктора за валидиране.</h5>" +
                            "<br><p>Данни за притежателя:</p>" +
                            "<p> Име на клиента:" + bookingData.getClient().getFirstName() + " " + bookingData.getClient().getLastName() + "</p>" +
                            "<br><p>Данни за пътуването:</p>" +
                            "<p> Дата на пътуване: " + bookingData.getRoute().getDepartureDate() + " г.</p>" +
                            "<p> Начална гара: " + bookingData.getRoute().getDepartureStation().getStationName() + "</p>" +
                            "<p> Крайна гара: " + bookingData.getRoute().getArrivalStation().getStationName() + "</p>" +
                            "<p> Влак №: " + bookingData.getRoute().getTrain().getId() + "</p>" +
                            "<p> Час на тръгване: " + bookingData.getRoute().getDepartureTimeString() + " ч.</p>" +
                            "<p> Час на пристигане: " + bookingData.getRoute().getArrivalTimeString() + " ч.</p>" +
                            "<p> Продължителност на пътуване: " + bookingData.getRoute().getTravelTime() + " мин.</p>" +
                            "<h5>Приятно пътуване!</h5><img src='cid:QRImage'>" +
                            "</body>" +
                            "</html>", true);
                    break;
                case "en":
                    helper.setSubject("BDZ Reservation online - your ticket!");
                    helper.setText("<html><body><h5>Thank you for your reservation in BDZ Reservation online. In this email you will find information about your ticket.</h5>" +
                            "<h5>Show the code to conductor for validation.</h5>" +
                            "<br><p>Client data:</p>" +
                            "<p> Client name:" + bookingData.getClient().getFirstName() + " " + bookingData.getClient().getLastName() + "</p>" +
                            "<br><p>Trip data:</p>" +
                            "<p> Travel date: " + bookingData.getRoute().getDepartureDate() + "</p>" +
                            "<p> Departure station: " + bookingData.getRoute().getDepartureStation().getStationName() + "</p>" +
                            "<p> Arrival station: " + bookingData.getRoute().getArrivalStation().getStationName() + "</p>" +
                            "<p> Train number: " + bookingData.getRoute().getTrain().getId() + "</p>" +
                            "<p> Departure time: " + bookingData.getRoute().getDepartureTimeString() + "</p>" +
                            "<p> Arrival time: " + bookingData.getRoute().getArrivalTimeString() + "</p>" +
                            "<p> Travel time: " + bookingData.getRoute().getTravelTime() + "</p>" +
                            "<h5>Have a nice trip!</h5><img src='cid:QRImage'>" +
                            "</body>" +
                            "</html>", true);
                    break;

            }

            FileSystemResource res = new FileSystemResource(new File("./src/main/resources/static/qr/" + bookingData.getClient().getEmail()+bookingData.getRoute().getTrain().getId()+bookingData.getRoute().getDepartureDate() + ".png"));
            helper.addInline("QRImage", res);

            emailService.sendHTMLMessage(mimeMessage);
        }catch(MessagingException ex){
            System.out.print(ex);
        }
    }

    public void validateTicket(Long ticketID){
        Ticket ticket = ticketRepository.findTicketById(ticketID);

        ticket.setTicketStatus(ticketStatusRepository.findTicketStatusByStatus("validated"));

        ticketRepository.save(ticket);
    }

    public boolean findTicketForThisClientAndTrainAndPurchaseDateAndStations(Long clientID,
                                                                                      Long trainID,
                                                                                      String purchaseDate,
                                                                                      String departureStation,
                                                                                      String arrivalStation,
                                                                                      String ticketStatus){


        try{
            SimpleDateFormat newDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date myDate = newDateFormat.parse(purchaseDate);
            newDateFormat.applyPattern("yyyy-MM-dd");
            String departureDateFormatted = newDateFormat.format(myDate);
            Date purchaseDateFormatted = new SimpleDateFormat("yyyy-MM-dd").parse(departureDateFormatted);

            Optional<Ticket> foundTicket =  ticketRepository.findTicketByClientAndTrainAndPurchaseDateAndDepartureStationAndArrivalStationAndTicketStatus(clientRepository.findById(clientID).get(),
                    trainRepository.findTrainById(trainID),
                    purchaseDateFormatted,
                    stationRepository.findStationByLatinStationName(departureStation),
                    stationRepository.findStationByLatinStationName(arrivalStation),
                    ticketStatusRepository.findTicketStatusByStatus(ticketStatus));
            if(foundTicket.isPresent()){
                return true;
            }else{
                return false;
            }

        }catch(ParseException ex){
            System.out.println("ParseException");
        }
        return false;
    }
}
