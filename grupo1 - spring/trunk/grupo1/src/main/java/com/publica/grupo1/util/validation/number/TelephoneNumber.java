package com.publica.grupo1.util.validation.number;

/***
 * Contains method for telephone number validation.
 * 
 * @version 0.0.1
 * 
 * @author Pedro Vinicius Hostert <pedrohostertt@gmail.com>
 *
 */
public class TelephoneNumber {
	
	private static final String TELEPHONE_REGEX = "\\(?[1-9]{2}\\)?\\s?9?\\s?\\d{4}-?\\d{4}";
	/***
	 * Validates telephone number based on Regex, with optional
	 * parentheses, number 9 prefix, and obligatory DDD and 8 numbers.
	 * 
	 * @param telephone
	 * @return <code> true or false</code>
	 */
	public static boolean validateTelephone(String telephone) {
		return telephone.matches(TELEPHONE_REGEX);
	}
}
