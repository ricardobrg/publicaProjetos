package utils.validations.number;

import utils.UtilsNumber;

/***
 * This class contains the logic to validate given CPF number
 * (in String).
 * 
 * @version 0.1.1
 * 
 * @author Pedro Vinicius Hostert <pedrohostertt@gmail.com>
 */
public class CPFValidation {
	/**
	 * 
	 * Static Method for CPF validation.
	 * 
	 * @param cpf : String
	 * @return : Boolean
	 * 
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
	 */
	public static boolean isCpfValid(String cpf) {
		if(!cpf.matches("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}"))
			return false;
		
		cpf = UtilsNumber.onlyNumbers(cpf);
			
		if (!cpf.matches("[0-9]{11}"))
			return false;

		if (cpf.matches("^([0-9])\\1*$"))
			return false;

		int sum, res;
		String[] dig = cpf.split("");

		sum = 0;
		for (int i = 0, j = 10; i < 9; i++, j--) {
			sum += Integer.parseInt(dig[i]) * j;
		}
		res = sum * 10 % 11;
		if (res != Integer.parseInt(dig[9]))
			return false;

		sum = 0;
		for (int i = 0, j = 11; i < 10; i++, j--) {
			sum += Integer.parseInt(dig[i]) * j;
		}
		res = sum * 10 % 11;
		if (res != Integer.parseInt(dig[10]))
			return false;

		return true;
	}

}
