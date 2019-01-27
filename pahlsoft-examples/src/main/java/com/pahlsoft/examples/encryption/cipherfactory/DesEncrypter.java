package com.pahlsoft.examples.encryption.cipherfactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.PrivateKey;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

import java.security.NoSuchAlgorithmException;


public class DesEncrypter {
	Cipher ecipher;
	Cipher dcipher;
	
	DesEncrypter(PrivateKey key) {
		try {
			ecipher = Cipher.getInstance("SunRsaSign");
			dcipher = Cipher.getInstance("SunRsaSign");
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			dcipher.init(Cipher.DECRYPT_MODE, key);
			
		} catch ( NoSuchPaddingException e) {
			e.printStackTrace();
		} catch ( NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch ( InvalidKeyException e ) {
			e.printStackTrace();
        }
	}
	
	public String encrypt (String str) {
		try {
			// Encode the strign into bytes using utf-8
			byte[] utf8 = str.getBytes("UTF8");
			
			// Encrypt 
			byte [] enc = ecipher.doFinal(utf8);
			
			// Encode bytes to base64 to get a string
			return new BASE64Encoder().encode(enc);
		} catch ( BadPaddingException e) {
			e.printStackTrace();
		} catch ( IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch ( UnsupportedEncodingException e ) {
			e.printStackTrace();
		}
	  return null;
	}
	
	public String decrypt(String str) {
		try {
		// Decode base64 to get bytes
		byte[] dec = new BASE64Decoder().decodeBuffer(str);
		
		// Decrypt
		byte [] utf8 = dcipher.doFinal(dec);
		
		//Decode using utf-8
		return new String(utf8,"UTF8");
		
		} catch ( BadPaddingException e) {
			e.printStackTrace();
		} catch ( IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch ( UnsupportedEncodingException e ) {
			e.printStackTrace();
		} catch ( IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
