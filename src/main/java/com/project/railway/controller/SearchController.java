package com.project.railway.controller;

import com.project.railway.data.entity.RouteList;
import com.project.railway.data.entity.SearchData;
import com.project.railway.data.entity.Station;
import com.project.railway.data.repository.RouteRepository;
import com.project.railway.data.repository.StationRepository;
import com.project.railway.data.repository.TrainRepository;
import com.project.railway.service.RouteService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.*;

@Controller
public class SearchController implements Serializable {

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    StationRepository stationRepository;

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    RouteService routeService;

    public boolean areAnyResults = true;

    @PostMapping("/searchresults")
    public String findRoutes(Model model, @ModelAttribute(name = "departureStation") String departureStation,
                             @ModelAttribute(name = "arrivalStation") String arrivalStation,
                             @ModelAttribute(name = "departureDate") final String departureDate) {

        SearchData searchData = new SearchData(stationRepository.findAllStations());

        SearchData fromSearchPage = new SearchData(stationRepository.findStationByLatinStationName(departureStation),
                stationRepository.findStationByLatinStationName(arrivalStation),
                departureDate);

        List<RouteList> routes = routeService.findRoutesBetweenStations(departureStation, arrivalStation, departureDate);
        System.out.println(routes.toString());
        if(routes.size()==0){
            areAnyResults = false;
        }else{
            areAnyResults = true;
        }

        model.addAttribute("results", areAnyResults);
        model.addAttribute("routes", routes);
        model.addAttribute("searchData", searchData);
        model.addAttribute("getFromSearch", fromSearchPage);

        return "searchresults";
    }
}
