package com.pahlsoft.examples.string;

public class TestString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Cleaning up any UTF-8 information before putting it into an XML parser
		String xml = "<?xml ...";
		xml = xml.trim().replaceFirst("^([\\W]+)<","<");
		
		System.out.println("DEBUG: " + xml.toString());

	}

}
