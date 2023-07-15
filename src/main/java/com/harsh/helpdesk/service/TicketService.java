package com.harsh.helpdesk.service;

import java.util.List;
import java.util.Optional;

import com.harsh.helpdesk.dto.TicketRequest;
import com.harsh.helpdesk.entity.Ticket;

public interface TicketService{
	List<Ticket> findAllTicket();
	Ticket saveTicket(TicketRequest ticketRequest);
	Ticket findById(Integer id);
	Ticket updateTicket(Ticket ticket);
	void deleteTicket(Integer id);
	
	List<Ticket> findTicketByCategoryId(Integer id);
	List<Ticket> findTicketBySubCategoryId(Integer id);

	List<Ticket> findTicketsFilterSort(
			Integer categoryId, String sortBy, String status);
	
	
	
}
