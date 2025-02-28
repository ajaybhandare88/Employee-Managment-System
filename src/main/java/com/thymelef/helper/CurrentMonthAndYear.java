package com.thymelef.helper;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentMonthAndYear {
	
	public static LocalDateTime localDateAndTime()
	{
		LocalDateTime currentDateTime = LocalDateTime.now();
		return currentDateTime;
	}
	public static Date sqlDate()
	{
	java.util.Date utilDate = new java.util.Date();
	java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	return sqlDate;
	}
	public static Date getCurrentFormatedDate()
	{
		Date sqlDate = null;
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = currentDateTime.format(formatter);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date parsedDate = dateFormat.parse(formattedDate);
			sqlDate = new Date(parsedDate.getTime());
			return sqlDate;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sqlDate;
	}
	
	public static DateTimeFormatter currentYear()
	{
		DateTimeFormatter year = DateTimeFormatter.ofPattern("yyyy");
		return year;
	}
	
	public static DateTimeFormatter currentMonth()
	{
		DateTimeFormatter month = DateTimeFormatter.ofPattern("MM");
		return month;
	}
	
	public static Date formatedCurrentDMY()
	{
		LocalDateTime currentDate=localDateAndTime();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String currentDMY=currentDate.format(formatter);
		Date d=Date.valueOf(currentDMY);
		return d;
	}
	
	public static Date formatedDateDDmmYYYY()
	{
		LocalDateTime currentDate=localDateAndTime();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String currentDMY=currentDate.format(formatter);
		Date d=Date.valueOf(currentDMY);
		return d;
	}
	
	public static DateTimeFormatter timeFormatter()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		return formatter;
	}
	
}
