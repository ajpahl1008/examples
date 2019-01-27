package com.pahlsoft.examples.enumerators;

public class EnumTest {
	public static void main(String[] args) {
		System.out.println(Keys.AccountNumber.key);
		System.out.println(Keys.CustomerFName.key+ " " +Keys.CustomerLName.key);
		System.out.println(Keys.CustomerLName.key+ "," +Keys.CustomerFName.key);
		System.out.println(Keys.SequenceNumber.key);

		
	}
	
	
	public static enum Keys {
	    SequenceNumber ("0000001"),
	    AccountNumber ("123456789"),
	    CustomerFName ("Tony"),
	    CustomerLName ("Pahl");

	    public final String key;
	    Keys(String k) { key = k; }
	}
}
	
