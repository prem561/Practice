/****************************************************************************
*   Copyright (c)2016 CLIC. All rights reserved.
*
*
*   THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF CLIC.
*
*   The copyright notice above does not evidence any actual or intended
*   publication of such source code.
	
*	MODULE      : AdServerProperties.java
*
*   PURPOSE     : This class is using to hold database connection details for connecting different server based on application selection.
*
*   DESCRIPTION : Holder.
*
*  @author CLIC
 * @version 1.0

*****************************************************************************/



package com.clic.config;


import java.util.Properties;

public class ProjectConfig
{
    static final long serialVersionUID = 101L;
    String fileName = "";
    
    
    public ProjectConfig(String fileName) {
    	this.fileName = fileName;
    }
 //To get the property values from properties file
    public Properties getProjectProperties() {
//    	logger.info("PROPERTY FILE PATH :::: /com/healtheverAPI/rest/config/"+fileName);
//    	System.out.println("PROPERTY FILE PATH :::: /config/"+fileName);
        java.io.InputStream inputstream = getClass().getResourceAsStream("/com/clic/config/"+fileName);
//        System.out.println("PROPERTY FILE FULL PATH :::: /config/"+fileName);
        Properties properties = new Properties();
        try {
        	//loading the properties file
            properties.load(inputstream);
//            logger.info("PROPERTY SIZE ::::: "+properties.size());
//            System.out.println("PROPERTY SIZE ::::: "+properties.size());
            return properties;
        }
        catch(Exception e) {
        	//logger.info(ws.utility.Utility.getStackTrace(e));            
        }
        return null;
    }
}
