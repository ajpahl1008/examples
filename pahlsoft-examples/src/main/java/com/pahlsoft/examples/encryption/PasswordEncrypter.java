package com.pahlsoft.examples.encryption;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.StringTokenizer;



public class PasswordEncrypter 
{
	private static String QUOTE = "\'";
	private static String COMMA = ",";
	
    public static void main( String[] args ) throws IOException {
    	Boolean encryptFromFile = false;
    	Boolean useQaPassword = false;
    	Boolean debugMode = false;
    	String fileToProcess = "";
    	int counter = 6;
    	String line = "";
    	File outputFile = null;
    	if (args.length == 0) {
    		System.out.println("ERROR: Proper Usage: java -jar PasswordEncrypter.jar \"Some plain text\" [-file=] [-qa] [-v]");
    		System.exit(0);
    	} else {
    		for (String watcherArgs : args) {
    			if (watcherArgs.contains("-file=")) {
    				encryptFromFile = true;
					StringTokenizer stokARGDBG = new StringTokenizer(watcherArgs, "=");
					@SuppressWarnings("unused")
					String stARGFILE = stokARGDBG.nextToken();
					 fileToProcess = stokARGDBG.nextToken();
					System.out.println("Processing File: " + fileToProcess);
				} 
    		
    			if (watcherArgs.contains("-qa")) {
    				outputFile = new File("ACCL_ENTITLEMENTS_FOR_QA.sql");
    				useQaPassword = true;
    			} else {
    				outputFile = new File("ACCL_ENTITLEMENTS_FOR_PROD.sql");				
    			}
    			
    			if (watcherArgs.contains("-v")) {
    				debugMode = true;
    			}
    			
    		}
    		
    	}
    	
    	if (encryptFromFile) {
    	    System.out.println("Processing file " + outputFile.getName());
    		InputStream fis = new FileInputStream(new File(fileToProcess));
    		OutputStream fos = new FileOutputStream(outputFile);
    		BufferedReader input = new BufferedReader(new InputStreamReader(fis));
    	        
    	        while ((line = input.readLine()) != null) {
    	        	StringTokenizer stokIRM = new StringTokenizer(line, ",");
    	        	String daId = stokIRM.nextToken();
    	        	String brand = stokIRM.nextToken();
    	        	@SuppressWarnings("unused")
					String file = stokIRM.nextToken();
    	        	String archive = stokIRM.nextToken();
    	        	String qaPassword = stokIRM.nextToken();
    	        	String prodPassword = stokIRM.nextToken();
    	        	//String filter = stokIRM.nextToken();
    	        String encryptPassword = "";
    	        if (useQaPassword) {
    	        	encryptPassword = qaPassword;
    	        } else {
    	        	encryptPassword = prodPassword;
    	        }
    	        String dataString = Integer.toString(counter) + COMMA +  
    	        				   QUOTE + daId + QUOTE + COMMA +
    	                           QUOTE + runEncrypt(encryptPassword) + QUOTE + COMMA +
    	                           "1" + COMMA + 
    	                           "0" + COMMA +  
    	                           QUOTE + QUOTE + COMMA + 
    	                           QUOTE + archive + QUOTE + COMMA + 
    	                           "1" + COMMA + 
    	                           QUOTE + brand + QUOTE + COMMA +
    	                           "1";

    	        dataString = "INSERT INTO LOCAL_CONFIG.ACCL_ENTITLEMENTS (ENTITLEMENT_ID, CLIENT_APPLICATION_ID, CLIENT_APPLICATION_CREDENTIAL, LOB_ID, ENABLED, ENTITLEMENTS, LEGACY_ENTITLEMENTS, FILTER_ID, ENTITLEMENTS_DESCR, NORMALIZED_FIELDS) VALUES (" + dataString;
    	        dataString = dataString + ");\n";
    	        if (debugMode) {
    	        	System.out.println(dataString);
    	        }

    	        fos.write(dataString.getBytes());
 	        	counter++;
    	        
    	        }
    	   fis.close();
    	   fos.close();
    	
    	} else {
    		String newPassword = runEncrypt(args[0]);
    		if (newPassword != null) {
    			System.out.println("Plain Text: " + args[0]);
    			System.out.println(" Encrypted: " + newPassword);
    			System.out.println("Make sure you save them somewhere safe!!!!");
    		}
    	}
  
	}
    
    public static String runEncrypt(String password) {
    	PasswordEngine pe = new PasswordEngine();
    	String encryptedPw = pe.encrypt(password);
    	/*
    	 * done to make sure the password salt is static
    	 * and that the password never never changes on us
    	 * for the given string
    	 */
		PasswordEngine pe2 = new PasswordEngine();
		if (pe2.validate(password, encryptedPw)){
			return encryptedPw;
		} else {
			return null;
		}
	}

}
