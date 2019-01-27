package com.pahlsoft.examples.math;

public class DoMath {

	public static void main(String[] args) {
		double allocated = 125;
		double used = 74;
		
		double percentUsed; 
		percentUsed = ((used / allocated)*100);
		
		if (percentUsed > 60) {
		System.out.println("DEBUG: Percent Used: " + percentUsed);
		} else {
			System.out.println("DEBUG: OK");
		}
		
	}

}
