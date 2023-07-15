package com.harsh.helpdesk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.harsh.helpdesk.entity.SubCategory;

@Service
public interface SubCategoryService {
	List<SubCategory> findAllSubCategory();
	SubCategory saveSubCategory(Integer categoryId,String name);
	SubCategory findById(Integer id);
	SubCategory updateSubCategory(Integer id, String name);
	boolean checkDelete(Integer id);
	void deleteSubCategory(Integer id);
	
	List<SubCategory> findSubCategoryByCategoryId(Integer id);
}
