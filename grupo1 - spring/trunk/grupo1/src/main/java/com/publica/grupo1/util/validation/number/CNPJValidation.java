package com.publica.grupo1.util.validation.number;

/***
 * 
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 * @author Jonathas Rocha de Souza <jonathasrochadesouza@gmail.com>
 */

public class CNPJValidation {
	private static final String CNPJ_REGEX= 
			"[0-9]{2}\\.?[0-9]{3}\\.?[0-9]{3}\\/?[0-9]{4}\\-?[0-9]{2}"; //TODO: new Regex for CNPJ validation

	private static boolean forSum(String cnpj, boolean first) {

		int sum = 0, temp = 5, mult = 0;
		if(!first) temp=6;
		
		try {
			for(int i = 0; i < cnpj.length()-1; i++) {
				if(temp == 1) temp = 9;
		
				mult = Integer.parseInt(cnpj.substring(i,i+1)) * temp;
				sum += mult;
				temp--;
			}
		}
		catch(NumberFormatException e) {
			return false;
		}
		sum %= 11;
		sum = 11-sum;
		if (sum <= 1) sum = 0;

		if(sum == Integer.parseInt(cnpj.substring(cnpj.length()-1))) return true;
		
		return false;
	}

	/***
	 * Method to make CNPJ validation digits
	 * With the last two numbers
	 * 
	 * @param cnpj String
	 * @return boolean
	 */
	public static boolean cnpjValidation(String cnpj) {

		if(!validateCnpjFormat(cnpj)) 
			return false;

		for(int i = 1; i < cnpj.length(); i++)
			if(cnpj.charAt(i) != cnpj.charAt(0))
				if(forSum(cnpj.substring(0, cnpj.length() - 1),true))
					return forSum(cnpj, false);

		return false;
	}

	public static boolean validateCnpjFormat(String cnpj) {
		return cnpj.matches(CNPJ_REGEX);
	}


}
