package com.pahlsoft.examples.encryption;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

public class PasswordEngine {
	
		private String encryptedPassword;
		
		public String encrypt(String inputPassword) {
			ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
			
			passwordEncryptor.setAlgorithm("SHA-512");
			passwordEncryptor.setPlainDigest(true);
			encryptedPassword = passwordEncryptor.encryptPassword(inputPassword);
			
			return encryptedPassword;
		}
	
		public Boolean validate(String inputPassword, String encryptedString) {
			
			//System.out.println("Validating Encrypted String: " + encryptedString);
			
			ConfigurablePasswordEncryptor passwordDecryptor = new ConfigurablePasswordEncryptor();
			passwordDecryptor.setAlgorithm("SHA-512");
			passwordDecryptor.setPlainDigest(true);
			
			if(passwordDecryptor.checkPassword(inputPassword, encryptedString)) {
				//System.out.println("Input Password: " + inputPassword + " valid");
				return true;
			} else {
				//System.out.println("Input Password: " + inputPassword + " invalid");
				return false;
			}
		}

		public String getEncryptedPassword() {
			return encryptedPassword;
		}

		public void setEncryptedPassword(String encryptedPassword) {
			this.encryptedPassword = encryptedPassword;
		}
	
		
}
