package com.pahlsoft.examples.encryption.signfactory;

import java.io.Serializable;

	public class TestClass implements Serializable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -2498199429369603918L;
		//private static final long serialVersionUID = -7862151121445599788L;
		int One = 1;
		int Five = 5;
		
		public void calculate() {
			int total = One + Five;
			System.out.println("Calculated: " + total);
		}
	}

