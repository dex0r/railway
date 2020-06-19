package com.project.railway.data.entity;

import java.sql.Timestamp;

public class RouteList {
    private Train train;
    private Station departureStation;
    private Station arrivalStation;
    private Timestamp departureTime;
    private Timestamp arrivalTime;

    private String departureTimeString;
    private String arrivalTimeString;
    private String departureDate;
    private Double price;
    private String priceString;
    private Double firstClassPrice;
    private String firstClassPriceString;
    private String travelTime;

    private Long routeStart;
    private Long routeEnd;

    public Long getRouteStart() {
        return routeStart;
    }

    public String getDepartureTimeString() {
        return departureTimeString;
    }

    public void setDepartureTimeString(String departureTimeString) {
        this.departureTimeString = departureTimeString;
    }

    public String getArrivalTimeString() {
        return arrivalTimeString;
    }

    public void setArrivalTimeString(String arrivalTimeString) {
        this.arrivalTimeString = arrivalTimeString;
    }

    public void setRouteStart(Long routeStart) {
        this.routeStart = routeStart;
    }

    public Long getRouteEnd() {
        return routeEnd;
    }

    public void setRouteEnd(Long routeEnd) {
        this.routeEnd = routeEnd;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
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

    public void setArrivalStation(Station arrrivalStation) {
        this.arrivalStation = arrrivalStation;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }

    public String getPriceString() {
        return priceString;
    }

    public void setPriceString(String priceString) {
        this.priceString = priceString;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public Double getFirstClassPrice() {
        return firstClassPrice;
    }

    public void setFirstClassPrice(Double firstClassPrice) {
        this.firstClassPrice = firstClassPrice;
    }

    public String getFirstClassPriceString() {
        return firstClassPriceString;
    }

    public void setFirstClassPriceString(String firstClassPriceString) {
        this.firstClassPriceString = firstClassPriceString;
    }

    @Override
    public String toString() {
        return "RouteList{" +
                "train=" + train +
                ", departureStation=" + departureStation +
                ", arrivalStation=" + arrivalStation +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", departureTimeString='" + departureTimeString + '\'' +
                ", arrivalTimeString='" + arrivalTimeString + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", price=" + price +
                ", priceString='" + priceString + '\'' +
                ", firstClassPrice=" + firstClassPrice +
                ", firstClassPriceString='" + firstClassPriceString + '\'' +
                ", travelTime='" + travelTime + '\'' +
                ", routeStart=" + routeStart +
                ", routeEnd=" + routeEnd +
                '}';
    }
}
