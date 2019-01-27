package com.pahlsoft.examples.math;

import java.security.MessageDigest;


public class CheckSum {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
	   String testString1 = "This is a Test Message";
	   String testString2 = "This is a Test Secondly Message";
	   byte [] bsTest1 = testString1.getBytes();
	   byte [] bsTest2 = testString2.getBytes();
	   MessageDigest md = MessageDigest.getInstance("MD5");
	   
	   byte[] checksum1 = md.digest(bsTest1);
	   byte[] checksum2 = md.digest(bsTest2);
	   
	   String mdString1 = new String(checksum1);
	   String mdString2 = new String(checksum2);
	   
	   System.out.println("DEBUG: Length of 1 is:  " + checksum1.length);
	   System.out.println("DEBUG: Content of 1 is: " + mdString1);
	   
	   System.out.println("DEBUG: Length of 2 is:  " + checksum2.length);
	   System.out.println("DEBUG: Content of 2 is: " + mdString2);
	}

}
