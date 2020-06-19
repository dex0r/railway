package com.project.railway.data.repository;

import com.project.railway.data.entity.Train;
import org.springframework.data.repository.CrudRepository;

public interface TrainRepository extends CrudRepository<Train, Long> {
    Train findTrainById(final Long TrainID);
}
