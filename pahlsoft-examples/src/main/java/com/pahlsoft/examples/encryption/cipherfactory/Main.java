package com.pahlsoft.examples.encryption.cipherfactory;

import java.security.KeyPair;
import java.security.PrivateKey;


import com.pahlsoft.examples.encryption.signfactory.ICEKeyStore;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try { 
			// Generate a temporary key. In practice, you would save this key. 
			// See also Encrypting with DES Using a Pass Phrase.
			ICEKeyStore iks = new ICEKeyStore();
			KeyPair kp = iks.getKeyPair();
			PrivateKey key = kp.getPrivate();
			System.out.println("DEBUG: Got Public Key " + key.toString());
			
			//SecretKey key = KeyGenerator.getInstance("DES").generateKey(); 
			// Create encrypter/decrypter class
			DesEncrypter encrypter = new DesEncrypter(key); 
			// Encrypt 
			String encrypted = encrypter.encrypt("Don't tell anybody!"); 
			// Decrypt 
			String decrypted = encrypter.decrypt(encrypted);  
			System.out.println("DEBUG: " + decrypted);
	     } catch (Exception e) {
	     } 
			
	}
		
 }
