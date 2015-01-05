package com.tmoncorp.PropertyManager.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tmoncorp.PropertyManager.model.CategoryModel;

/**
 * 
 * @author piro
 *
 */

@Repository
public class CategoryRepository {
	@Autowired
	private SqlSession sqlSession;

	public List<CategoryModel> selectAllCategories(int division) {
		CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
		return categoryMapper.selectAllCategories(division);
	}

	public int insertUpperCategory(CategoryModel category) {
		CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
		return categoryMapper.insertUpperCategory(category);
	}

	public int insertLowerCategory(CategoryModel categoryModel) {
		CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
		return categoryMapper.insertLowerCategory(categoryModel);
	}

	public int selectSpecificCategory(String categoryName) {
		CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
		return categoryMapper.selectSpecificCategory(categoryName);
	}
}
