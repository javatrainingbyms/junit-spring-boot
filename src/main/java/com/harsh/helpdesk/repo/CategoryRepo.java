package com.harsh.helpdesk.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harsh.helpdesk.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
	

}
