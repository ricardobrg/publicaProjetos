package com.publica.grupo1.util;

/***
 * Contains utility methods for numbers.
 * 
 * @version 0.0.1
 * 
 * @author Pedro Vinicius Hostert
 */
public class UtilsNumber {

	/***
	 * Returns given String containing only the digits.
	 * 
	 * Don't accepts negative numbers.
	 * 
	 * @param str
	 * @return String with only digits.
	 */

	public static String onlyNumbers(String str) {
		return str.replaceAll("\\D", "");
	}

	private static final String NUMBER_REGEX = "-?[0-9]{1,}";

	/***
	 * Returns <code>true</code> if given String matches the NUMBER_REGEX: optional
	 * hyphen(minus sign), only numbers.
	 * 
	 * @param number
	 * @return <code> true or false </code>
	 */
	public static boolean isNumber(String number) {
		return number.matches(NUMBER_REGEX);
	}

	/***
	 * Returns <code>true</code> if given number is no grater than max, and no lower
	 * than min.
	 * 
	 * @param number
	 * @param min
	 * @param max
	 * @return <code>true or false</code>
	 */
	public static boolean isNumberInBounds(double number, double min, double max) {
		return number >= min && number <= max;
	}

	/***
	 * Returns given String containing only the digits.
	 * 
	 * Don't accepts negative numbers.
	 * 
	 * @param str
	 * @return String with only digits.
	 */
	public static String onlyDigits(String str) {
		return str.replaceAll("\\D", "");
	}
}
