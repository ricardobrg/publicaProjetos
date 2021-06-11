package utils.validations.number;

/***
 * This class contains the logic to calculate if given CNPJ
 * number(in String) is valid.
 * 
 * @version 0.1.1
 * 
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 */
public class CNPJValidation {
	/**
	 * Static Method for CNPJ validation.
	 * 
	 * @param cnpj : String 
	 * @return : Boolean
	 * 
	 * @author Caio Shimada
	 */
	public static boolean isCnpjValid(String cnpj) {

		if (cnpj.contains(".") || cnpj.contains("/") || cnpj.contains("-")) {
			if (!cnpj.matches("[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}/[0-9]{4}-[0-9]{2}"))
				return false;
			cnpj = cnpj.replaceAll("[\\./-]", "");
		}

		if (!cnpj.matches("[0-9]{14}"))
			return false;

		int sum, res;
		String[] dig = cnpj.split("");

		sum = 0;
		for (int i = 0, j = 5; i < 12; i++, j--) {
			if (j == 1)
				j = 9;
			sum += Integer.parseInt(dig[i]) * j;
		}
		res = sum % 11;
		if (res > 2) {
			if (11 - res != Integer.parseInt(dig[12]))
				return false;
		} else {
			if (Integer.parseInt(dig[12]) != 0)
				return false;
		}

		sum = 0;
		for (int i = 0, j = 6; i < 13; i++, j--) {
			if (j == 1)
				j = 9;
			sum += Integer.parseInt(dig[i]) * j;
		}
		res = sum % 11;
		if (res > 2) {
			if (11 - res != Integer.parseInt(dig[13]))
				return false;
		} else {
			if (Integer.parseInt(dig[13]) != 0)
				return false;
		}

		return true;
	}
}
