package com.publica.grupo2sprint3.model.util;

public class UtilNumbers {
	public static String onlyNumbers(String str) {
		return str.replaceAll("\\D", "");
	}
}
