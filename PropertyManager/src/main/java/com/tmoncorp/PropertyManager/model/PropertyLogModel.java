package com.tmoncorp.PropertyManager.model;

import java.util.Date;

/**
 * 
 * @author piro
 *
 */

public class PropertyLogModel {
	private String propertyNumber;
	private String adAccount;

	public String getAdAccount() {
		return adAccount;
	}

	public void setAdAccount(String adAccount) {
		this.adAccount = adAccount;
	}

	private Date urgentDate;
	private Date withdrawDate;

	public String getPropertyNumber() {
		return propertyNumber;
	}

	public void setPropertyNumber(String propertyNumber) {
		this.propertyNumber = propertyNumber;
	}

	public Date getUrgentDate() {
		return urgentDate;
	}

	public void setUrgentDate(Date urgentDate) {
		this.urgentDate = urgentDate;
	}

	public Date getWithdrawDate() {
		return withdrawDate;
	}

	public void setWithdrawDate(Date withdrawDate) {
		this.withdrawDate = withdrawDate;
	}
}
