package com.pahlsoft.generators;

import java.util.UUID;

public class UuidMaker {
	public static void main (String [] args) {
		for (int x = 0; x < 25; x++) {
			String uuid = UUID.randomUUID().toString();
			System.out.println(uuid);
		}
		
	}
}
