package com.publica.grupo2sprint3.model.person.physicalperson;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/***
 * 
 * @author Diego Daniel Borba <diegoborba25@gmail.com>
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
 *
 * @author Diego Borba
 * @author Caio Shimada
 *
 * Version: 1.5.0
 */
public class Vacation {

	/***
	 * Method for verifying the number of vacation Days that 
	 * the collaborator is allowed to have.
	 * 
	 * @param year : Int
	 * @return : Int
	 * 
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
	 * 
	 */
	public static int vacationAllowed(Collaborator collab, int month, int year) {
		int vacationDays = 0;
		int result = collab.workedMinutesInYear(year);
		
		if (result >= (1920 * 60)) {
			vacationDays = 30;
			return vacationDays;
		}

		if (result >= (1808 * 60) && result <= (1872 * 60)) {
			vacationDays = 24;
			return vacationDays;
		}

		if (result >= (1736 * 60) && result <= (1807 * 60) ) {
			vacationDays = 18;
			return vacationDays;			
		}

		if (result >= (1664 * 60) && result <= (1735 * 60)) {
			vacationDays = 12;
			return vacationDays;
		}
		return vacationDays;
	}

	/***
	 * Return if the collaborator can Take Off now
	 * 
	 * @author Diego Borba <diegoborba25@gmail.com>
	 * @author Caio Shimada
	 * 
	 * @param lastVacation
	 * @return true/false(can/cant take off)
	 */
	public static boolean vacationReady(String lastVacation){
		String[] d = lastVacation.split("/");
		LocalDate date = LocalDate.of(Integer.parseInt(d[2]), Integer.parseInt(d[1]), Integer.parseInt(d[0]));
		LocalDate now = LocalDate.now();
		
		int difference = (int) ChronoUnit.YEARS.between(date, now);
		return (difference >= 1) ? true : false; 
		
	}
	
	/***
	 * Return if the collaborator will recive
	 * a double vacation
	 * 
	 * @author Diego Borba <diegoborba25@gmail.com>
	 * @author Caio Shimada
	 * 
	 * @param lastVacation
	 * @return true/false(are double/arent double)
	 */
	public static boolean doubleVacation(String lastVacation) {
		String[] d = lastVacation.split("/");
		LocalDate date = LocalDate.of(Integer.parseInt(d[2]), Integer.parseInt(d[1]), Integer.parseInt(d[0]));
		LocalDate now = LocalDate.now();
		
		int difference = (int) ChronoUnit.YEARS.between(date, now);
		return (difference >= 2) ? true : false; 
	}

	/***
	 * Return how much money that 
	 * the collaborator will receives
	 * 
	 * @author Diego Borba <diegoborba25@gmail.com>
	 * @param salary
	 * @param VacationSize
	 * @param doubleVacation (2years take off)
	 * @return	payment value
	 */
	public static Double vacationPayment(double grossSalary, int VacationSize, boolean doubleVacation) {
		double result;
		result = ((grossSalary/30)*VacationSize + ((grossSalary/30)*VacationSize)/3);
		result-= (result/100)*7.5;

		if (doubleVacation)
			return result*2;
		else
			return result;
	}
	

	/***
	 * Override of the toString method to inform all fields of this
	 * object.
	 */
	public static String toString(Collaborator collab) {
		return String.format(""
				+ "Pode tirar f�rias? %s\n"
				+ "Est� de f�rias? %s\n"
				+ "Tempo de f�rias: %s\n",
				collab.getCanVacation() ? "Pode" : "N�o pode",
				collab.getInVacation() ? "Est�" : "N�o est�",
				collab.getVacationSize());
		
	}

}