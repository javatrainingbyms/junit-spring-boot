package com.harsh.helpdesk.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harsh.helpdesk.repo.CategoryRepo;
import com.harsh.helpdesk.repo.SubCategoryRepo;
import com.harsh.helpdesk.repo.TicketRepo;
import com.harsh.helpdesk.service.CategoryService;
import com.harsh.helpdesk.service.TicketService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import com.harsh.helpdesk.dto.TicketRequest;
import com.harsh.helpdesk.entity.Category;
import com.harsh.helpdesk.entity.SubCategory;
import com.harsh.helpdesk.entity.Ticket;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepo ticketRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private SubCategoryRepo subCategoryRepo;
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<Ticket> findAllTicket() {
		return ticketRepo.findAll();
	}

	@Override
	public Ticket saveTicket(TicketRequest ticketRequest) {
		Ticket ticket = new Ticket();
		Category category = categoryRepo.findById(ticketRequest.getCategoryId())
				.orElseThrow(() -> new IllegalArgumentException("Category not found"));
		SubCategory subCategory = subCategoryRepo.findById(ticketRequest.getSubCategoryId())
				.orElseThrow(() -> new IllegalArgumentException("SubCategory not found"));
		ticket.setCategory(category);
		ticket.setSubCategory(subCategory);
		ticket.setPriority(ticketRequest.getPriority());
		ticket.setEndDate(ticketRequest.getEndDate());
		ticket.setStartDate(ticketRequest.getStartDate());
		ticket.setStatus(ticketRequest.getStatus());
		return ticketRepo.save(ticket);
	}

	@Override
	public Ticket findById(Integer id) {
		Optional<Ticket> ticket = ticketRepo.findById(id);
		if(ticket.isPresent()) {
		return ticket.get();
			}
		return null;
		}

	@Override
	public Ticket updateTicket(Ticket ticket) {
		return ticketRepo.save(ticket);
	}

	@Override
	public void deleteTicket(Integer id) {
		ticketRepo.deleteById(id);
	}

	@Override
	public List<Ticket> findTicketByCategoryId(Integer id) {
		Category category = categoryRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Category not found"));
		return category.getTicket();
	}

	@Override
	public List<Ticket> findTicketBySubCategoryId(Integer id) {
		SubCategory subCategory = subCategoryRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("SubCategory not found"));
		return subCategory.getTicket();
	}
	
	@Override
	public List<Ticket> findTicketsFilterSort(Integer categoryId, String sortBy, String status){
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ticket> query = cb.createQuery(Ticket.class);
        Root<Ticket> root = query.from(Ticket.class);
        List<Predicate> predicates = new ArrayList<>();

        if (categoryId != null) {
        	Category category = categoryRepo.findById(categoryId)
        			.orElseThrow(()-> new IllegalArgumentException("Category Not Found"));
            predicates.add(cb.equal(root.get("category"), category));
            }
        
        if (status != null) {
            predicates.add(cb.equal(root.get("status"), status));
        } 

        if (!predicates.isEmpty()) {
            query.where(predicates.toArray(new Predicate[0]));
        }
        
        if (sortBy != null) {
            if (sortBy.equalsIgnoreCase("priority")) {
                query.orderBy(cb.desc(root.get("priority")));
            } else {
            	query.orderBy(cb.asc(root.get("endDate")));
            }
        } else {
        	query.orderBy(cb.asc(root.get("endDate")));
        }
	
        return entityManager.createQuery(query).getResultList();
    }

	
}
