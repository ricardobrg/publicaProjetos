package com.publica.grupo1.util.validation.text;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Locale;

/**
 * This class implements several input validations
 * 
 * @version 1.0.0
 * @author Caio Shimada <xcaiosr@gmail.com>
 *
 */
public class ValidatorClass {

	/**
	 * Verifies if cpf is valid.
	 * 
	 * Validates input size, format, digits and repetition.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * 
	 * @param cpf a string of the cpf to validate
	 * @return <code>true</code> if cpf is valid; <code>false</code> otherwise
	 * 
	 * 
	 */
	public static boolean isCpfValid(String cpf) {
		
		// TODO verify and refactor
		// quickfix return true
		
		
		if (cpf.contains(".") || cpf.contains("-")) {
			if (!cpf.matches("[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}"))
				return false;
			cpf = cpf.replaceAll("[\\.-]", "");
		}

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

	/**
	 * Verifies if cnpj is valid.
	 * 
	 * Validates input size, format, digits and repetition.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * 
	 * @param cnpj a string of the cnpj to validate
	 * @return <code>true</code> if cnpj is valid; <code>false</code> otherwise
	 */
	public static boolean isCnpjValid(String cnpj) {

		if (cnpj.contains(".") || cnpj.contains("/") || cnpj.contains("-")) {
			if (!cnpj.matches("[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}/[0-9]{4}-[0-9]{2}"))
				return false;
			cnpj = cnpj.replaceAll("[\\./-]", "");
		}

		if (!cnpj.matches("[0-9]{14}"))
			return false;

		if (cnpj.matches("^([0-9])\\1*$"))
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

	/**
	 * Verifies if cep is valid.
	 * 
	 * Validates input size and format.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * 
	 * @param cep a string of the cep to validate
	 * @return <code>true</code> if cep is valid; <code>false</code> otherwise
	 */
	public static boolean isCepValid(String cep) {

		if (cep.contains("-")) {
			if (!cep.matches("[0-9]{5}-[0-9]{3}"))
				return false;
			cep = cep.replaceAll("-", "");
		}

		if (!cep.matches("[0-9]{8}"))
			return false;

		return true;
	}

	/**
	 * Verifies if pis is valid.
	 * 
	 * Validates size, format and digits.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * 
	 * @param pis a string of a pis to validate
	 * @return <code>true</code> if pis is valid; <code>false</code> otherwise
	 */
	public static boolean isPisValid(String pis) {

		if (pis.contains(".") || pis.contains("-")) {
			if (!pis.matches("[0-9]{3}\\.[0-9]{5}\\.[0-9]{2}-[0-9]{1}"))
				return false;
			pis = pis.replaceAll("[\\.-]", "");
		}

		if (!pis.matches("[0-9]{11}"))
			return false;

		if (pis.matches("^([0-9])\\1*$"))
			return false;

		int sum, res;
		String[] dig = pis.split("");

		sum = 0;
		for (int i = 0, j = 3; i < 10; i++, j--) {
			if (j == 1)
				j = 9;
			sum += Integer.parseInt(dig[i]) * j;
		}
		res = 11 - (sum % 11);
		if (res >= 10) {
			if (Integer.parseInt(dig[10]) != 0)
				return false;
		}
		if (res != Integer.parseInt(dig[10]))
			return false;

		return true;
	}

	/**
	 * Verifies if telephone number is valid.
	 * 
	 * Validates telephone number by size, format, ddd and digit 9
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * 
	 * @param tel a string of a telephone number to validate
	 * @return <code>true</code> if telephone number is valid; <code>false</code>
	 *         otherwise
	 */
	public static boolean isTelValid(String tel) {
		if (tel.contains("(") && !tel.contains(")") || tel.contains(")") && !tel.contains("("))
			return false;
		return (tel.matches("\\(?[1-9][0-9]\\)? ?9?[0-9]{4}-?[0-9]{4}"));
	}

	/**
	 * Verifies if email is valid.
	 * 
	 * Validates format
	 * 
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * 
	 * @param email a string of the email to validate
	 * @return <code>true</code> if email is valid; <code>false</code> otherwise
	 */
	public static boolean isEmailValid(String email) {
		return email.matches(
				"^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
	}

	/**
	 * Verifies if input is of only numbers.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * 
	 * @param input a string of the input to check
	 * @return <code>true</code> if input is all numbers; <code>false</code>
	 *         otherwise
	 */
	public static boolean isAllNumber(String input) {
		return (input.matches("^[0-9]+$"));
	}

	/**
	 * Verifies if input is of only text.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * 
	 * @param input a string of the input to check
	 * @return <code>true</code> if input is all text; <code>false</code> otherwise
	 */
	public static boolean isAllText(String input) {
		return (input.matches("^[a-zA-Z�-��-� -]+$"));
	}

	/***
	 * Validates formatting of dates.
	 * 
	 * Returns <code>true</code> if the date is structured
	 * dd/mm/yyyy or ddmmyyyy, and checks for invalid input,
	 * for instance, 32 days, or 13 months.
	 * 
	 * @param date
	 * @return <code> true or false </code>
	 */
	public static boolean isDateFormatted(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu", Locale.UK)
				.withResolverStyle(ResolverStyle.STRICT);
		
		DateTimeFormatter formatterOnlyNumbers = DateTimeFormatter.ofPattern("ddMMuuuu", Locale.UK)
				.withResolverStyle(ResolverStyle.STRICT);

		try {
			if(date.contains("/"))
				formatter.parse(date);
			else
				formatterOnlyNumbers.parse(date);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

}
