package com.tmoncorp.PropertyManager.repository;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author piro
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext*.xml" })
public class PropertyLogRepositoryTest {
	@Autowired
	private PropertyLogRepository propertyLogRepository;

	@Test
	public void 특정_자산의_로그를_정상적으로_셀렉트하는지_테스트() {
		assertNotNull(propertyLogRepository.getPropertyLog("PCM12345"));
	}

	@Test
	public void 특정자산의_현재_소유자의_지급일자를_확인하는_테스트() {
		Date result = (Date) propertyLogRepository.getPropertyNowStatus("PCM12345", "201404016");
		assertNotNull(result);
		System.out.println(result);
	}

}
