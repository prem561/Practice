package com.clic.model;

import java.util.ArrayList;

public class UserItem {

	private String brandID;
	private String productId;
	private String description;
	private String modelnumber;
	private String categoryId;
	private String subcategoryId;
	private String yearop;
	private String itemid;

	private String customerId;	
	private String invoiceCopy;
	private String userMannual;
	private String insurancecopy;

	private String warrentyCopy;

	private String warrentyMonths;
	private String specification;	
	
	private ArrayList<iteemDocs> itemdocs;
	
	
	
	
	
	public ArrayList<iteemDocs> getItemdocs() {
		return itemdocs;
	}
	public void setItemdocs(ArrayList<iteemDocs> itemdocs) {
		this.itemdocs = itemdocs;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getInvoiceCopy() {
		return invoiceCopy;
	}
	public void setInvoiceCopy(String invoiceCopy) {
		this.invoiceCopy = invoiceCopy;
	}
	public String getUserMannual() {
		return userMannual;
	}
	public void setUserMannual(String userMannual) {
		this.userMannual = userMannual;
	}
	public String getInsurancecopy() {
		return insurancecopy;
	}
	public void setInsurancecopy(String insurancecopy) {
		this.insurancecopy = insurancecopy;
	}
	public String getWarrentyCopy() {
		return warrentyCopy;
	}
	public void setWarrentyCopy(String warrentyCopy) {
		this.warrentyCopy = warrentyCopy;
	}
	public String getWarrentyMonths() {
		return warrentyMonths;
	}
	public void setWarrentyMonths(String warrentyMonths) {
		this.warrentyMonths = warrentyMonths;
	}
	public String getModelnumber() {
		return modelnumber;
	}
	public void setModelnumber(String modelnumber) {
		this.modelnumber = modelnumber;
	}
	public String getYearop() {
		return yearop;
	}
	public void setYearop(String yearop) {
		this.yearop = yearop;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getSubcategoryId() {
		return subcategoryId;
	}
	public void setSubcategoryId(String subcategoryId) {
		this.subcategoryId = subcategoryId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getBrandID() {
		return brandID;
	}
	public void setBrandID(String brandID) {
		this.brandID = brandID;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	
}
