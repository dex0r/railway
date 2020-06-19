package com.project.railway.service;

import com.project.railway.data.entity.BookingData;
import com.project.railway.data.entity.Seat;
import com.project.railway.data.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    List<Seat> findSeatIdByTrainIdAndPurchaseDate(String trainID, String purchaseDate);

    List<Seat> findReservedSeatsForCompartment(String trainID, String purchaseDate, String compartmentType);

    void reserveTicket(BookingData bookingData);

    List<Ticket> findTicketByClientAndTicketStatus(Long clientID, String ticketStatus);

    void validateTicket(Long ticketID);

    boolean findTicketForThisClientAndTrainAndPurchaseDateAndStations(Long clientID, Long trainID, String purchaseDate, String departureStation, String arrivalStation, String ticketStatus);
}
