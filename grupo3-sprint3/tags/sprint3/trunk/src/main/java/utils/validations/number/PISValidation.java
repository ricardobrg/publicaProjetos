package utils.validations.number;

/***
 * This class contains the logic to calculate if given
 * PIS is valid.
 * 
 * @version 0.1.1
 * 
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com> 
 */
public class PISValidation {
	/**
	 * Static Method for pis validation.
	 * 
	 * @param pis : String
	 * @return  : Boolean
	 * 
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
	 * 
	 */
	public static boolean isPisValid(String pis) {
		if (pis == null || pis.length() != 11)
			return false;

		if (pis.matches("\\b(\\d+)\\1+\\b"))
			return false;

		int[] pesos = { 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
		char[] numerosPis = pis.toCharArray();
		int soma = 0;

		try {
			for (int i = 0; i < numerosPis.length - 1; i++) {
				soma += pesos[i] * Integer.parseInt(String.valueOf(numerosPis[i]));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		int verificador = 11 - (soma % 11);
		char digitoVerificador = Integer.toString(verificador).charAt(0);

		if (verificador == 11 || verificador == 10)
			return pis.charAt(10) == '0';

		return digitoVerificador == pis.charAt(10);

	}
}
