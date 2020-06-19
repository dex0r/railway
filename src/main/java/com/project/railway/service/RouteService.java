package com.project.railway.service;

import com.project.railway.data.entity.RouteList;

import java.util.List;

public interface RouteService {
    List<RouteList> findRoutesBetweenStations(String departureStation, String arrivalStation, String departureDate);

    RouteList findRouteByTrainAndStations(String departureStation, String arrivalStation, String TrainID, String departureDate);
}
