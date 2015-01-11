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

	List<CategoryModel> selectUpperCategories();

	List<CategoryModel> selectLowerCategories(int upperCategory);

	int insertUpperCategory(CategoryModel category);

	int insertLowerCategory(CategoryModel category);

	int selectSpecificCategory(String categoryName);

	int selectLowerCategoryCount(int upperCategory);
}
