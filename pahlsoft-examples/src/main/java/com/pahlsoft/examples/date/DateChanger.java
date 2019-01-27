package com.pahlsoft.examples.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateChanger {
	public static void main(String[] args) {

		// Changing from Standard Date to a YYYYmmdd format
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String simpleDate = sdf.format(new Date());
		System.out.println("Today is: " + simpleDate.toString());
		System.out.println("HEllo"); 

		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 90); // add 10 days

		date = cal.getTime();
		
		String expirationDate = sdf.format(date);
		
		System.out.println("90 days from now: " + expirationDate );
	}
}
