package com.pahlsoft.examples.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeMath {
	public static void main(String[] args) {
	
		
	Date start = new Date();
	Date end = new Date();
	
	DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
	formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
	System.out.println("Duration: " + formatter.format(end.getTime() - start.getTime()));
	}
}
