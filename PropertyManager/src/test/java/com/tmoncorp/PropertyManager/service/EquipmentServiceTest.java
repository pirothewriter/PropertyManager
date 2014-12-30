package com.tmoncorp.PropertyManager.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author piro
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext*.xml" })
public class EquipmentServiceTest {
	@Autowired
	private EquipmentService equipmentService;
	
	@Transactional
	@Test
	public void 여래개의_장비정보가_제대로_들어가는지_테스트() throws IOException, ParseException{
		File file = new File("webContent/csv/test_property.csv");
		int result = equipmentService.insertMultipleEquipment(file);
		assertEquals(25, result);
	}
	
}
