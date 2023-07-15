package com.harsh.helpdesk.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.helpdesk.dto.TicketRequest;
import com.harsh.helpdesk.entity.Category;
import com.harsh.helpdesk.entity.Ticket;
import com.harsh.helpdesk.service.CategoryService;
import com.harsh.helpdesk.service.TicketService;

@CrossOrigin("*")
@RestController
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	@Autowired
	private CategoryService categoryService;
	
	

//    @GetMapping
//    public List<Ticket> getAllTicket() {
//    		return ticketService.findAllTicket();
//    }
    
    @GetMapping("/{id}")
    public List<Ticket> getbyId(@PathVariable Integer id) {
    	Ticket ticket = ticketService.findById(id);
    	List<Ticket> tickets = new ArrayList<>();
    	if(ticket!=null) {
    		tickets.add(ticket);
    	}
    	return tickets;
    }
    
    @PostMapping
    public Ticket saveTicket(
    		@RequestBody TicketRequest ticketRequest) {
    	return ticketService.saveTicket(ticketRequest);
    }
  
    
    @GetMapping("/category/{id}")
    public List<Ticket> filterTicketsByCategory( 
    		@PathVariable Integer id ) {
        return ticketService.findTicketByCategoryId(id);
    }
    
    @GetMapping
    public ResponseEntity<List<Ticket>> getTickets(
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String status
    ) {
        List<Ticket> tickets = ticketService.findTicketsFilterSort(categoryId, sortBy, status);
        return ResponseEntity.ok(tickets);
    }
    
    @PutMapping("/{id}")
    public Ticket updateStatus(
    		@PathVariable Integer id,
    		@RequestBody String updatedStatus) {
    	Ticket ticket = ticketService.findById(id);
    	ticket.setStatus(updatedStatus);
    	return ticketService.updateTicket(ticket);
    }
    
    
}
