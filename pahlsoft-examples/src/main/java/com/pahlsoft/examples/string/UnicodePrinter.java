package com.pahlsoft.examples.string;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;


public class UnicodePrinter {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {

		
		PrintStream sysout = new PrintStream(System.out, true, "UTF-8");
		sysout.println("\u0039");
		
		System.out.println("\u0039");
		
	}

}
