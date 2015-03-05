package com.tmoncorp.PropertyManager.model;

import java.sql.Date;

/**
 * 
 * @author pirothewriter
 *
 */

public class InspectionModel {
	private int nth;
	private String propertyNumber;
	private String propertyName;
	private String memberName;
	private String adAccount;
	private String realMemberName;
	private String realAdAccount;
	private Date inspectionDate;

	public int getNth() {
		return nth;
	}

	public void setNth(int nth) {
		this.nth = nth;
	}

	public String getPropertyNumber() {
		return propertyNumber;
	}

	public void setPropertyNumber(String propertyNumber) {
		this.propertyNumber = propertyNumber;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getAdAccount() {
		return adAccount;
	}

	public void setAdAccount(String adAccount) {
		this.adAccount = adAccount;
	}

	public String getRealMemberName() {
		return realMemberName;
	}

	public void setRealMemberName(String realMemberName) {
		this.realMemberName = realMemberName;
	}

	public String getRealAdAccount() {
		return realAdAccount;
	}

	public void setRealAdAccount(String realAdAccount) {
		this.realAdAccount = realAdAccount;
	}

	public Date getInspectionDate() {
		return inspectionDate;
	}

	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}

}
