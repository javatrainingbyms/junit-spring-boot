package com.harsh.helpdesk.dto;

import java.util.List;

import com.harsh.helpdesk.entity.SubCategory;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTO {
	private Integer categoryId;
	private String categoryName;
	private List<SubCategory> subCategories;
	
	public CategoryDTO(Integer categoryId, String categoryName,List<SubCategory> subCategories) {
		this.categoryId=categoryId;
		this.categoryName=categoryName;
		this.subCategories=subCategories;
	}

	
	
}
