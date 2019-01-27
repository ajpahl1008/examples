package com.pahlsoft.examples.encryption.signfactory;

import java.io.Serializable;
import java.security.SignedObject;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		// Sign TestClass Object
		Serializable o = new TestClass();
		SignedObject so = ClassSigner.run(o, new ICEKeyStore().getKeyPair());
		
		Object tester = so.getObject();
		TestClass runMe = (TestClass) tester;
		runMe.calculate();
		//TestClass runMe = tester.
		
	}
	
}
