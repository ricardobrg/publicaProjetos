package com.publica.grupo1.util.validation.text;

public class TextValidator {
	/***
	 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
	 * 
	 * Method to validate characters
	 * 
	 * This method validate special characters, size, and number 
	 * characters on String text
	 * 
	 * @param text Text to validate
	 * @param min Minimal size
	 * @param max Max size
	 * @param number Numbers on text = True
	 * @return boolean
	 */
	public static boolean caracterValidation(String text, int min, int max, boolean number) {
		
		String regexNum = "";
		
		if(number) 
			regexNum = "0-9";
		
		return text.matches("[a-zA-Z "+regexNum+"]{"+min+","+max+"}");		
	}
	
}
