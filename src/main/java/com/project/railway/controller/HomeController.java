package com.project.railway.controller;

import com.project.railway.data.entity.SearchData;
import com.project.railway.data.entity.Seat;
import com.project.railway.data.entity.Station;
import com.project.railway.data.repository.SeatRepository;
import com.project.railway.data.repository.StationRepository;
import com.project.railway.data.repository.TrainRepository;
import io.micrometer.core.instrument.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    StationRepository stationRepository;

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    SeatRepository seatRepository;

    @GetMapping("/")
    public String home(Model model){
        SearchData search = new SearchData(getStations());
        model.addAttribute("searchData", search);
        return  "search";
    }

    @GetMapping("/search")
    public String searchResult(Model model){
        SearchData search = new SearchData(getStations());

        model.addAttribute("searchData", search);
        return "search";
    }

    @GetMapping("/searchresults")
    public String getSearchResultPage(Model model){
        SearchData search = new SearchData(getStations());
        model.addAttribute("searchData", search);
        return "search";
    }

    @GetMapping("/contacts")
    public String getContactsPage(Model model){
        return "contacts";
    }

    @PostMapping("/search")
    public String getSearchResults(Model model){
        return "searchresults";
    }

    public List<Station> getStations(){
        List<Station> stations = stationRepository.findAllStations();
        Collections.sort(stations);
        return stations;
    }
}
