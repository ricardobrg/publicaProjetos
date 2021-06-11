package utils.validations.number;

/***
 * This class contains methods to validate a CEP String.
 * 
 * @version 0.1.1
 * @author Pedro Vinicius Hostert <pedrohostertt@gmail.com>
 */
public class CEPValidation {

	/***
	 * Method that validates CEP based on a String regex.
	 * Hyphen is optional, but the String must have only 8 digits.
	 * 
	 * @return <code>true or false</code>
	 */
	public static boolean isCepValid(String cep) {		
		return cep.matches("[0-9]{5}-?[0-9]{3}");
	}
}
