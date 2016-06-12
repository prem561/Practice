package com.clic.Utility;

public class Utility {
	
		public static int generatePIN() 
	   {
	        //generate a 4 digit integer 1000 <10000
	        int randomPIN = (int)(Math.random()*9000)+1000;
	        return randomPIN;
	   }


}
