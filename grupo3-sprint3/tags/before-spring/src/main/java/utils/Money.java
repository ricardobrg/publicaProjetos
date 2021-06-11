package utils;

/***
 * Class responsible for managing money values
 * 
 * @version 0.0.1
 * @author Diego Borba <diegoborba25@gmail.com>
 */
public class Money {

		/***
		 * Receives a double or float
		 * and return the value
		 * formatted with 2 decimal points
		 * 
		 * @author Diego Borba <diegoborba25@gmail.com>
		 * @param number
		 * @return the value formated
		 */
		public static String ToString(double number){
			return String.format("%.2f",number);
		}
	}
