package com.tmoncorp.PropertyManager.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 
 * @author piro
 *
 */

public class ExchangeDateBetweenString {
	public Date stringToDate(String expressionDateForString) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDate = dateFormat.parse(expressionDateForString);
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate;
	}
	
	public String returnNowDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy. MM. dd");
		java.util.Date today = new java.util.Date();
		String nowDate = dateFormat.format(today);
		return nowDate;
	}
}
