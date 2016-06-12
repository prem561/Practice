package com.clic.controller;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.clic.dao.ProductsDao;
import com.clic.dao.UserDao;
import com.clic.model.Items;
import com.clic.model.SubCategorys;
import com.clic.model.User;
import com.clic.model.UserItem;
import com.clic.model.categorys;
import com.clic.model.otpValidation;
import com.clic.model.serrviceRequest;
import com.clic.model.ticket;
 
@Path("/user")
public class UserService {
 
	/*
	 * Method for user mobile number duplication
	 */
	
	@GET
	@Path("checkuserduplicate/{param}")
	public Object ValidateUser(@PathParam("param") String mobilenumber)
	{
		UserDao obj = new UserDao();
		return obj.validatenumber(mobilenumber);
	}

	@POST
	@Path("createuser")
	@Consumes("application/json")
	@Produces("application/json")
	public Object createuser(User user)
	{
		UserDao obj=new UserDao();
		
		return obj.createuser(user);
	}
	
	
	@POST
	@Path("otpvalidation")
	@Consumes("application/json")
	@Produces("application/json")
	public Object ValidateOtp(otpValidation otpValidation)
	{
		UserDao obj=new UserDao();
		
		return obj.validateOtp(otpValidation);
	}

	@POST
	@Path("login")
	@Consumes("application/json")
	@Produces("application/json")
	public Object login(User userbean)
	{
		UserDao obj=new UserDao();
		return obj.login(userbean);
	}

	/*
	 * Method for get brands list
	 */
	
	@GET
	@Path("brandlist")
	public Object brandslist()
	{
		ProductsDao obj = new ProductsDao();
		return obj.brandslist();
	}
	

	@GET
	@Path("itmesexist/{param}")
	public Object itmesexist(@PathParam("param") String customerID)
	{
		ProductsDao obj = new ProductsDao();
		return obj.itemsexist(customerID);
	}
	
	
	@GET
	@Path("productslist/{param}")
	public Object productslist(@PathParam("param") String brandid)
	{
		ProductsDao obj = new ProductsDao();
		return obj.productslist(brandid);
	}
	
	@POST
	@Consumes("application/json")
	@Path("categoryslist")
	public Object categoryslist(categorys categorys)
	{
		ProductsDao obj = new ProductsDao();
		return obj.categoryslist(categorys);
	}
	
	@POST
	@Consumes("application/json")
	@Path("subcategoryslist")
	public Object subcategoryslist(SubCategorys subcategorys)
	{
		ProductsDao obj = new ProductsDao();
		return obj.subcategoryslist(subcategorys);
	}
	@POST
	@Consumes("application/json")
	@Path("itemslist")
	public Object itemslist(Items items)
	{
		ProductsDao obj = new ProductsDao();
		return obj.itemslist(items);
	}
	
	
	@POST
	@Consumes("application/json")
	@Path("adduseritem")
	public Object adduseritem(UserItem items)
	{
		UserDao obj = new UserDao();
		return obj.addUserItemData(items);
	}
	
	
	@GET
	@Path("useritemslist/{param}")
	public Object useritemslist(@PathParam("param") String userid)
	{
		UserDao obj = new UserDao();
		return obj.itemslist(userid);
	}
	
	@POST
	@Consumes("application/json")
	@Path("raiseaticket")
	public Object useriteminfo(ticket ticketBean)
	{
		UserDao obj = new UserDao();
		return obj.addUserTicket(ticketBean);
	}
	
	@GET
	@Path("usertickets/{param}")
	public Object usertickets(@PathParam("param") String customerID)
	{
		UserDao obj = new UserDao();
		return obj.userticketsList(customerID);
	}
	@POST
	@Consumes("application/json")
	@Path("updateticket")
	public Object updateTicket(ticket ticketBean)
	{
		UserDao obj = new UserDao();
		return obj.updateTicket(ticketBean);
	}

	
	@GET
	@Path("typeoservices/")
	public Object typeoservices()
	{
		UserDao obj = new UserDao();
		return obj.typeofservices();
	}

	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("servicerequest")
	public Object servicerequest(serrviceRequest servrequst)
	{
		UserDao obj = new UserDao();
		System.out.println("hee");
		return obj.addserviceRequest(servrequst);
	}
}