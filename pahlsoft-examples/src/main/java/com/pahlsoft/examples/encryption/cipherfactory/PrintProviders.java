package com.pahlsoft.examples.encryption.cipherfactory;

import java.util.Iterator;
import java.security.Security;
import java.security.Provider;

public class PrintProviders {
	public static void main(String[] args) {
		Provider[] providers = Security.getProviders();
		for (int i = 0; i < providers.length; i++) {
			String name = providers[i].getName();
			String info = providers[i].getInfo();
			double version = providers[i].getVersion();
			System.out.println("-------------------------------------");
			System.out.println("name: " + name);
			System.out.println("info: " + info);
			System.out.println("version: " + version);

			for (Iterator<?> iter = providers[i].keySet().iterator(); iter
					.hasNext();) {
				String key = (String) iter.next();
				System.out.println("\t" + key + "\t"
						+ providers[i].getProperty(key));
			}

			System.out.println("-------------------------------------");

		}
	}
}
