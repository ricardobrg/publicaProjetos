package com.publica.grupo2sprint3.model.util;

/***
 * Class responsible for manage money
 * 
 * @version 0.0.1
 * @author Diego Borba <diegoborba25@gmail.com>
 */
public class Money {
	
	/***
	 * Recives an double or float
	 * and return the value
	 * formated with 2 decimal points
	 * 
	 * @author Diego Borba <diegoborba25@gmail.com>
	 * @param number
	 * @return the value formated
	 */
	public static String ToString(double number){
		return String.format("%.2f",number);
	}
}
