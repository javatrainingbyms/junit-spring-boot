package com.harsh.helpdesk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harsh.helpdesk.entity.SubCategory;

@Repository
public interface SubCategoryRepo extends JpaRepository<SubCategory, Integer> {

}
