package com.project.railway.data.repository;

import com.project.railway.data.entity.Route;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

public interface RouteRepository extends CrudRepository<Route, Long> {
    List<Route> findRouteByDepartureStationId(final Long departureStationID);

    @Query("SELECT r.train FROM Route r WHERE r.departureStation = ?1")
    List<Integer> findTrainByDepartureStation(final Long departureStation);

    @Query(value = "SELECT r.departure_time FROM Route r WHERE r.RouteID=?1", nativeQuery = true)
    Timestamp findDepartureTimeByRouteID(final Long RouteID);

    @Query(value = "SELECT r.arrival_time FROM Route r WHERE r.RouteID=?1", nativeQuery = true)
    Timestamp findArrivalTimeByRouteID(final Long RouteID);

    @Query(value = "SELECT SUM(r.price) FROM Route r WHERE r.RouteID BETWEEN ?1 AND ?2", nativeQuery = true)
    double findPriceByRouteStartAndRouteEnd(final Long RouteStart, final Long RouteEnd);

    List<Route> findRouteByArrivalStationId(final Long arrivalStationID);
    //@Query("SELECT r.RouteID, t.trainid FROM Route r INNER JOIN Train t ON r.trainid = t.trainid LEFT JOIN Station s ON r.Departure_StationID = s.StationID WHERE s.StationName = ?1")
    //int findRouteStart();
}
