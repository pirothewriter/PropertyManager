package com.tmoncorp.PropertyManager.model;

import java.sql.Date;

/**
 * 
 * @author pirothewriter
 *
 */

public class InspectionLogModel {
	private int nth;
	private String adAccount;
	private String propertyNumber;
	private Date inspectedDate;

	public int getNth() {
		return nth;
	}

	public void setNth(int nth) {
		this.nth = nth;
	}

	public String getAdAccount() {
		return adAccount;
	}

	public void setAdAccount(String adAccount) {
		this.adAccount = adAccount;
	}

	public String getPropertyNumber() {
		return propertyNumber;
	}

	public void setPropertyNumber(String propertyNumber) {
		this.propertyNumber = propertyNumber;
	}

	public Date getInspectedDate() {
		return inspectedDate;
	}

	public void setInspectedDate(Date inspectedDate) {
		this.inspectedDate = inspectedDate;
	}

}
