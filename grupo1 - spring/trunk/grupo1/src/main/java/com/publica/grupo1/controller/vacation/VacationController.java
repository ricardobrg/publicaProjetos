package com.publica.grupo1.controller.vacation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.publica.grupo1.model.dao.point.PointDAO;
import com.publica.grupo1.model.entities.collaborator.Collaborator;
import com.publica.grupo1.model.entities.point.Point;

/***
 * Class containing methods for vacations calculations.
 * 
 * @author Pedro
 * @author Vinicius
 * @author Pablo
 * @author Diego
 */
public class VacationController {	
	
	private Collaborator collab;
	private Session session;
	
	public VacationController(Collaborator collab, Session session) {
		this.collab = collab;
		this.session = session;
	}
	
	/***
	 * Checks if collaborator can take off.
	 * 
	 * @return <code>true or false</code>
	 */
	public boolean isVacationAvailable() {
		LocalDate date = collab.getLastVacationDate();
		return date.until(LocalDate.now()).getYears() >= 1;
	}
	
	/***
	 * Compares the next time the collaborator can take off with the now.
	 * 
	 * @param collab 
	 * @return the days left to take off. 
	 */
	public long daysToVacation() {
		LocalDate date = collab.getLastVacationDate().plusYears(1);
		return ChronoUnit.DAYS.between(LocalDate.now(), date);
	}
	
	/***
	 * Gets all the collaborator's points since he last took off, and today.
	 * @return
	 */
	public List<Point> getCollaboratorPointsOnInterval() {
		PointDAO pointDAO = PointDAO.getInstance(session);
		LocalDate date = collab.getLastVacationDate();
		
		return pointDAO.getCollaboratorPointsOnInterval(collab, date, LocalDate.now());
	}
		
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
	 * 
	 * @return The difference between two points.
	 * @author Giovanni Buzzi
	 */
	public  int pointDifference(List<Point> pointList) {
		int hours = 0;
		ArrayList<LocalDateTime> clocks = new ArrayList<LocalDateTime>();
		
		pointList.forEach(o -> clocks.add(o.getDate()));

		if (clocks != null) {
			for(int i = 1; i < clocks.size(); i += 2){
				long difference = ChronoUnit.HOURS.between(clocks.get(i - 1), clocks.get(i));
				
				if(difference > 8)
					difference = 8;
				
				hours += difference;
			}
		}
		
		return hours;
	}
	
	public int getWorkedTime() {
		List<Point> points = getCollaboratorPointsOnInterval();
		return pointDifference(points) * 60;
	}
	
	public int vacationAllowedDays() {
		int workedTime = getWorkedTime();
		int vacationDays = 0;
		
		if (workedTime >= 1920) 
			vacationDays = 30;
			
		else if (workedTime >= 1808 && workedTime <= 1872)
			vacationDays = 24;
			
		else if (workedTime >= 1736 && workedTime <= 1807) 
			vacationDays = 18;
			
		else if (workedTime >= 1664 && workedTime <= 1735) 
			vacationDays = 12;
		
		return vacationDays;	
	}

}