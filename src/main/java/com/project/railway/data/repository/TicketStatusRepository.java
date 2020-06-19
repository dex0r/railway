package com.project.railway.data.repository;

import com.project.railway.data.entity.TicketStatus;
import org.springframework.data.repository.CrudRepository;

public interface TicketStatusRepository extends CrudRepository<TicketStatus, Long> {
    TicketStatus findTicketStatusByStatus(String status);
}
