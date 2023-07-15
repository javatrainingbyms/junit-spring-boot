package com.harsh.helpdesk.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harsh.helpdesk.dto.CategoryDTO;
import com.harsh.helpdesk.entity.Category;
import com.harsh.helpdesk.entity.SubCategory;
import com.harsh.helpdesk.entity.Ticket;
import com.harsh.helpdesk.repo.CategoryRepo;
import com.harsh.helpdesk.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;



	@Override
	public Category saveCategory(Category category) {
		return categoryRepo.save(category);
	}


	@Override
	public Category updateCategory(Integer id, String name) {
		Category category = categoryRepo.findById(id).get();
		category.setCategoryName(name);
		return categoryRepo.save(category);
	}


	@Override
	public void deleteCategory(Integer id) {
		categoryRepo.deleteById(id);
		
	}


	@Override
	public List<CategoryDTO> findAllCategoryDTO() {
		List<Category> categories = categoryRepo.findAll();
		List<CategoryDTO> categoryDTOs = new ArrayList<>();
		for (Category category : categories) {
            categoryDTOs.add(new CategoryDTO(
            		category.getCategoryId(), 
            		category.getCategoryName(), 
            		category.getSubcategory()));
        }
        return categoryDTOs;
    }


	@Override
	public CategoryDTO findById(Integer id) {
		Optional<Category> category = categoryRepo.findById(id);
		if(category.isPresent()) {
			Category foundCategory = category.get();
			CategoryDTO categoryDTO = new CategoryDTO(
					foundCategory.getCategoryId(),
					foundCategory.getCategoryName(), 
					foundCategory.getSubcategory());
		return categoryDTO ;}
		return null;
	}



}
