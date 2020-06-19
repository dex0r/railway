package com.project.railway.controller;

import com.project.railway.config.SecurityAES;
import com.project.railway.data.entity.*;
import com.project.railway.data.repository.*;
import com.project.railway.service.ClientService;
import com.project.railway.service.RouteService;
import com.project.railway.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@SessionAttributes("bookingData")
public class BookingController {

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    TicketService ticketService;

    @Autowired
    RouteService routeService;

    @Autowired
    StationRepository stationRepository;

    @Autowired
    DiscountTypeRepository discountTypeRepository;

    @Autowired
    ClientService clientService;

    private String seatName;

    @ModelAttribute("bookingData")
    public BookingData getBookingData(){
        return new BookingData();
    }

    @GetMapping("/booking")
    public String getClientDataStep(){
        return "booking/data";
    }

    @GetMapping("/booking/data")
    public String getDataStep(@ModelAttribute(name = "bookingData") BookingData bookingData,
                              @RequestParam(name = "error", required = false) String error,
                              Model model){
        if(error!=null){
            model.addAttribute("errorMessage", error);
        }
        return "booking/data";
    }

    @GetMapping("/booking/space")
    public String getBookingSpacePage(Model model){
        return "/booking/space";
    }

    @GetMapping("/booking/delivery")
    public String getDeliveryPage(Model model){
        return "/booking/delivery";
    }

    @GetMapping("/booking/payment-data")
    public String getPaymentDataPage(Model model){
        return "/booking/payment-data";
    }

    @GetMapping("/booking/payment")
    public String getPaymentPage(Model model){
        return "/booking/payment";
    }

    @GetMapping("/booking/review")
    public String getReviewPage(Model model){
        return "/booking/review";
    }

    @PostMapping("/booking")
    public String postClientDataStep(@ModelAttribute(name = "train") String TrainID,
                                     @ModelAttribute(name = "departureStation") String departureStation,
                                     @ModelAttribute(name = "arrivalStation") String arrivalStation,
                                     @ModelAttribute(name = "departureDate") String departureDate,
                                     @ModelAttribute(name = "traveltime") String travelTime,
                                     @ModelAttribute(name = "Price") String price,
                                     @ModelAttribute(name = "bookingData") BookingData bookingData,
                                     Model model){
        //System.out.println(TrainID + departureDate + departureStation + arrivalStation + travelTime + price);
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails){
            Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            bookingData.setClient(client);
            model.addAttribute("client", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        }

        System.out.println(bookingData.toString());

        bookingData.setRoute(routeService.findRouteByTrainAndStations(departureStation, arrivalStation, TrainID, departureDate));

        List<DiscountType> discountTypes = discountTypeRepository.findAllDiscountTypes();
        model.addAttribute("discount_types", discountTypes);
        return "/booking/data";
    }

    @PostMapping("/booking/space")
    public String postBookingSpaceStep(@ModelAttribute("bookingData") BookingData bookingData,
                                       Model model){
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails){
            model.addAttribute("client", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        }else{

        }
        System.out.println(bookingData.toString());
        return "/booking/space";
    }

    @GetMapping("/booking/setSpace")
    @ResponseBody
    public String setSpaceName(@RequestParam(name = "seatName") String seatName){
        this.seatName = seatName;

        return "success";
    }

    @PostMapping(value = "/booking/delivery")
    public String postDeliveryStep(@ModelAttribute(name = "bookingData") BookingData bookingData,
                                   Model model){
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails){
            model.addAttribute("client", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        }else{
            System.out.println("Anonymous");
        }

        if (bookingData.getCompartmentType().equals("first")) {
            bookingData.setPrice(Double.parseDouble(bookingData.getRoute().getFirstClassPriceString()));
        } else if (bookingData.getCompartmentType().equals("economy")) {
            bookingData.setPrice(Double.parseDouble(bookingData.getRoute().getPriceString()));
        }

        bookingData.setSpaceName(this.seatName);

        System.out.println(bookingData.toString());

        return "/booking/delivery";
    }

    @PostMapping("/booking/payment-data")
    public String postPaymentDataPage(@ModelAttribute(name = "bookingData") BookingData bookingData,
                                      Model model){
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails){
            model.addAttribute("client", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        }

        System.out.println(bookingData.getDeliveryType());
        System.out.println(bookingData.getClient().getAddress());

        return "/booking/payment-data";
    }

    @PostMapping("/booking/review")
    public String postReviewPage(@ModelAttribute(name = "bookingData") BookingData bookingData,
                                 Model model){
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails){
            model.addAttribute("client", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        }

        if(bookingData.getClient().getDiscountType().getId() != 1){
            int discountValue = bookingData.getClient().getDiscountType().getDiscountValue();
            double price = bookingData.getPrice();
            double finalPrice = price - (price*(discountValue/100.0));
            //bookingData.setPrice(Double.parseDouble(round(finalPrice,2).toString()));
            bookingData.setPrice(Math.round(finalPrice*100.0)/100.0);
        }

        Pattern creditCardCVVPattern = Pattern.compile("^[0-9]{3}$");
        Matcher creditCardCVVMatcher = creditCardCVVPattern.matcher(bookingData.getClient().getCreditCardCVV());
        if(creditCardCVVMatcher.matches()){
            bookingData.getClient().setCreditCardCVV(SecurityAES.encrypt(bookingData.getClient().getCreditCardCVV(), bookingData.getClient().getId()+bookingData.getClient().getFirstName()+bookingData.getClient().getLastName()+bookingData.getClient().getEmail()));
        }

        System.out.println(bookingData.toString());
        System.out.println(bookingData.getClient().getFirstName() + bookingData.getClient().getLastName());
        System.out.println(bookingData.getClient().getCreditCardCVV());
        System.out.println(SecurityAES.decrypt(bookingData.getClient().getCreditCardCVV(), bookingData.getClient().getId()+bookingData.getClient().getFirstName()+bookingData.getClient().getLastName()+bookingData.getClient().getEmail()));

        System.out.println(bookingData.toString());
        return "/booking/review";
    }

    @PostMapping("/booking/cancel-purchase")
    public String postCancelPurchasePage(@ModelAttribute(name = "bookingData") BookingData bookingData,
                                         Model model){

        bookingData = new BookingData();

        List<Station> stations = stationRepository.findAllStations();
        Collections.sort(stations);
        SearchData search = new SearchData(stations);


        System.out.println(bookingData.toString());

        String cancelMessage = "cancel";
        model.addAttribute("searchData", search);
        model.addAttribute("cancelMessage", cancelMessage);
        return "search";
    }

    @PostMapping("/booking/finish-purchase")
    public String postFinishPurchasePage(@ModelAttribute(name = "bookingData") BookingData bookingData,
                                         Model model){
        System.out.println(bookingData.toString());

        if(!ticketService.findTicketForThisClientAndTrainAndPurchaseDateAndStations(bookingData.getClient().getId(),
                bookingData.getRoute().getTrain().getId(),
                bookingData.getRoute().getDepartureDate(),
                bookingData.getRoute().getDepartureStation().getLatinStationName(),
                bookingData.getRoute().getArrivalStation().getLatinStationName(),
                "unverified")){
            ticketService.reserveTicket(bookingData);
            return "redirect:/booking/purchase?result=success";
        }else{
            return "redirect:/booking/purchase?result=fail";
        }
    }

    @GetMapping(value = "/booking/return/spaces", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String returnReservedSpacesForDate(@RequestParam(name = "train") String train,
                                              @RequestParam(name = "purchasedate") String purchaseDate,
                                              @RequestParam(name = "compartment") String compartmentType){
        List<Seat> reservedSeats = ticketService.findReservedSeatsForCompartment(train, purchaseDate, compartmentType);

        return reservedSeats.toString();
    }

    @GetMapping("/booking/purchase")
    public String getFinalPurchasePage(@RequestParam(name = "result") String result,
                                       Model model){
        model.addAttribute("result", result);
        return "/booking/purchase";
    }

    public static BigDecimal round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd;
    }
}
