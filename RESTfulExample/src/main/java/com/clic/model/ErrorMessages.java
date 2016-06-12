/****************************************************************************
 *   Copyright (c)2013 healtheverAPI. All rights reserved.
 *
 *   THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF healtheverAPI.
 *
 *   The copyright notice above does not evidence any actual or intended
 *   publication of such source code.
 *****************************************************************************/
package com.clic.model;

/**
 * ErrorMessages.java
 * Purpose: This class is responsible for maintaining the getter and setter method for Error Messages.
 * @author healtheverAPI
 * @version 1.0
 */
public class ErrorMessages {

	private String errorcode = "";
	private String errormessage = "";
	public String getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}
	public String getErrormessage() {
		return errormessage;
	}
	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}
	
	
	
}
