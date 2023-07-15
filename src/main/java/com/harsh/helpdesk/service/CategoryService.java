package com.harsh.helpdesk.service;

import java.util.List;
import java.util.Optional;

import com.harsh.helpdesk.dto.CategoryDTO;
import com.harsh.helpdesk.entity.Category;

public interface CategoryService {
	public List<CategoryDTO> findAllCategoryDTO();
	Category saveCategory(Category category);
	CategoryDTO findById(Integer id);
	Category updateCategory(Integer id, String name);
	void deleteCategory(Integer id);
}