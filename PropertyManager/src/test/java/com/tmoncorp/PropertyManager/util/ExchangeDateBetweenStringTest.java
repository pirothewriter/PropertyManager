package com.tmoncorp.PropertyManager.util;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author piro
 *
 */

public class ExchangeDateBetweenStringTest {
	private ExchangeDateBetweenString exchangeDateBetweenString;

	@Before
	public void setup() {
		exchangeDateBetweenString = new ExchangeDateBetweenString();
	}

	@Test
	public void String으로_넘어온_날짜를_sql_타입의_DATE로_올바르게_변환하는지_테스트() throws ParseException {
		String testDate = "2014-12-30";
		Date resultDate = exchangeDateBetweenString.stringToDate(testDate);
		System.out.println(resultDate);
	}

}
