/****************************************************************************
 *   Copyright (c)2016 Clic. All rights reserved.
 *
 *   THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF CLIC.
 *
 *   The copyright notice above does not evidence any actual or intended
 *   publication of such source code.
 *****************************************************************************/
package com.clic.Dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * ConnectionHelper.java
 * Purpose: This class is responsible for creating the database connection.
 * @author CLIC
 * @version 1.0
 */
public class ConnectionHelper
{
	private static ConnectionHelper instance;
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String DB_URL="jdbc:mysql://localhost:3306/clic?user=root&password=";
	

	/**
	 * Method to set the database connection properties.
	 */
	private ConnectionHelper()
	{
    	try {
	
            Class.forName(driverName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to get the database connection.
	 */
	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			instance = new ConnectionHelper();
		}
		try {
			return DriverManager.getConnection(DB_URL);
		} catch (SQLException e) {
			System.out.println(e);
			throw e;
		}	
	}
	
	/**
	 * Method to close the database connection.
	 */
	public static void close(Connection connection)
	{
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}