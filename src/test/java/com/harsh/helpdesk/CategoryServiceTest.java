package com.harsh.helpdesk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.harsh.helpdesk.dto.CategoryDTO;
import com.harsh.helpdesk.entity.Category;
import com.harsh.helpdesk.service.CategoryService;

import jakarta.transaction.Transactional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;
	
	@Disabled
	@Test
	public void testFindById() {
		CategoryDTO categoryDTO=categoryService.findById(1);
		assertNotNull(categoryDTO);
		assertEquals("hr", categoryDTO.getCategoryName());
	}
	
	@Disabled
	@Test
	public void testFindAll() {
		List<CategoryDTO> categories=categoryService.findAllCategoryDTO();
		assertNotNull(categories);
		assertEquals(4,categories.size());
	}
	
	@Test
	@Commit
	public void testSaveCategory() {
		Category category=new Category();
		category.setCategoryName("r&d");
		Category categoryObtained=categoryService.saveCategory(category);
		System.out.println(categoryObtained);
		assertEquals(category.getCategoryId(),categoryObtained.getCategoryId());
		assertEquals(category.getCategoryName(), categoryObtained.getCategoryName());
		//assertEquals(category,categoryObtained);
	}
	
}
