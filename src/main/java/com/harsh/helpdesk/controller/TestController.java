package com.harsh.helpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.helpdesk.entity.SubCategory;
import com.harsh.helpdesk.repo.SubCategoryRepo;
import com.harsh.helpdesk.service.SubCategoryService;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	SubCategoryRepo subCategoryRepo;
	
	@Autowired
	SubCategoryService subCategoryService;
	
	@DeleteMapping("/{id}")
	public void deleteSubcategory(@PathVariable Integer id) {
		System.out.println("ID:" +id);
		System.out.println(subCategoryService.checkDelete(id));
		SubCategory sCategory = subCategoryRepo.findById(id).get();
		System.out.println(sCategory.getSubCategoryName());
		subCategoryRepo.deleteById(id);
		System.out.println(sCategory.getSubCategoryName());
	}
}
