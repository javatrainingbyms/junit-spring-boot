package com.harsh.helpdesk.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.harsh.helpdesk.entity.Ticket;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Integer> {
	
    List<Ticket> findByStatus(String value);
//    List<Ticket> findByCategoryId(Integer id);

}
