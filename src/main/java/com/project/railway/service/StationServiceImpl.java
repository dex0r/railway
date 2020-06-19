package com.project.railway.service;

import com.project.railway.data.entity.Station;
import com.project.railway.data.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StationServiceImpl implements StationService {

    @Autowired
    StationRepository stationRepository;

    List<Station> allStations = stationRepository.findAllStations();
}
