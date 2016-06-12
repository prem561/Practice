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
import com.clic.model.Items;
import com.clic.model.SubCategorys;
import com.clic.model.User;
import com.clic.model.brands;
import com.clic.model.categorys;
import com.clic.model.otp;
import com.clic.model.otpValidation;
import com.clic.model.products;
import com.clic.model.successbean;



/**
 * UserDao.java
 * Purpose: ProductsDao class is responsible for 
 * @author CLIC
 * @version 1.0
 */

public class ProductsDao {
	
    String errorcode = "";
                  
    /**
     * Method to validate user mobile number 
     */
    public Object brandslist() {
    	try
        {
        	return getbrandslist();
        } catch (Exception e) {
            throw new RuntimeException(e);
		}
    } 
    /**
	 * Method to check user existed or not
	 */
    private Object getbrandslist() {		
		Connection c = null;		
		PreparedStatement ps = null;
		brands brands = new brands();
		ResultSet rs=null;
		String getqry="select * from brands";
		ArrayList<brands> brandslist = new ArrayList<brands>();
		try {
			c = ConnectionHelper.getConnection();
			ps = c.prepareStatement(getqry);
	      	rs=ps.executeQuery();
	      	while(rs.next())
	      	{
	      		brands.setBrandID(rs.getString(1));
	      		brands.setName(rs.getString(2));
	      		brands.setLogoUrl(rs.getString(3));
	      		brands.setDescription(rs.getString(4));
	      		brandslist.add(brands);
	      	}
		
		} catch (Exception e) {
			return new ErrorMessage().getErrorResponse("100");
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
		return brands;
	}
    
    
/**
* Method to validate user mobile number 
*/
public Object productslist(String brandid) {
try
{
	return getproductslist(brandid);
} catch (Exception e) {
 throw new RuntimeException(e);
}
} 
/**
* Method to check user existed or not
*/
private Object getproductslist(String brandid) {		
Connection c = null;		
PreparedStatement ps = null;
products products = new products();
ResultSet rs=null;
ArrayList<products> productslist = new ArrayList<products>();
String getqry="select * from products where brand_id=?";
try {
	c = ConnectionHelper.getConnection();
	ps = c.prepareStatement(getqry);
	ps.setString(1, brandid);
	rs=ps.executeQuery();
	while(rs.next())
	{
		products.setBrandID(rs.getString("brand_id"));
		products.setName(rs.getString("product_name"));
		products.setProductId(rs.getString("product_id"));
		products.setDescription(rs.getString("description"));
		productslist.add(products);
	}

} catch (Exception e) {
	return new ErrorMessage().getErrorResponse("100");
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
return productslist;
}

/**
* Method to validate user mobile number 
*/
public Object itemsexist(String customerid) {
try
{
	return checkitemexist(customerid);
} catch (Exception e) {
 throw new RuntimeException(e);
}
} 
/**
* Method to check user existed or not
*/
private Object checkitemexist(String customerid) {		
Connection c = null;		
PreparedStatement ps = null;
products products = new products();
ResultSet rs=null;
String getqry="select * from customer_item_data where customer_id=?";
successbean bean= new successbean();

try {
	c = ConnectionHelper.getConnection();
	ps = c.prepareStatement(getqry);
	ps.setString(1, customerid);
	rs=ps.executeQuery();
	bean.setResponseCode("200");
	bean.setServicetype("Method for check items existed or not for logged in user");
	bean.setUserid(customerid);
	if(rs.next())
	{
		
		bean.setStatus("yes");
		
		
	}
	else
	{
		bean.setStatus("no");
	}

} catch (Exception e) {
	return new ErrorMessage().getErrorResponse("100");
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
return bean;
}



/**
* Method to validate user mobile number 
*/
public Object categoryslist(categorys categorys) {
try
{
return getcategoryslist(categorys);
} catch (Exception e) {
throw new RuntimeException(e);
}
} 
/**
* Method to check user existed or not
*/
private Object getcategoryslist(categorys categorys) {		
Connection c = null;		
PreparedStatement ps = null;
ResultSet rs=null;
ArrayList<categorys> categoryslist = new ArrayList<categorys>();
String getqry="select * from category where brand_id=? and product_id = ?";
try {
c = ConnectionHelper.getConnection();
ps = c.prepareStatement(getqry);
ps.setString(1, categorys.getBrandID());
ps.setString(2, categorys.getProductId());
rs=ps.executeQuery();

while(rs.next())
{
	categorys.setBrandID(rs.getString("brand_id"));
	categorys.setName(rs.getString("category_name"));
	categorys.setProductId(rs.getString("product_id"));
	categorys.setDescription(rs.getString("description"));
	categorys.setCategoryId(rs.getString("category_id"));
	categoryslist.add(categorys);
}

} catch (Exception e) {
return new ErrorMessage().getErrorResponse("100");
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
return categoryslist;
}

/**
* Method to validate user mobile number 
*/
public Object subcategoryslist(SubCategorys subcategorys) {
try
{
return getsubcategoryslist(subcategorys);
} catch (Exception e) {
throw new RuntimeException(e);
}
} 
/**
* Method to check user existed or not
*/
private Object getsubcategoryslist(SubCategorys subcategorys) {		
Connection c = null;		
PreparedStatement ps = null;
ResultSet rs=null;
ArrayList<SubCategorys> subcategoryslist = new ArrayList<SubCategorys>();
String getqry="select * from subcategory where brand_id=? and product_id = ? and category_id = ?";
try {
c = ConnectionHelper.getConnection();
ps = c.prepareStatement(getqry);
ps.setString(1, subcategorys.getBrandID());
ps.setString(2, subcategorys.getProductId());
ps.setString(3, subcategorys.getCategoryId());
rs=ps.executeQuery();

while(rs.next())
{
	subcategorys.setBrandID(rs.getString("brand_id"));
	subcategorys.setName(rs.getString("name"));
	subcategorys.setProductId(rs.getString("product_id"));
	subcategorys.setDescription(rs.getString("description"));
	subcategorys.setCategoryId(rs.getString("category_id"));
	subcategorys.setSubcategoryId(rs.getString("subcategory_id"));
	subcategoryslist.add(subcategorys);
}

} catch (Exception e) {
return new ErrorMessage().getErrorResponse("100");
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
return subcategoryslist;
}


/**
* Method to validate user mobile number 
*/
public Object itemslist(Items items) {
try
{
return getitemslist(items);
} catch (Exception e) {
throw new RuntimeException(e);
}
} 
/**
* Method to check user existed or not
*/
private Object getitemslist(Items items) {		
Connection c = null;		
PreparedStatement ps = null;
ResultSet rs=null;
ArrayList<Items> itemslist = new ArrayList<Items>();
String getqry="select * from master_item where brand_id=? and product_id = ? and category_id = ? and subcategory_id = ?";
try {
c = ConnectionHelper.getConnection();
ps = c.prepareStatement(getqry);
ps.setString(1, items.getBrandID());
ps.setString(2, items.getProductId());
ps.setString(3, items.getCategoryId());
ps.setString(4, items.getSubcategoryId());
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
	itemslist.add(items);
}

} catch (Exception e) {
return new ErrorMessage().getErrorResponse("100");
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
return itemslist;
}

	}