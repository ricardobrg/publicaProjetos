package com.publica.grupo1.util.validation.number;

/***
 * 
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 */
public class CPFValidation {

	private static final String CPF_REGEX= "[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}";

	private static boolean forSum(String cpf) {

		int sum = 0;
		try {
			for(int i = cpf.length()-1; i > 0; i--) {
				int num = Integer.parseInt(cpf.substring(cpf.length()-i-1,cpf.length()-i))*(i+1);
				sum += num;
			}
		}
		catch(NumberFormatException e) {
			return false;
		}
		sum %= 11;
		sum = 11-sum;
		if (sum >= 10) sum = 0;
		if(sum == Integer.parseInt(cpf.substring(cpf.length()-1))) return true;
		
		return false;

	}

	/***
	 * Method to make CPF validation digits
	 * With the last two numbers
	 * 
	 * @param cpf String
	 * @return boolean
	 */
	public static boolean cpfValidation(String cpf) {

		if(!validateCpfFormat(cpf)) 
			return false;

		for(int i = 1; i < cpf.length(); i++) 
			if(cpf.charAt(i) != cpf.charAt(0)) 
				if(forSum(cpf.substring(0,(cpf.length()-1))))
					if (forSum(cpf)) 
						return true;
		
		return false;
	}

	public static boolean validateCpfFormat(String cpf) {
		return cpf.matches(CPF_REGEX);
	}


}
