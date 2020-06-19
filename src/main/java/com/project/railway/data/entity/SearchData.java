package com.project.railway.data.entity;

import com.project.railway.data.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SearchData {

    private Station departureStation;
    private Station arrivalStation;
    private String departureDate;

    @Autowired
    StationRepository stationRepository;

    public List<Station> stations;

    public SearchData() {
        super();
    }

    public SearchData(List<Station> stations){
        super();
        this.stations = stations;
    }

    public SearchData(Station departureStation, Station arrivalStation, String departureDate){
        super();
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.departureDate = departureDate;
    }


    public Station getDepartureStation(){
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

    public String getDepartureDate() {
        return this.departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }
}
