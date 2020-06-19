package com.project.railway.data.repository;

import com.project.railway.data.entity.Station;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StationRepository extends CrudRepository<Station, Long> {
    @Query("SELECT s.id FROM Station s WHERE s.latinStationName = ?1")
    Long findStationIdByLatinStationName(final String stationName);

    Station findStationByLatinStationName(final String stationName);

    @Query("SELECT s from Station s")
    List<Station> findAllStations();
}
