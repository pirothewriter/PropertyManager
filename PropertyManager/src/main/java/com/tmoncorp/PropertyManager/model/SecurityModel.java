package com.tmoncorp.PropertyManager.model;

/**
 * 
 * @author piro
 *
 */

public class SecurityModel {
	private String userName;
	private String passworkd;
	private String role;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassworkd() {
		return passworkd;
	}

	public void setPassworkd(String passworkd) {
		this.passworkd = passworkd;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
