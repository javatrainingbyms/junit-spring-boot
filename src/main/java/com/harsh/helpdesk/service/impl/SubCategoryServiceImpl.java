package com.harsh.helpdesk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harsh.helpdesk.entity.Category;
import com.harsh.helpdesk.entity.SubCategory;
import com.harsh.helpdesk.repo.CategoryRepo;
import com.harsh.helpdesk.repo.SubCategoryRepo;
import com.harsh.helpdesk.service.SubCategoryService;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

	@Autowired
	private SubCategoryRepo subCategoryRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public List<SubCategory> findAllSubCategory() {
		return subCategoryRepo.findAll();
	}

	@Override
	public SubCategory saveSubCategory(Integer categoryId,String name) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new IllegalArgumentException("Category not found"));
		SubCategory subCategory = new SubCategory();
		subCategory.setCategory(category);
		subCategory.setSubCategoryName(name);
		return subCategoryRepo.save(subCategory);
	}

	@Override
	public SubCategory findById(Integer id) {
		SubCategory subCategory = subCategoryRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Not found"));
		return subCategory;
	}

	@Override
	public SubCategory updateSubCategory(Integer id,String name) {
		SubCategory subCategory = subCategoryRepo.findById(id).get();
		subCategory.setSubCategoryName(name);
		return subCategoryRepo.save(subCategory);
	}

	@Override
	public void deleteSubCategory(Integer id) {
		Category category = subCategoryRepo.findById(id).get().getCategory();
		category.getSubcategory().removeIf(sc -> sc.getSubCategoryId()==id);
		categoryRepo.save(category);
	}
	
	@Override
	public boolean checkDelete(Integer id) {
		int size = subCategoryRepo.findById(id).get().getCategory().getSubcategory().size();
		if(size!=1) {
			return true;
		} return false;
	}

	@Override
	public List<SubCategory> findSubCategoryByCategoryId(Integer id) {
		Category category = categoryRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Category not found"));
		return category.getSubcategory();
	}



}
