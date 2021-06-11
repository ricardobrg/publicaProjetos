package utils.validations.string;

/***
 * This class contains methods to validate email addresses, 
 * based on a String regex. 
 * 
 * @version 0.1.1
 * 
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
 */
public class EmailValidation {	
	/** 
	 * Static Method for E-mail validation.
	 * 
	 * @param email : String
	 * @return  : Boolean 
	 */
	public static boolean isEmailValid(String email) {
		return email.matches(
				"^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
	}
}
