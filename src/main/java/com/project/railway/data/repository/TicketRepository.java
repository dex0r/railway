package com.project.railway.data.repository;

import com.project.railway.data.entity.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
    List<Ticket> findTicketByClientIdAndTicketStatus(Long clientID, TicketStatus ticketStatus);

    List<Ticket> findAllByTrainAndPurchaseDate(Train train, Date purchaseDate);

    @Query("SELECT t from Ticket t WHERE t.id = ?1")
    Ticket findTicketById(Long TicketID);

    @Query("SELECT t.id FROM Ticket t WHERE t.client = ?1 AND t.ticketStatus = ?2 AND t.train = ?3 AND t.purchaseDate = ?4 AND t.departureStation = ?5 AND t.arrivalStation = ?6")
    Long findIdByClientIdAndTicketStatusAndTrainAndPurchaseDate(Client client,
                                                                TicketStatus ticketStatus,
                                                                Train train,
                                                                Date purchaseDate,
                                                                Station departureStation,
                                                                Station arrivalStation);

    @Query("SELECT t FROM Ticket t WHERE t.client = ?1 AND t.train = ?2 AND t.purchaseDate = ?3 AND t.departureStation = ?4 AND t.arrivalStation = ?5 AND t.ticketStatus = ?6")
    Optional<Ticket> findTicketByClientAndTrainAndPurchaseDateAndDepartureStationAndArrivalStationAndTicketStatus(Client client,
                                                                                                                  Train train,
                                                                                                                  Date purchaseDate,
                                                                                                                  Station departureStation,
                                                                                                                  Station arrivalStation,
                                                                                                                  TicketStatus ticketStatus);
}
