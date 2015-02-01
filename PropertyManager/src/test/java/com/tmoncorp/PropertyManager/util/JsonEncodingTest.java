package com.tmoncorp.PropertyManager.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.tmoncorp.PropertyManager.model.CategoryModel;

/**
 * 
 * @author piro
 *
 */

public class JsonEncodingTest {
	private JsonEncoding jsonEncoding;
	
	@Before
	public void setup(){
		jsonEncoding = new JsonEncoding();
	}
	
	@Test
	public void 제이슨으로_인코딩하는지_테스트(){
		List<CategoryModel> categories = generateCategoryList();
		
		String result = jsonEncoding.encodingJson(categories);
		
		System.out.println(result);
	}
	
	private List<CategoryModel> generateCategoryList(){
		List<CategoryModel> categories = new ArrayList<CategoryModel>();
		
		for(int index = 0; index < 5; index ++){
			CategoryModel category = new CategoryModel();
			
			category.setCategoryCode(index);
			category.setCategoryDivision(index % 2);
			category.setCategoryName("테스트" + String.valueOf(index));
			
			categories.add(category);
		}
		
		return categories;
	}

}
