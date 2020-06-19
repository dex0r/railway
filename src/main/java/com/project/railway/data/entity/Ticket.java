package com.project.railway.data.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TicketID")
    private Long Id;

    @ManyToOne(fetch= FetchType.LAZY, optional = false)
    @JoinColumn(name = "ClientID", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TrainID")
    private Train train;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SeatID")
    private Seat seat;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Departure_stationID")
    private Station departureStation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Arrival_stationID")
    private Station arrivalStation;

    @Column(name = "Purchase_Date")
    private Date purchaseDate;

    @Column(name = "Arrival_Time")
    private Timestamp arrivalTime;

    @Column(name = "Departure_Time")
    private Timestamp departureTime;

    @Column(name = "Price")
    private double Price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Status_ID")
    private TicketStatus ticketStatus;

    public Ticket(){

    }

    public Seat getSeat() {
        return seat;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Station getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(Station departureStation) {
        this.departureStation = departureStation;
    }

    public Station getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(Station arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public Long getId() {
        return Id;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + Id +
                ", client=" + client +
                ", train=" + train +
                ", seat=" + seat +
                ", departureStation=" + departureStation +
                ", arrivalStation=" + arrivalStation +
                ", purchaseDate=" + purchaseDate +
                ", arrivalTime=" + arrivalTime +
                ", Price=" + Price +
                ", ticketStatus=" + ticketStatus +
                '}';
    }
}
