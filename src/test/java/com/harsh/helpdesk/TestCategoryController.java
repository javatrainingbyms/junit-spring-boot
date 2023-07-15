package com.harsh.helpdesk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.harsh.helpdesk.dto.CategoryDTO;
import com.harsh.helpdesk.entity.Category;

class TestCategoryController {

	private static RestTemplate restTemplate;
	
	@BeforeAll
	public static void createRestTemplate() {
		restTemplate=new RestTemplate();
	}
	
	@Test
	public void testUpdateCategory() {
		System.out.println("Testing Update...!!");
		String endPoint="http://localhost:8080/category/1?name=human-resource";
		HttpHeaders headers = new HttpHeaders();
		//headers.set("Content-Type", "application/json");
		//HttpEntity requestEntity=new HttpEntity(category, headers);
		ResponseEntity<Category> response=restTemplate.exchange(endPoint, HttpMethod.PUT, null, Category.class);
		Category categoryResponse=response.getBody();
		System.out.println(categoryResponse);
		assertEquals(1,categoryResponse.getCategoryId());
		assertEquals("human-resource",categoryResponse.getCategoryName());
		
		
	}
	@Disabled
	@Test
	public void testGetAll() {
		String endPoint="http://localhost:8080/category";
		CategoryDTO[] categories=restTemplate.getForObject(endPoint, CategoryDTO[].class);
		assertEquals(categories.length,9);
		assertEquals(categories[8].getCategoryName(),"research");
	}
	
	@Disabled
	@Test
	public void testSaveCategory() {
		String endPoint="http://localhost:8080/category";
		Category category=new Category();
		category.setCategoryId(501);
		category.setCategoryName("research");
		//here you need to send a post request
		//you need to pass your category object as json
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		HttpEntity requestEntity=new HttpEntity(category, headers);
		ResponseEntity<Category> response=restTemplate.exchange(endPoint, HttpMethod.POST, requestEntity, Category.class);
		Category categoryResponse=response.getBody();
		System.out.println(categoryResponse);
		assertEquals(category.getCategoryName(),categoryResponse.getCategoryName());
		
		
	}
	@Disabled
	@Test
	public void testFindByIdAPI() {
		//here we need to call the api for findById
		String endPoint="http://localhost:8080/category/1";
		CategoryDTO categoryDTO=restTemplate.getForObject(endPoint, CategoryDTO.class);
		assertNotNull(categoryDTO);
		assertEquals("hr",categoryDTO.getCategoryName());
		
	}

}
