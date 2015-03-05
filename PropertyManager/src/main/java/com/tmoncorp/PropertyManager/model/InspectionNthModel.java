package com.tmoncorp.PropertyManager.model;

import java.sql.Date;

/**
 * 
 * @author pirothewriter
 *
 */

public class InspectionNthModel {
	private int nth;
	private Date startDate;
	private Date endDate;

	public int getNth() {
		return nth;
	}

	public void setNth(int nth) {
		this.nth = nth;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
