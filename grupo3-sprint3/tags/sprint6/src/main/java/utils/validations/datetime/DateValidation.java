package utils.validations.datetime;

import java.time.Year;

/**
 * This class contains methods to validate Dates. * 
 * 
 * @version 1.0.1
 * 
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com> *
 */
public class DateValidation {
	/***
	 * Static Method for date validation
	 * 
	 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
	 * @param date String: Date that will be validated
	 * @return boolean: Result of the validation
	 */
	public static boolean isDateValid(String date) {
		// DATE PATTERN: XX/XX/XXXX

		if (!date.matches("[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}")) {
			return false;
		}

		int day = Integer.parseInt(date.substring(0, 2));
		int month = Integer.parseInt(date.substring(3, 5));
		int year = Integer.parseInt(date.substring(6, 10));

		if (month == 2) {
			if ((Year.isLeap(year) && day > 29) || (day > 28)) {
				return false;
			}
		} else if ((month < 8 && month % 2 == 0) && (day > 30)) {
			return false;
		} else if ((month >= 8 && month % 2 != 0) && (day > 30)) {
			return false;
		}

		return (date.matches("^\\s*(3[01]|[12][0-9]|0?[1-9])\\/(1[012]|0?[1-9])\\/((?:19|20)\\d{2})\\s*$"));
	}
}









