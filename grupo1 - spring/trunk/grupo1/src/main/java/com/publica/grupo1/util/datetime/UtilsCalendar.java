package com.publica.grupo1.util.datetime;

import java.util.Calendar;
/***
 * Class responsible for work with 
 * dates variables of some types.
 * 
 *
 * This class have one overload(toString) which
 * converts Calendar var or Int var to formated String.
 * Also  have one toMinutes method wich convert schedule, to
 * minute and put it in one int var.
 * Also  have one toCalendar method, which
 * transform String date(dd/mm/yy) in 
 * Calendar type var
 * 
 * @deprecated
 * @version 0.0.1
 * @author Diego Borba <diegoborba25@gmail.com>
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 */
public class UtilsCalendar {

	/***
	 * Recives one calendar type var, and 
	 * return formated date in String type
	 * 
	 * @deprecated
	 * @param calendar
	 * @param withYear
	 * @return formated (dd/mm/yy) or (dd//mm)
	 */
	public static String toString(Calendar calendar, boolean withYear){
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		
		if(withYear) 
			return String.format("%02d/%02d/%02d",day,month,year);

		return String.format("%02d/%02d",day,month); 
	}

	/***
	 * Recives one int type var, and 
	 * return formated schedule in String type
	 * 
	 * @deprecated
	 * @param minutes
	 * @return formated (hh:mm)
	 */
	public static String toString(int input){
		int minutes = input % 60;
		int hours = (input - minutes)/60;
		return String.format("%02d:%02d", hours, minutes);
	}

	/***
	 * Recives one String type var, and 
	 * return all minutes of the schedule
	 * into a int type var
	 * 
	 * @param schedule (hh:mm)
	 * @return minutes
	 */
	public static int toMinutes(String input){
		return Integer.parseInt(input.substring(0,2)) * 60 + 
				Integer.parseInt(input.substring(3,5));
	}

	/***
	 * Recives one String type var, and 
	 * return an Calendar type var using
	 * the inputed informations
	 * 
	 * @deprecated
	 * @param date(dd/mm/yy)
	 * @return calendar
	 */
	public static Calendar toCalendar(String date){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date.substring(0,2)));
		calendar.set(Calendar.MONTH, Integer.parseInt(date.substring(3,5))-1);
		calendar.set(Calendar.YEAR, Integer.parseInt(date.substring(6,10)));
		//25061982
		return calendar;
	}
}
