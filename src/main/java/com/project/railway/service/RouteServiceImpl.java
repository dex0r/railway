package com.project.railway.service;

import com.project.railway.data.entity.Route;
import com.project.railway.data.entity.RouteList;
import com.project.railway.data.entity.Train;
import com.project.railway.data.repository.RouteRepository;
import com.project.railway.data.repository.StationRepository;
import com.project.railway.data.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    StationRepository stationRepository;

    @Autowired
    TrainRepository trainRepository;

    @Override
    public List<RouteList> findRoutesBetweenStations(final String departureStation, final String arrivalStation, String departureDate) {

        List<RouteList> routes = new ArrayList<RouteList>();
        RouteList route = new RouteList();
        Train train = new Train();

        List<Route> routeStart = routeRepository.findRouteByDepartureStationId(stationRepository.findStationIdByLatinStationName(departureStation));
        for (int i = 0; i < routeStart.size(); i++) {
            route.setRouteStart(routeStart.get(i).getId());
            train.setId(routeStart.get(i).getTrain().getId());
            route.setTrain(train);
            routes.add(route);
            route = new RouteList();
            train = new Train();
        }

        List<Route> routeEnd = routeRepository.findRouteByArrivalStationId(
                stationRepository.findStationIdByLatinStationName(arrivalStation));
        for (int i = 0; i < routeEnd.size(); i++) {
            for (int j = 0; j < routes.size(); j++) {
                if (routeEnd.get(i).getTrain().getId().equals(routes.get(j).getTrain().getId())) {
                    routes.get(j).setRouteEnd(routeEnd.get(i).getId());
                }
            }
        }

        for(int i=0;i<routes.size();i++){
            if(routes.get(i).getRouteEnd()==null){
                routes.remove(i);
            }
        }

        for (int i = 0; i < routes.size(); i++) {
            System.out.println("TrainID: " + routes.get(i).getTrain().getId() + " Route Start: " + routes.get(i).getRouteStart() + " // Route End:  " + routes.get(i).getRouteEnd());

            if (routes.get(i).getRouteStart() != null && routes.get(i).getRouteEnd() != null) {
                routes.get(i).setTrain(trainRepository.findTrainById(routes.get(i).getTrain().getId()));
                // Set TrainID to the RouteList

                routes.get(i).setDepartureStation(stationRepository.findStationByLatinStationName(departureStation));
                // Set Departure Station to the RouteList

                routes.get(i).setArrivalStation(stationRepository.findStationByLatinStationName(arrivalStation));
                // Set Arrival Station to the RouteList

                Calendar calendar = Calendar.getInstance();
                Timestamp departureTime = routeRepository.findDepartureTimeByRouteID(routes.get(i).getRouteStart());
                calendar.setTime(departureTime);

                routes.get(i).setDepartureTimeString(new SimpleDateFormat("HH:mm").format(calendar.getTime()));
                // Format departure time

                routes.get(i).setDepartureTime(routeRepository.findDepartureTimeByRouteID(routes.get(i).getRouteStart()));

                Timestamp arrivalTime = routeRepository.findArrivalTimeByRouteID(routes.get(i).getRouteEnd());
                calendar.setTime(arrivalTime);

                routes.get(i).setArrivalTimeString(new SimpleDateFormat("HH:mm").format(calendar.getTime()));
                //Format arrival time

                routes.get(i).setArrivalTime(routeRepository.findArrivalTimeByRouteID(routes.get(i).getRouteEnd()));
                // Set arrival time to RouteList

                routes.get(i).setPrice(routeRepository.findPriceByRouteStartAndRouteEnd(routes.get(i).getRouteStart(), routes.get(i).getRouteEnd()));
                // Set price to Route List

                if(i==0){
                    if ((routes.get(i).getRouteEnd() - routes.get(i).getRouteStart()) > 2 && (routes.get(i).getRouteEnd() - routes.get(i).getRouteStart() < 7)) {
                        double price = Math.round((routes.get(i).getPrice() - (routes.get(i).getPrice() * (24.0 / 100.0)))*100.0)/100.0;
                        routes.get(i).setPrice(price);
                    } else if ((routes.get(i).getRouteEnd() - routes.get(i).getRouteStart()) >= 7 && (routes.get(i).getRouteEnd() - routes.get(i).getRouteStart()) < 10) {
                        double price = Math.round((routes.get(i).getPrice() - (routes.get(i).getPrice() * (45.0 / 100.0)))*100.0)/100.0;
                        routes.get(i).setPrice(price);
                    } else if ((routes.get(i).getRouteEnd() - routes.get(i).getRouteStart()) >= 10) {
                        double price = Math.round((routes.get(i).getPrice() - (routes.get(i).getPrice() * (50.0 / 100.0)))*100.0)/100.0;
                        routes.get(i).setPrice(price);
                    } else {
                        double price = routes.get(i).getPrice();
                        routes.get(i).setPrice(price);
                    }
                }if(i>0){
                    routes.get(i).setPrice(routes.get(0).getPrice());
                }
                // Format price

                routes.get(i).setPriceString(round(routes.get(i).getPrice(),2).toString());

                routes.get(i).setFirstClassPrice(Math.round((routes.get(i).getPrice() + (routes.get(i).getPrice()*(25.0/100.0)))*100.0)/100.0);

                routes.get(i).setFirstClassPriceString(round(routes.get(i).getFirstClassPrice(),2).toString());

                long difference = routes.get(i).getArrivalTime().getTime() - routes.get(i).getDepartureTime().getTime();

                long diffMinutes = difference / (60 * 1000) % 60;
                long diffHours = difference / (60 * 60 * 1000) % 24;
                String travelTimeHours;
                String travelTimeMins;

                if (Long.toString(diffHours).equals("0")) {
                    travelTimeHours = "";

                } else {
                    travelTimeHours = Long.toString(diffHours);
                }

                if (Long.toString(diffMinutes).equals("0")) {
                    travelTimeMins = Long.toString(diffMinutes) + "0";
                } else if (diffMinutes == 0 && Long.toString(diffMinutes).length() == 1) {
                    travelTimeMins = Long.toString(diffMinutes) + "0";
                } else if (Long.toString(diffMinutes).length() == 1 && diffHours != 0) {
                    travelTimeMins = Long.toString(diffMinutes) + "0";
                } else {
                    travelTimeMins = Long.toString(diffMinutes);
                }

                if (travelTimeHours.length() == 0) {
                    routes.get(i).setTravelTime(travelTimeMins);
                } else {
                    routes.get(i).setTravelTime(travelTimeHours + ":" + travelTimeMins);
                }
            }
        }

        for(int i=routes.size()-1;i>=0;i--){
            if(routes.get(i).getRouteStart() == null || routes.get(i).getRouteEnd() == null){
                routes.remove(i);
            }
        }

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat simpleDateFormatDate = new SimpleDateFormat("yyyy-MM-dd");

        try{
            for(int i=0;i<routes.size();i++){
                if(simpleDateFormat.parse(routes.get(i).getDepartureTimeString())
                        .before(simpleDateFormat.parse(simpleDateFormat.format(date)))
                && simpleDateFormatDate.parse(departureDate)
                        .equals(simpleDateFormatDate.parse(simpleDateFormatDate.format(date)))){
                    routes.remove(i);
                }
            }
        }catch(ParseException ex){
            System.out.println("ParseException");
        }

        return routes;
    }

    public RouteList findRouteByTrainAndStations(final String departureStation, final String arrivalStation, String TrainID, String departureDate){
        List<RouteList> routes = findRoutesBetweenStations(departureStation, arrivalStation, departureDate);
        System.out.println(routes.size());
        RouteList route = new RouteList();

        for(int i=0;i<routes.size();i++){
            if(routes.get(i).getTrain().getId() == Long.parseLong(TrainID)){
                route = routes.get(i);
            }
        }
        try{
            SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = newDateFormat.parse(departureDate);
            newDateFormat.applyPattern("dd.MM.yyyy");
            String departureDateFormatted = newDateFormat.format(myDate);
            route.setDepartureDate(departureDateFormatted);

        }catch(ParseException ex){
            System.out.println("ParseException");
        }
        return route;
    }

    public static BigDecimal round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd;
    }

}
