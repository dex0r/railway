package com.project.railway.data.repository;

import com.project.railway.data.entity.Seat;
import com.project.railway.data.entity.Train;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends CrudRepository<Seat, Long> {
    Seat findBySeatNameAndCompartmentTypeAndTrain(String seatName, String compartmentType, Train train);
}
