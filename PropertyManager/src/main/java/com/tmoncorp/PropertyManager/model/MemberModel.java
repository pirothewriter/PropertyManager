package com.tmoncorp.PropertyManager.model;

/**
 * 
 * @author piro
 *
 */

public class MemberModel {
	private String memberId;
	private String memberName;
	private String upperDivision;
	private String lowerDivision;
	private String adAccount;
	private int officePhoneNumber;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getUpperDivision() {
		return upperDivision;
	}

	public void setUpperDivision(String upperDivision) {
		this.upperDivision = upperDivision;
	}

	public String getLowerDivision() {
		return lowerDivision;
	}

	public void setLowerDivision(String lowerDivision) {
		this.lowerDivision = lowerDivision;
	}

	public String getAdAccount() {
		return adAccount;
	}

	public void setAdAccount(String adAccount) {
		this.adAccount = adAccount;
	}

	public int getOfficePhoneNumber() {
		return officePhoneNumber;
	}

	public void setOfficePhoneNumber(int officePhoneNumber) {
		this.officePhoneNumber = officePhoneNumber;
	}
}