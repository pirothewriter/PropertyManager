package com.tmoncorp.PropertyManager.util;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.tmoncorp.PropertyManager.model.CategoryModel;
import com.tmoncorp.PropertyManager.model.EquipmentModel;

/**
 * 
 * @author piro
 *
 */

public class JsonEncodingTest {
	private JsonEncoding jsonEncoding;

	@Before
	public void setup() {
		jsonEncoding = new JsonEncoding();
	}

	@Test
	public void 제이슨으로_인코딩하는지_테스트() {
		List<CategoryModel> categories = generateCategoryList();

		String result = jsonEncoding.encodingJson(categories);

		System.out.println(result);
	}

	@Test
	public void 객체를_제이슨으로_인코딩하는지_테스트() {
		EquipmentModel equipment = getTestInsertionData();
		String result = jsonEncoding.encodingJson(equipment);

		System.out.println(result);
	}

	private List<CategoryModel> generateCategoryList() {
		List<CategoryModel> categories = new ArrayList<CategoryModel>();

		for (int index = 0; index < 5; index++) {
			CategoryModel category = new CategoryModel();

			category.setCategoryCode(index);
			category.setCategoryDivision(index % 2);
			category.setCategoryName("테스트" + String.valueOf(index));

			categories.add(category);
		}

		return categories;
	}

	private EquipmentModel getTestInsertionData() {
		EquipmentModel equipmentModel = new EquipmentModel();

		equipmentModel.setPropertyNumber("test");
		equipmentModel.setIncommingFinance(new Date(System.currentTimeMillis()));
		equipmentModel.setIncommingItUnit(new Date(System.currentTimeMillis()));
		equipmentModel.setInfomation1("test");
		equipmentModel.setInfomation2("test");
		equipmentModel.setName("test");
		equipmentModel.setPrice(1000);
		equipmentModel.setProductor("test");
		equipmentModel.setSeller("test");
		equipmentModel.setUser("티켓몬스터");
		equipmentModel.setLowerCategory("test");
		equipmentModel.setUpperCategory("test");
		return equipmentModel;
	}

}
