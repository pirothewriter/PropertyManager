package com.tmoncorp.PropertyManager.service;

import static org.junit.Assert.assertTrue;

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
public class CsvReaderServiceTest {
	@Autowired
	private CsvReaderService csvReaderService;

	@Transactional
	@Test
	public void 정상적으로_여래개의_사원정보를_입력하는지_테스트() throws IOException {
		File file = new File("WebContent/csv/test_member.csv");
		csvReaderService.multipleMemberInsert(file);
	}
	
	@Transactional
	@Test
	public void 정상적으로_매핑된_정보를_입력하는지_테스트() throws IOException, ParseException{
		File file = new File("WebContent/csv/map_test.csv");
		csvReaderService.multipleMappedInsert(file);
	}

	@Test
	public void 폼이_일치하는지_테스트() throws IOException {
		assertTrue(csvReaderService.verifyForm(new File("WebContent/csv/test_member.csv"), "member"));
		assertTrue(csvReaderService.verifyForm(new File("WebContent/csv/test_property.csv"), "property"));
	}
}
