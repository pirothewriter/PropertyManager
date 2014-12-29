package com.tmoncorp.PropertyManager.util;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author piro
 *
 */

public class ExchangeCharacterSetTest {
	private ExchangeCharacterSet exchangeCharacterSet;

	@Before
	public void setup() {
		exchangeCharacterSet = new ExchangeCharacterSet();
	}

	@Test
	public void 문자셋을_정상적으로_변환하는지_테스트() throws IOException {
		String testString = "가나다라마바사";
		assertEquals(7, (exchangeCharacterSet.convert(testString, "EUC-KR").length()));
		assertEquals(7, (exchangeCharacterSet.convert(testString, "UTF-8").length()));
	}
}
