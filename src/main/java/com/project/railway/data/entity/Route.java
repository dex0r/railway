package com.project.railway.data.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RouteID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TrainID")
    private Train train;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Departure_stationID")
    private Station departureStation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Arrival_stationID")
    private Station arrivalStation;

    @Column(name = "Departure_Time")
    private Timestamp departureTime;

    @Column(name = "Arrival_Time")
    private Timestamp arrivalTime;

    @Column(name = "Price")
    private Double Price;


    public void setTrain(Train train) {
        this.train = train;
    }


    public Long getId() {
        return id;
    }

    public Train getTrain() {
        return train;
    }

    public Station getDepartureStation() {
        return departureStation;
    }

    public Station getArrivalStation() {
        return arrivalStation;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public Double getPrice() {
        return Price;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", train=" + train +
                ", departureStation=" + departureStation +
                ", arrivalStation=" + arrivalStation +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", Price=" + Price +
                '}';
    }
}
