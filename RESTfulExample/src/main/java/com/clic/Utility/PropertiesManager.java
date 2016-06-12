package com.clic.Utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.HashMap;

public class PropertiesManager {
	
	private static HashMap<String, Properties> propertiesManager = new HashMap<String, Properties>();
	
	private static synchronized Properties loadProperty(String propertyFile) {
		Properties property = null;
		if (!propertiesManager.containsKey(propertyFile)) {

			InputStream in = PropertiesManager.class.getResourceAsStream(propertyFile);
			
			if (in != null){			
				property = new Properties();
				try {
					property.load(in);
					propertiesManager.put(propertyFile, property);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
			}
		} else {
			property = propertiesManager.get(propertyFile);
		}
		return property;
	}
	
	public static String getProperty (String propertyFile, String propertyKey) {
		Properties property = null;
		String propertyValue = null;
		
		if (propertiesManager.containsKey(propertyFile)) {
			//System.out.println("::Property file already existed, getting the value::");
			property = propertiesManager.get(propertyFile);
		} else {
			//System.out.println("::Need to load the property file to the hashmap::");
			property = loadProperty(propertyFile);
		}
		//System.out.println("::property:: "+property);
		if (property != null) {
			propertyValue = property.getProperty(propertyKey);
		}
		return propertyValue;
	}	
}
