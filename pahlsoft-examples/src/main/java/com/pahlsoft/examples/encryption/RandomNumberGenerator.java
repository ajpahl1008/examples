package com.pahlsoft.examples.encryption;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class RandomNumberGenerator {

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
		String randomNum = new Integer( prng.nextInt()).toString();
		
		System.out.println("Random Number: " + randomNum);

	}

}
