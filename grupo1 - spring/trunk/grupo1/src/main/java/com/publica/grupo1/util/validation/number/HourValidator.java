package com.publica.grupo1.util.validation.number;

/***
 * Class responsible for validate the hour format
 *
 * Reciver an hour(String) and validate it
 * using one regex
 * 
 * @version 0.0.1
 * @author Diego Borba <diegoborba25@gmail.com>
 */
public class HourValidator {
	/***
	 *
	 * Validates inputed time formatation
	 * @author Diego Borba <diegoborba25@gmail.com>
	 * @param String inputedTime
	 * @return true or false
	 *
	 */
	public static boolean validateHour(String inputedTime) {
		return inputedTime.matches("^([0-1]?[0-9]|2[0-3]):?[0-5][0-9]$");
	}
}












