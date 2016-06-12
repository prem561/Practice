/****************************************************************************
 *   Copyright (c)2016 CLIC. All rights reserved.
 *
 *   THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF CLIC.
 *
 *   The copyright notice above does not evidence any actual or intended
 *   publication of such source code.
 *****************************************************************************/

package com.clic.Utility;


import java.util.Properties;

import com.clic.config.ProjectConfig;
import com.clic.model.ErrorMessages;

/**
 * ErrorMessage.java
 * Purpose: This class is responsible for returning error code and response in json format.
 * @author CLIC
 * @version 1.0
 */

public class ErrorMessage {
	
//	private static Logger logger = Logger.getLogger(ErrorMessage.class);
	
	/**
	 * Method to get Error Message for given error code from error messages properties file.
	 * @param errorCode
	 * @return String
	 * @author prem
	 */
	
	public String getErrorMessage(String errorCode)
	{
		String error_message="";
		try
		{
			ProjectConfig prop=new ProjectConfig("errormessages.properties");
	        Properties properties = prop.getProjectProperties();
//	        System.out.println("properties : "+properties);
	        String errormessage=properties.getProperty(errorCode);
	        error_message=errormessage;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		finally {
					
		}
		return error_message;
	}
	
	/**
	 * Method to get error response object.
	 * @param errorCode
	 * @return error response object.
	 */
	public Object getErrorResponse(String errorCode)
	{
		String error_message="";
    	error_message = getErrorMessage(errorCode);
    	ErrorMessages errorMessages = new ErrorMessages();
    	errorMessages.setErrorcode(errorCode);
    	errorMessages.setErrormessage(error_message);
    	System.out.println("get error message: "+errorMessages);
    	return errorMessages;
	}
	
}
