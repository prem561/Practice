package com.clic.dao;

/****************************************************************************
 *   Copyright (c)2016 CLIC. All rights reserved.
 *
 *   THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF CLIC.
 *
 *   The copyright notice above does not evidence any actual or intended
 *   publication of such source code.
 *****************************************************************************/


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.clic.Dbconnection.ConnectionHelper;
import com.clic.Utility.ErrorMessage;
import com.clic.Utility.Utility;
import com.clic.model.User;
import com.clic.model.UserItem;
import com.clic.model.iteemDocs;
import com.clic.model.otp;
import com.clic.model.otpValidation;
import com.clic.model.serrviceRequest;
import com.clic.model.serviceType;
import com.clic.model.serviceTypeResponse;
import com.clic.model.successbean;
import com.clic.model.ticket;



/**
 * UserDao.java
 * Purpose: Userdao class is responsible for user creation , login , otp generation and check user duplication.
 * @author CLIC
 * @version 1.0
 */

public class UserDao {
	
    String errorcode = "";
                  
    /**
     * Method to validate user mobile number 
     */
    public Object validatenumber(String number) {
    	try
        {
        	boolean checkduplicate = false;
        	checkduplicate = validatemobilenumber(number);
        	if(checkduplicate == false) 
            {
        		return "{\"responsecode\":\"200\",\"servicetype\":\"Check Duplicates\",\"status\":\"New user.\"}";           	
            }else
            {
            	return new ErrorMessage().getErrorResponse("102");  
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
		}
    } 
    /**
	 * Method to check user existed or not
	 */
    private boolean validatemobilenumber(String number) {		
		Connection c = null;		
		PreparedStatement ps = null;
		boolean questioninsertion=false;
		ResultSet rs=null;
		String getqry="select customer_mob from customer where customer_mob = ?";
		try {
			c = ConnectionHelper.getConnection();
			ps = c.prepareStatement(getqry);
			ps.setString(1, number);
	      	rs=ps.executeQuery();
	      	if(rs.next())
	      	{
	      		questioninsertion = true;
	      	}
		
		} catch (Exception e) {
			
		}
		finally
		{
 			ConnectionHelper.close(c);	
 			try {
				if(ps!=null)ps.close();
				if(rs!=null)rs.close();
			} catch (SQLException e) {
			}
		}
		return questioninsertion;
	}
    public Object createuser(User userbean)
    {
    	Object obj=insertuser(userbean);
    	
    	return obj;
    }
    public Object insertuser(User userbean)
    {
		Connection c = null;		
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		otp bean=new  otp();
	try {
		c = ConnectionHelper.getConnection();
		ps = c.prepareStatement("insert into customer(customer_name,customer_email,customer_mob,customer_password) values (?,?,?,?)");
		ps.setString(1, userbean.getName());
		ps.setString(2, userbean.getEmailid());
		ps.setString(3, userbean.getPhonenumber());
		ps.setString(4, userbean.getPassword());
		int i =ps.executeUpdate();
		int otp=0;
		if(i>0)
		{
			otp=Utility.generatePIN();
			ps1 = c.prepareStatement("insert into otp(customer_mobilenumber,otp) values (?,?)");
			ps1.setString(1,  userbean.getPhonenumber());
			ps1.setString(2, otp+"");
			int k=ps1.executeUpdate();
			if(k>0)
			{
				bean.setOtp(otp+"");
				bean.setResponseCode(200);
				bean.setStatus("User created successfully , Login with this otp");
			}
			else
			{
				return new ErrorMessage().getErrorResponse("100");
			}
			
		}
		else
		{
			return new ErrorMessage().getErrorResponse("101");
		}
		
			
	} catch (Exception e) {
		// TODO: handle exception
	}
	finally
	{
			ConnectionHelper.close(c);	
			try {
			if(ps!=null)ps.close();
			if(ps1!=null)ps1.close();
		} catch (SQLException e) {
		}
	}
    	return bean;
    }



		// code for validate otp

	public Object validateOtp(otpValidation otpValidation)
	{
		Object obj= validateotp(otpValidation);
		return obj;
	}
	
	public Object validateotp(otpValidation otpValidation)
	{
		Connection c = null;		
		PreparedStatement ps = null;
		otp otpbean=new  otp();
	try {
		c = ConnectionHelper.getConnection();
		
		ps = c.prepareStatement("select otp  from customer where customer_mobilenumber = ? and otp=?");
		ps.setInt(1, otpValidation.getUserid());
		ps.setString(2, otpValidation.getUser_otp());
		
		ResultSet rs =ps.executeQuery();
		
		if(rs.next())
		{
			otpbean.setResponseCode(200);
			otpbean.setStatus("User validated successfully");
				//success code
		}else 
		{
				return new ErrorMessage().getErrorResponse("104");
		}
			
	} catch (Exception e) {
		// TODO: handle exception
	}
		return otpbean;
		
	}
	public Object login(User userbean) {
		// TODO Auto-generated method stub
		
		Object obj= authenticateUser(userbean);
		return obj;
	}
	private Object authenticateUser(User userbean) {
		// TODO Auto-generated method stub
		Connection c = null;		
		PreparedStatement ps = null;
		successbean bean= new successbean();
		try {
			c = ConnectionHelper.getConnection();
			ps = c.prepareStatement("select customer_id  from customer where customer_mob = ? and customer_password=?");
			ps.setString(1, userbean.getPhonenumber());
			ps.setString(2, userbean.getPassword());
			ResultSet rs =ps.executeQuery();
			if(rs.next())
			{
				bean.setResponseCode("200");
				bean.setServicetype("User Authentication");
				bean.setStatus("User authenticated successfully");
				bean.setUserid(rs.getString(1));
				System.out.println("bean : "+bean.getUserid());
				return bean;
			}
			else
			{
				return new ErrorMessage().getErrorResponse("105");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return bean;
	}
	

	
	
	public Object addUserItemData(UserItem useritem)
	{
		Connection c = null;		
		PreparedStatement ps = null;
		successbean successbean=new  successbean();
	try {
		c = ConnectionHelper.getConnection();
		
		ps = c.prepareStatement("INSERT INTO customer_item_data"
								+ "(brand_id,product_id,category_id,subcategory_id,year_of_purchase,"
								+ "Model_Number,customer_id,warrenty) VALUES (?,?,?,?,?,?,?,?)");
		ps.setString(1, useritem.getBrandID());
		ps.setString(2, useritem.getProductId());
		ps.setString(3, useritem.getCategoryId());
		ps.setString(4, useritem.getSubcategoryId());
		ps.setString(5, useritem.getYearop());
		ps.setString(6, useritem.getModelnumber());
		ps.setString(7, useritem.getCustomerId());
		ps.setString(8, useritem.getWarrentyMonths());
	    int i=ps.executeUpdate();
		
	    ArrayList<iteemDocs> listofdocs = useritem.getItemdocs();
	    
	    // Need to get item id 
	    
	    for(int l=0;l<listofdocs.size();l++)
	    {
			ps = c.prepareStatement("INSERT INTO customer_item_documents"
									+ "(item_id,doctype_id,file_path) VALUES (?,?,?)");
			ps.setString(1, "1");
			ps.setString(2, listofdocs.get(l).getDocType());
			ps.setString(3, listofdocs.get(l).getFilePath());

		    //images need to create in server
	    }
	    
	    
		if(i>0)
		{
			successbean.setResponseCode("200");
			successbean.setStatus("Added products successfully");
				//success code
		}
		else 
		{
				return new ErrorMessage().getErrorResponse("104");
		}
			
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("ex :"+e);
	}
		return successbean;
		
	}

	public Object itemslist(String customerId) {
		try
		{
		return getitemslist(customerId);
		} catch (Exception e) {
		throw new RuntimeException(e);
		}
	} 
	
	private Object getitemslist(String customerId)
	{		
		UserItem items = new UserItem();
		Connection c = null;		
		PreparedStatement ps = null;
		ResultSet rs=null;
		PreparedStatement ps1 = null;
		ResultSet rs1=null;
		ArrayList<UserItem> itemslist = new ArrayList<UserItem>();
	
		String getqry="select * from customer_item_data where customer_id=?";
		try 
		{
				c = ConnectionHelper.getConnection();
				ps = c.prepareStatement(getqry);
				ps.setString(1, items.getBrandID());
				rs=ps.executeQuery();
				while(rs.next())
				{
					items.setBrandID(rs.getString("brand_id"));
					items.setModelnumber(rs.getString("Model_Number"));
					items.setProductId(rs.getString("product_id"));
					items.setDescription(rs.getString("description"));
					items.setCategoryId(rs.getString("category_id"));
					items.setSubcategoryId(rs.getString("subcategory_id"));
					items.setSpecification(rs.getString("Specification"));
					items.setItemid(rs.getString("item_id"));
					items.setCustomerId(customerId);
					items.setWarrentyMonths(rs.getString("warrenty"));
					ArrayList<iteemDocs> idocs= new ArrayList<iteemDocs>();			
					// Need to get list of docs
					ps1 = c.prepareStatement("select * from customer_item_documents where item-id = ?");
					ps1.setString(1, rs.getString("item_id"));
					rs1=ps1.executeQuery();
					while(rs1.next())
					{
						iteemDocs docs= new iteemDocs();
						docs.setDocType("Invoice");
						docs.setFilePath("local");
						idocs.add(docs);
					}
					items.setItemdocs(idocs);
					itemslist.add(items);
				}
		} 
		catch (Exception e)
		{
			System.out.println(e);
			return new ErrorMessage().getErrorResponse("100");
		}
		finally
		{
			ConnectionHelper.close(c);	
			try 
			{
				if(ps!=null)ps.close();
				if(rs!=null)rs.close();
			} 
			catch (SQLException e) {
			}
		}
		return itemslist;
	}
	public Object addUserTicket(ticket ticketBean)
	{
		Connection c = null;		
		PreparedStatement ps = null;
		successbean successbean=new  successbean();
	try {
		c = ConnectionHelper.getConnection();
		
		ps = c.prepareStatement("INSERT INTO ticket"
								+ "(customer_id,customer_item_id,raisedby,query,status"
								+ ") VALUES (?,?,?,?,?)");
		ps.setString(1, ticketBean.getCustomerID());
		ps.setString(2, ticketBean.getCustomerItemID());
		ps.setString(3, "self");
		ps.setString(4, ticketBean.getQuery());
		ps.setString(5, "new");
	    int i=ps.executeUpdate();
		
	    //images need to create in server
	    
		if(i>0)
		{
			successbean.setResponseCode("200");
			successbean.setStatus("Request raised successfully");
				//success code
		}
		else 
		{
				return new ErrorMessage().getErrorResponse("104");
		}
			
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("ex :"+e);
	}
		return successbean;
		
	}
	
	public Object userticketsList(String customerID)
	{
		Connection c = null;		
		PreparedStatement ps = null;
		ArrayList<ticket>  ticketsList =  new ArrayList<ticket>();
	try {
		c = ConnectionHelper.getConnection();
		
		ps = c.prepareStatement("select *  from ticket where customer_id = ? ");
		ps.setString(1, customerID);
		
		ResultSet rs =ps.executeQuery();
		
		while(rs.next())
		{
			ticket ticket=new ticket();
			ticket.setCustomerID(rs.getString("customer_id"));
			ticket.setCustomerItemID(rs.getString("customer_item_id"));
			ticket.setQuery(rs.getString("query"));
			ticket.setTicketId(rs.getString("ticket_id"));
			ticket.setStatus(rs.getString("status"));
			ticketsList.add(ticket);
			
		}
			
	} catch (Exception e) {
		// TODO: handle exception
	}
		return ticketsList;
		
	}
	
	
	public Object updateTicket(ticket ticketBean)
	{
		Connection c = null;		
		PreparedStatement ps = null;
		successbean successbean=new  successbean();
	try {
		c = ConnectionHelper.getConnection();
		
		ps = c.prepareStatement("INSERT INTO ticket_status"
								+ "(customer_id,customer_item_id,raisedby,query,status"
								+ ") VALUES (?,?,?,?,?)");
		ps.setString(1, ticketBean.getCustomerID());
		ps.setString(2, ticketBean.getCustomerItemID());
		ps.setString(3, "self");
		ps.setString(4, ticketBean.getQuery());
		ps.setString(5, "new");
	    int i=ps.executeUpdate();
		
	    //images need to create in server
	    
		if(i>0)
		{
			successbean.setResponseCode("200");
			successbean.setStatus("Request raised successfully");
				//success code
		}
		else 
		{
				return new ErrorMessage().getErrorResponse("104");
		}
			
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("ex :"+e);
	}
		return successbean;
		
	}
	public Object addserviceRequest(serrviceRequest ticketBean)
	{
		Connection c = null;		
		PreparedStatement ps = null;
		successbean successbean=new  successbean();
	try {
		c = ConnectionHelper.getConnection();
		
		ps = c.prepareStatement("INSERT INTO service_request"
								+ "(customer_id,type_of_request,description,scheduled_time,lat,lang,image_path,address"
								+ ",token_number) VALUES (?,?,?,?,?,?,?,?,?)");
		ps.setString(1, ticketBean.getCustomerId());
		ps.setString(2, ticketBean.getTypeofrequest());
		ps.setString(3, ticketBean.getDescription());
		ps.setString(4, ticketBean.getScheduledDate());
		ps.setString(5, ticketBean.getLat());
		ps.setString(6, ticketBean.getLang());
		ps.setString(7, "image path");
		ps.setString(8, "12334");
		ps.setString(9, "12334");
		
		//Write image in local server
		
	    int i=ps.executeUpdate();
		
	    //images need to create in server
	    
		if(i>0)
		{
			successbean.setResponseCode("200");
			successbean.setStatus("Request raised successfully");
			successbean.setUserid(ticketBean.getCustomerId());
				//success code
		}
		else 
		{
				return new ErrorMessage().getErrorResponse("100");
		}
			
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("ex :"+e);
		return new ErrorMessage().getErrorResponse("100");
	}
		return successbean;
		
	}
	

	public Object typeofservices()
	{
		Connection c = null;		
		PreparedStatement ps = null;
		serviceTypeResponse response = new serviceTypeResponse();
	try {
		c = ConnectionHelper.getConnection();
		
		ps = c.prepareStatement("select *  from request_types where status = ? ");
		ps.setBoolean(1, true);
		ResultSet rs =ps.executeQuery();
		ArrayList<serviceType> servicetypes = new ArrayList<serviceType>();
		while(rs.next())
		{
			serviceType obj= new serviceType();
			obj.setServiceType(rs.getString("request_type"));
			obj.setServiceTypeId(rs.getString("typeid"));
			servicetypes.add(obj);
		}

		response.setCustomerID("");
		response.setResponseCode("200");
		response.setStatus("Service types List");
		response.setServicetype("Type of services");
		response.setSerrviceRequests(servicetypes);
		
	} catch (Exception e) {
		// TODO: handle exception
	}
		return response;
		
	}
	
	

}