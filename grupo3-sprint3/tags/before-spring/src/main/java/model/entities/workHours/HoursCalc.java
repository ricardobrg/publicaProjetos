package model.entities.workHours;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/***
 * HoursCalc
 * 
 * Class responsible for calculating the hours of an employee
 * based on the database, receives as parameter two dates, a
 * start date, and an end date. It returns based on the time
 * entries how much the employee worked between the dates
 * entered, in munutes.
 * 
 * @version 2.0.0
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 */

public class HoursCalc {

	/***
	 *
	 * Method pointDifference
	 *
	 * Method used to calculate how much an employee works
	 * between the two dates informed, it goes through the
	 * database of hours entry and using the calendar class
	 * returns the number of minutes that the employee worked
	 * between the dates informed.
	 * 
	 * @param ArrayList WorkEntry database clocks
	 * @param String date1
	 * @param String date2
	 * @return
	 */
	public static int pointDifference(ArrayList<LocalDateTime> clocks) {

		int hours = 0;

		if (clocks != null) {
			for(int i = 1; i < clocks.size(); i+=2){ 
				hours += ChronoUnit.HOURS.between(clocks.get(i-1), clocks.get(i));
			}
		}

		return hours;

	}

}
