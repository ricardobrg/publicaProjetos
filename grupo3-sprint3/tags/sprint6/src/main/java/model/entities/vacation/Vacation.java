package model.entities.vacation;

import utils.Money;

/***
 * 
 * @author Diego Daniel Borba <diegoborba25@gmail.com>
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
 *
 * @author Diego Borba
 * @author Caio Shimada
 *
 * @version: 1.5.1
 */
public class Vacation {

	/***
	 * Method for verifying the number of vacation days that the collaborator is
	 * allowed to have. It also helps identifying if he is allowed to be in vacation.
	 * 
	 * @param result : Int
	 * @return : Int
	 * 
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
	 * 
	 */
	public static int vacationAllowedAndDays(int result) {
		int vacationDays = 0;
		
		if (result >= 1920) {
			vacationDays = 30;
			
		} else if (result >= 1808 && result <= 1872) {
			vacationDays = 24;
			
		} else if (result >= 1736 && result <= 1807) {
			vacationDays = 18;
			
		} else if (result >= 1664 && result <= 1735) {
			vacationDays = 12;
		}
		
		return vacationDays;
		
	}

	/**
	 * Return how much money that the collaborator will receive.
	 * 
	 * @author Diego Borba <diegoborba25@gmail.com>
	 * @param payment
	 * @param vacationDays
	 * @param doubleVacation (2years take off)
	 * @return payment value
	 */
	public static String VacationPayment(Double payment, int vacationDays) {
		double result;
		result = ((payment/30)*vacationDays + ((payment/30)*vacationDays)/3);
		result-= (result/100)*7.5;
		return Money.ToString(result);
	}
}
