package com.publica.grupo1.util.validation.number;

/***
 * Class responsible for verify cep formatation
 * 
 * The method recives one CEP, and use regex to
 * validate the formatation
 * 
 * @version 0.0.1
 * @author Diego Borba <diegoborba25@gmail.com>
 */
public class CepValidator {
	
	private static final String CEP_REGEX = "^[0-9]{5}\\-?[0-9]{3}";
	/***
	 * Verify if the inputed CEP follow the national standard
	 * @author Diego Borba <diegoborba25@gmail.com>
	 * @param String cep
	 * @return true/false(valid/invalid)
	 */
	public static boolean validateCep(String cep) {
		return cep.matches(CEP_REGEX);
	}
}
