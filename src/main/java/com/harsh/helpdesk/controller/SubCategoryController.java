package com.harsh.helpdesk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.helpdesk.entity.SubCategory;
import com.harsh.helpdesk.service.SubCategoryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/subcategory")
public class SubCategoryController {
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@GetMapping
	public List<SubCategory> getAllSubCategory() {
		return subCategoryService.findAllSubCategory();
	}

    
    @GetMapping("/{id}")
    public ResponseEntity<SubCategory> getbyId(@PathVariable Integer id) {
    	SubCategory subCategory = subCategoryService.findById(id);
    	return ResponseEntity.ok(subCategory);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubCategory(@PathVariable Integer id) {
    	if(subCategoryService.checkDelete(id)) {
    	subCategoryService.deleteSubCategory(id);
    	return ResponseEntity.ok("Succesfully Deleted");
    	} return ResponseEntity.ok("Cannot delete only Subcategory");
    }
    
    @PostMapping
    public SubCategory saveSubCategory(
    		@RequestParam("name") String name,
    		@RequestParam("categoryId") Integer categoryId) {
    	return subCategoryService.saveSubCategory(categoryId,name);
    }
  
    
    @GetMapping("/category/{id}")
    public List<SubCategory> filterSubCategoryByCategory( 
    		@PathVariable Integer id ) {
        return subCategoryService.findSubCategoryByCategoryId(id);
    }
    
    @PutMapping("/{id}")
    public void updateSubCategory(@PathVariable Integer id,
    		@RequestParam("name") String name) {
    	subCategoryService.updateSubCategory(id, name);
    }
}
