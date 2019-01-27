package com.pahlsoft.examples.threads;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class ProcessController {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		BufferedReader input = null;
		Process p = Runtime.getRuntime().exec("cmd");
        input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        input.close();
        
        if (input != null) {
        	System.out.println("p: Valid Command");
        } 
        
        Process r = Runtime.getRuntime().exec("cmd");
        input = new BufferedReader(new InputStreamReader(r.getInputStream()));
        
        if (input != null) {
        	System.out.println("r: Valid Command");
        } 

	}

}
