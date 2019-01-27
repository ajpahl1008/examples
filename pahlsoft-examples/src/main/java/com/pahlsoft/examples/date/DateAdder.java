package com.pahlsoft.examples.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateAdder {
	public static void main(String[] args) throws ParseException {

		String inputDate = "07/26/2011";
		Date anchorDate = new SimpleDateFormat("MM/dd/yyyy").parse(inputDate);
		String outputDate = new SimpleDateFormat("MMM-dd-yyyy").format(anchorDate);
		System.out.println(outputDate); 
		System.out.println("Today is: " + outputDate);

		Calendar cal = Calendar.getInstance();
		cal.setTime(anchorDate);
		cal.add(Calendar.DATE, Integer.parseInt(args[0])); //Add 45 Days (from JVM Args)

		anchorDate = cal.getTime();
		
		String expirationDate = new SimpleDateFormat("MM/dd/yyy").format(anchorDate).toString();
		
		System.out.println(args[0] + " days from now: " + expirationDate );
	}
}