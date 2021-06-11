package com.publica.grupo1.util.validation.text;

/***
 * 
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 */

public class EmailValidation {
	
	private static final String EMAIL_REGEX = 
			"[a-zA-Z0-9.]{1,15}+@+[a-zA-Z]{2,15}+.+[a-zA-Z]{2,4}";

	/***
	 
	 * Method EmailValidation
	 * 
	 * Method to validate email expression
	 * 
	 * @param String email
	 * @return boolean true if email is valid
	 */
	
	public static boolean emailValidation(String texto) {
		return texto.matches(EMAIL_REGEX);
	}	
}
