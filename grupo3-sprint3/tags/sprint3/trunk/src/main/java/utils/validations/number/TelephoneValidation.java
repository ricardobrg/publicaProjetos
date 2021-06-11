package utils.validations.number;

/***
 * 
 * This class contains a method that validates a telephone number
 * based on a String regex. The telephone number must contain
 * parenthesis 
 * 
 * @version 0.1.1
 * 
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com> 
 */
public class TelephoneValidation {
	/**
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
	 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
	 * 
	 * Static Method for phone validation.
	 * 
	 * PHONE PATTERN: (XX) {X}XXXX-XXXX
	 * @param phone : String
	 * @return : Boolean
	 */
	public static boolean isPhoneValid(String phone) {
		return phone.matches("\\(?[1-9]{2}\\)?\\s?9?\\s?\\d{4}-?\\d{4}");
	}
}
