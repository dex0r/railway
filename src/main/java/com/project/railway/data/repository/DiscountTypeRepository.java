package com.project.railway.data.repository;

import com.project.railway.data.entity.DiscountType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface DiscountTypeRepository extends CrudRepository<DiscountType, Long> {
    DiscountType findDiscountTypeById(Long id);

    @Query("SELECT ds from DiscountType ds")
    List<DiscountType> findAllDiscountTypes();
}
