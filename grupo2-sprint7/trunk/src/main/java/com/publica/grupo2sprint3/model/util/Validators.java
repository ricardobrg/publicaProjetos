package com.publica.grupo2sprint3.model.util;

import java.time.Year;

/**
 * This class provides static methods for validation of various data
 * 
 * @version 1.5.0
 * 
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 * @author Pablo Mafessoli <mafessolip@gmail.com>
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
 * 
 * @author Caio Shimada <xcaiosr@gmail.com>
 */
public class Validators {

	/***
	 * Static Method for date validation
	 * 
	 * @param date String: Date that will be validated
	 * @return boolean: Result of the validation
	 * 
	 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
	 */
	public static boolean isDateValid(String date) {

		if (!date.matches("[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}")) {
			return false;
		}

		int day = Integer.parseInt(date.substring(0, 2));
		int month = Integer.parseInt(date.substring(3, 5));
		int year = Integer.parseInt(date.substring(6, 10));

		if (month == 2) {
			if ((Year.isLeap(year) && day > 29) || (!Year.isLeap(year) && day > 28)) {
				return false;
			}
		} else if ((month < 8 && month % 2 == 0) && (day > 30)) {
			return false;
		} else if ((month >= 8 && month % 2 != 0) && (day > 30)) {
			return false;
		}

		return (date.matches("^\\s*(3[01]|[12][0-9]|0?[1-9])\\/(1[012]|0?[1-9])\\/((?:19|20)\\d{2})\\s*$"));
	}

	/***
	 * Method for checking if a set of characters is valid.
	 * 
	 * Normal and Special characters are valid, as well as spaces and hyphens.
	 * 
	 * @param texto : String 
	 * @return  :boolean - True if does not exist special characters
	 * 
	 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 */
	public static boolean isCharactersValid(String texto) {
		final int NUM_MIN = 1;
		final int NUM_MAX = 50;
		return texto.matches("[a-zA-Zà-úÀ-Ú -]{" + NUM_MIN + "," + NUM_MAX + "}");
	}

	/**
	 * Static Method for CPF validation.
	 * 
	 * @param cpf : String
	 * @return : Boolean
	 *
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 */
	public static boolean isCpfValid(String cpf) {
		if (cpf == null) {
			return false;
		}
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
	 * Static Method for E-mail validation.
	 * 
	 * @param email : String
	 * @return  : Boolean 
	 *
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
	 */
	public static boolean isEmailValid(String email) {
		return email.matches(
				"^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
	}

	/**
	 * Validates cep considering its format and size
	 * 
	 * @param cep : String
	 * @return : Boolean
	 *
	 * @author Caio Shimada <xcaiosr@gmail.com>
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
	 * @param pis a string of a pis to validate
	 * @return <code>true</code> if pis is valid; <code>false</code> otherwise
	 *
	 * @author Caio Shimada <xcaiosr@gmail.com>
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
	public static boolean isPhoneValid(String tel) {
		if (tel.contains("(") && !tel.contains(")") || tel.contains(")") && !tel.contains("("))
			return false;
		return (tel.matches("\\(?[1-9][0-9]\\)? ?9?[0-9]{4}-?[0-9]{4}"));
	}

	/**
	 * Static Method for CNPJ validation.
	 * 
	 * @param cnpj : String 
	 * @return : Boolean
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 */
	public static boolean isCnpjValid(String cnpj) {

		if (cnpj.contains(".") || cnpj.contains("/") || cnpj.contains("-")) {
			if (!cnpj.matches("^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$"))
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

	/**
	 * Method for checking if price input is valid.
	 * 
	 * @param price double: Price that will be verified
	 * @return
	 * 
	 * @author Vinicius Roosevelt<vinicius_roose@hotmail.com>
	 */
	public static boolean isPriceValid(double price) {
		return (!(price <= 0));
	}

	/**
	 * Method for checking if workHours sums a valid number.
	 * 
	 * @param workHours
	 * @return
	 * 
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
	 */
	public static boolean isWorkHoursValid(int workHours) {
		return (workHours > 0 & workHours <= 160);
	}

	/**
	 * Method for checking if salary is bigger than zero.
	 * 
	 * @param salary
	 * 
	 * @return
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
	 */
	public static boolean isSalaryValid(double salary) {
		return (salary >= 0);
	}

	private static final String PASSWORD_REGEX = 
			"^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$";

	/***
	 * Verify the password size(must be bigger than 8 and smaller than 100),
	 * And the password format, must have 1 upper, 1 lower and 1 special character
	 * 
	 * @param String password
	 * @return true/false(valid/invalid)
	 * 
	 * @author Diego Borba <diegoborba25@gmail.com>
	 */
	public static boolean validatePassword(String password) {
		return password.matches(PASSWORD_REGEX); 
	}


}


