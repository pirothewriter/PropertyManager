package com.tmoncorp.PropertyManager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmoncorp.PropertyManager.model.CategoryModel;
import com.tmoncorp.PropertyManager.repository.CategoryRepository;

/**
 * 
 * @author piro
 *
 */

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public List<CategoryModel> getAllUpperCategory() {
		return categoryRepository.selectAllCategories(0);
	}

	public List<CategoryModel> getAllLowerCategory() {
		return categoryRepository.selectAllCategories(1);
	}

	public int insertUpperCategory(CategoryModel categoryModel) {
		return categoryRepository.insertUpperCategory(categoryModel);
	}

	public int insertLowerCategory(CategoryModel categoryModel) {
		return categoryRepository.insertLowerCategory(categoryModel);
	}

}
