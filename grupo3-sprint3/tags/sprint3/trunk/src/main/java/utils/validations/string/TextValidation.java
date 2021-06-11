package utils.validations.string;

/***
 * This class contains methods to validate plain text.
 * 
 * @version 0.1.1
 * 
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 */
public class TextValidation {
	/***
	 * Method for checking if a set of characters is valid.
	 * 
	 * @param texto : String 
	 * @return  :boolean - True if does not exist special characters
	 */
	public static boolean isCharactersValid(String texto) {
		final int NUM_MIN = 1;
		final int NUM_MAX = 50;	
		return texto.matches("[a-zA-Z]{" + NUM_MIN + "," + NUM_MAX + "}");
	}
}
