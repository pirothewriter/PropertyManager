package com.tmoncorp.PropertyManager.repository;

import java.util.List;

import com.tmoncorp.PropertyManager.model.CategoryModel;

/**
 * 
 * @author piro
 *
 */

public interface CategoryMapper {
	List<CategoryModel> selectAllCategories(int division);

	int insertUpperCategory(CategoryModel category);
	
	int insertLowerCategory(CategoryModel category);

	int selectSpecificCategory(String categoryName);

}
