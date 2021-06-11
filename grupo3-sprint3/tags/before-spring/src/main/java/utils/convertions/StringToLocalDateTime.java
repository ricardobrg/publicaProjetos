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
public class StringToLocalDateTime {

	public static LocalDateTime stringToLocalDateTime(String date) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate retDate = LocalDate.parse(date,formatter);
		
		LocalDateTime retDateTime = retDate.atStartOfDay();

		return retDateTime;
	}	
	
}
