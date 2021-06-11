package utils.convertions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * StringToLocalDateTime Class<br>
 * Used to parse dates in a pattern. 
 * (dd/MM/yyyy)
 * @return retDate : String
 */
public class DateConversions {

	public static LocalDateTime convert(String date) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate retDate = LocalDate.parse(date,formatter);
		
		LocalDateTime retDateTime = retDate.atStartOfDay();

		return retDateTime;
	}	
	
	/**
	 * Instead of using LocalDateTime, uses LocalDate only.
	 * @return retDate : String
	 */
	public static LocalDate convertDate(String date) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate retDate = LocalDate.parse(date,formatter);

		return retDate;
	}	
	
	/**
	 * Used to parse dates from 2021-01-05 to 05/01/2021. (Example)
	 * @return ret : String
	 */
	public static String parseDate(String date) {
		
		String ret = "";
		ret = date.substring(8,10) + "/" + date.substring(5,7) + "/" + date.substring(0,4);
		
		return ret;
	}
}
