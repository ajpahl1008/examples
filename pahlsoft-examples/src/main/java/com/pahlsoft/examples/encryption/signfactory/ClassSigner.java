package com.pahlsoft.examples.encryption.signfactory;

import java.io.Serializable;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignedObject;


public class ClassSigner  {

	public static SignedObject run(Serializable o2, KeyPair pk) throws Exception {
		
		// Create a public and private key 
		PublicKey publicKey = null; 
		PrivateKey privateKey = null;

		privateKey = pk.getPrivate();
		publicKey = pk.getPublic();
		
		// return SignedObject
		SignedObject so = null;
		Serializable o = o2; 
		
		Signature sig = Signature.getInstance(privateKey.getAlgorithm());
				
		so = new SignedObject(o, privateKey, sig);
		
		// Validate Object is signed
		Boolean checkSigned = so.verify(publicKey, sig);
		if (checkSigned) {
			System.out.println("DEBUG: Test is Signed " + publicKey.toString());
		}
		
		return so;

    }
}

	