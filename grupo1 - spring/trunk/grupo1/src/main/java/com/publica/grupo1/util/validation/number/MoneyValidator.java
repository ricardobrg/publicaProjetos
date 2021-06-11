package com.publica.grupo1.util.validation.number;

/***
 * Class responsible for verify money format
 *
 * The method recives money(String) and verify,
 * it formatation. Must have "." and can't have
 * more than 2 digits after the "." 
 * 
 * @version 0.0.1
 * @author Diego Borba <diegoborba25@gmail.com>
 */
public class MoneyValidator {
	/***
	 * Take an String money, and
	 * verify if its valid money
	 * number
	 * 
	 * @param money Ex:(15.00)
	 * @return valid/invalid(true/false)
	 */
	public static boolean validateMoney(String money) {
		String[] arr = money.split("\\.");
		
		return (!(arr[1].length() > 2));
	}
}
