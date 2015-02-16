package com.tmoncorp.PropertyManager.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tmoncorp.PropertyManager.model.CategoryModel;

/**
 * 
 * @author piro
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext*.xml" })
public class CategoryRepositoryTest {
	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	public void 카테고리정보를_정상적으로_반환하는지_테스트() {
		assertNotNull(categoryRepository.selectAllCategories(0));
	}

	@Test
	public void 하위_카테고리정보를_정상적으로_반환하는지_테스트() {
		assertNotNull(categoryRepository.selectLowerCategories(16));
	}

	@Test
	public void 특정_카테고리를_정확히_셀렉하는지_테스트() {
		assertEquals(categoryRepository.selectSpecificCategory("마케팅Wg"), 438);
	}

	@Transactional
	@Test
	public void 상위카테고리를_정상적으로_인서트하는지_테스트() {
		CategoryModel categoryModel = generateTempUpperCategory();

		assertEquals(1, categoryRepository.insertUpperCategory(categoryModel));
	}

	@Transactional
	@Test
	public void 하위카테고리를_정상적으로_인서트하는지_테스트() {
		CategoryModel categoryModel = generateTempLowerCategory();

		assertEquals(1, categoryRepository.insertLowerCategory(categoryModel));
	}

	private CategoryModel generateTempUpperCategory() {
		CategoryModel categoryModel = new CategoryModel();

		categoryModel.setCategoryName("테스투");

		return categoryModel;
	}

	private CategoryModel generateTempLowerCategory() {
		CategoryModel categoryModel = new CategoryModel();

		categoryModel.setCategoryName("테스투");
		categoryModel.setUpperCategory(0);

		return categoryModel;
	}

}
