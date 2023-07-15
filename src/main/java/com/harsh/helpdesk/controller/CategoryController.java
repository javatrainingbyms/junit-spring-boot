package com.harsh.helpdesk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.harsh.helpdesk.dto.CategoryDTO;
import com.harsh.helpdesk.entity.Category;
import com.harsh.helpdesk.service.CategoryService;

@RestController
@CrossOrigin("*")
@RequestMapping("category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/{id}")
	public CategoryDTO getCategoryById(@PathVariable Integer id) {
		CategoryDTO categoryDTO=categoryService.findById(id);
		return categoryDTO;
	}
	@PostMapping(value="")
	public Category saveCategory(@RequestBody Category category) {
        //Category category = new Category();
        //category.setCategoryName(name);
        return categoryService.saveCategory(category);
    }
	@GetMapping(value="")
    public List<CategoryDTO> getAllCategory() {
    		return this.categoryService.findAllCategoryDTO();
    }
	
	@DeleteMapping("/{id}")
	public void deleteCategory(@PathVariable Integer id) {
		categoryService.deleteCategory(id);
	}
	
	@PutMapping("/{id}")
	public Category updateCategory(@PathVariable Integer id,
			@RequestParam("name") String name) {
		Category category=categoryService.updateCategory(id, name);
		System.out.println(category);
		return category;
	}
	
}
