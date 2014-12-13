package com.tmoncorp.PropertyManager.model;

import java.sql.Date;

/**
 * 
 * @author piro
 *
 */

public class EquipmentModel {
	private String propertyNumber;
	private String name;
	private String upperCategory;
	private String lowerCategory;
	private String infomation1;
	private String infomation2;
	private Date incommingItUnit;
	private Date incommingFinance;
	private String productor;
	private String seller;
	private int price;
	private String user;
	
	public String getPropertyNumber() {
		return propertyNumber;
	}
	public void setPropertyNumber(String propertyNumber) {
		this.propertyNumber = propertyNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUpperCategory() {
		return upperCategory;
	}
	public void setUpperCategory(String upperCategory) {
		this.upperCategory = upperCategory;
	}
	public String getLowerCategory() {
		return lowerCategory;
	}
	public void setLowerCategory(String lowerCategory) {
		this.lowerCategory = lowerCategory;
	}
	public String getInfomation1() {
		return infomation1;
	}
	public void setInfomation1(String infomation1) {
		this.infomation1 = infomation1;
	}
	public String getInfomation2() {
		return infomation2;
	}
	public void setInfomation2(String infomation2) {
		this.infomation2 = infomation2;
	}
	public Date getIncommingItUnit() {
		return incommingItUnit;
	}
	public void setIncommingItUnit(Date incommingItUnit) {
		this.incommingItUnit = incommingItUnit;
	}
	public Date getIncommingFinance() {
		return incommingFinance;
	}
	public void setIncommingFinance(Date incommingFinance) {
		this.incommingFinance = incommingFinance;
	}
	public String getProductor() {
		return productor;
	}
	public void setProductor(String productor) {
		this.productor = productor;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}
