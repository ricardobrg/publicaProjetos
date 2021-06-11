package com.publica.grupo1.util.datetime;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Utilities with LocalDate class
 * 
 * @author Diego Leon
 *
 */
public class DateUtil {
	
	/**
	 * 
	 * @param earlierDate
	 * @param laterDate
	 * @return difference in minutes between the param dates
	 * 
	 * @author Diego Leon
	 */
	public static double getDifferenceInHoursWithMinutesBetweenDates(LocalDateTime earlierDate, LocalDateTime laterDate) {
		return ChronoUnit.MINUTES.between(earlierDate, laterDate) / 60;
	}
}
