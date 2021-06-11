package com.publica.grupo1.util.datetime;

import java.util.Calendar;

/**
 * 
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 */
public class HourSubtration {

	/***
	 * 
	 * Method hour subtration
	 * 
	 * Method to do hours subtration in class Calendar
	 * 
	 * @param first Calendar
	 * @param second Calendar
	 * @return int minutes
	 */
	public static int hourSubtration (Calendar first, Calendar second) {
		return ((first.get(Calendar.HOUR_OF_DAY)*60 - second.get(Calendar.HOUR_OF_DAY)*60) 
				+ (first.get(Calendar.MINUTE) - second.get(Calendar.MINUTE)));
	}
	
}
