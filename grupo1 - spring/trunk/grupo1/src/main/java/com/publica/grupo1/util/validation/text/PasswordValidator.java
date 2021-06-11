package com.publica.grupo1.util.validation.text;

/***
 * Class responsible for verify the password format
 *
 * The method recives an password(String) and
 * verify it format using one regex.
 * 
 * @version 0.0.1
 * @author Diego Borba <diegoborba25@gmail.com>
 */
public class PasswordValidator {
	
	private static final String PASSWORD_REGEX = 
			"^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$";
	/***
	 * Verify the password size(must be bigger than 8 and smaller than 100),
	 * And the password format, must have 1 upper, 1 lower and 1 special character
	 * @author Diego Borba <diegoborba25@gmail.com>
	 * @param String password
	 * @return true/false(valid/invalid)
	 */
	public static boolean validatePassword(String password) {
		return password.matches(PASSWORD_REGEX); 
	}
}
