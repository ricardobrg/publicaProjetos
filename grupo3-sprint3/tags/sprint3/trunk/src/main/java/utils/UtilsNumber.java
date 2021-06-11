package utils;

/***
 * This class contains utility methods related to numbers.
 * 
 * @version 1.1.2
 * 
 * @author Pedro Vinicius Hostert <pedrohostertt@gmail.com>
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
	
	public static boolean isOnlyNumbers(String str) {
		return str.matches("-?[0-9]{1,}");
	}
	
	public static boolean isValidNumber(double number, double min, double max) {
		return number >= min && number <= max;
	}	
}
