package com.pahlsoft.utility;

import java.util.Properties;

public class LoaderChecker {

	/**
	 * @param args
	 */
	static Properties prop = System.getProperties();
	public static void main(String[] args) {

		prop.setProperty("java.class.path", getClassPath());
		System.out.println("java.class.path now = " + getClassPath());

	}
	
	static String getClassPath() {
		return prop.getProperty("java.class.path", null);
	}

}
