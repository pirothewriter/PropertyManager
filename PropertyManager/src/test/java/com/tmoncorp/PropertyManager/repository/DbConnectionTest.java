package com.tmoncorp.PropertyManager.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tmoncorp.PropertyManager.model.TestModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext*.xml" })
public class DbConnectionTest {
	@Autowired
	private DbConnection dbConnection;
	
	@Test
	public void test() {
		assertNotNull(dbConnection.select());
	}
	
	@Test
	public void 값_검증(){
		List<TestModel> resultList = dbConnection.select();
		TestModel result = resultList.get(0);
		assertEquals(result.getNumbering(), 1);
	}

}
