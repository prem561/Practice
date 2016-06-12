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
 * SuccessMessage.java
 * Purpose: This class is responsible for returning error code and response in json format.
 * @author CLIC
 * @version 1.0
 */

public class SuccessMessage {
	
//	private static Logger logger = Logger.getLogger(ErrorMessage.class);
	
	/**
	 * Method to get SuccessMessage  for given error code from error messages properties file.
	 * @param errorCode
	 * @return String
	 * @author prem
	 */
	
	public String getSuccessMessage(String messagecode)
	{
		String successmessage="";
		try
		{
			ProjectConfig prop=new ProjectConfig("successmessages.properties");
	        Properties properties = prop.getProjectProperties();
//	        System.out.println("properties : "+properties);
	        successmessage=properties.getProperty(messagecode);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		finally {
					
		}
		return successmessage;
	}
	
	/**
	 * Method to get success response object.
	 * @param messagecode
	 * @return success response object.
	 */
	public Object getErrorResponse(String messagecode)
	{
		String message="";
		message = getSuccessMessage(messagecode);
    	ErrorMessages errorMessages = new ErrorMessages();
    	errorMessages.setErrorcode(messagecode);
    	errorMessages.setErrormessage(message);
    	System.out.println("get error message: "+errorMessages);
    	return errorMessages;
	}
	
}
