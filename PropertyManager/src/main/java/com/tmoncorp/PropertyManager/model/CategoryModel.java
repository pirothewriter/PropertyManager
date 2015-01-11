package com.tmoncorp.PropertyManager.model;

/**
 * 
 * @author piro
 *
 */

public class CategoryModel {
	private String categoryName;
	private int categoryDivision;
	private int categoryCode;
	private int upperCategory;
	private int lowerCategories;

	public int getUpperCategory() {
		return upperCategory;
	}

	public void setUpperCategory(int upperCategory) {
		this.upperCategory = upperCategory;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getCategoryDivision() {
		return categoryDivision;
	}

	public void setCategoryDivision(int categoryDivision) {
		this.categoryDivision = categoryDivision;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

	public int getLowerCategories() {
		return lowerCategories;
	}

	public void setLowerCategories(int lowerCategories) {
		this.lowerCategories = lowerCategories;
	}

}
