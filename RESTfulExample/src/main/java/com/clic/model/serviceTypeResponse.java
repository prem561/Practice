package com.clic.model;

import java.util.ArrayList;

public class serviceTypeResponse {
	
	private ArrayList<serviceType>	serrviceRequests;
	private String servicetype;
	private String status;
	private String responseCode;
	private String customerID;
	public ArrayList<serviceType> getSerrviceRequests() {
		return serrviceRequests;
	}
	public void setSerrviceRequests(ArrayList<serviceType> serrviceRequests) {
		this.serrviceRequests = serrviceRequests;
	}
	public String getServicetype() {
		return servicetype;
	}
	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
	

	
	
	
}
