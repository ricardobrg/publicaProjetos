package view.workHours;

import java.util.Calendar;

import controller.workHours.WorkHoursController;
import model.WorkEntry;

/**
 * WorkHoursListDay. 
 * Lists all registries in a specific day. Including Hour and Minute.
 * This information is unique to each CPF in database.
 * P.S.: "Sistema de Pontos" in Portuguese.
 * 
 * @version 1.0.0	
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 */
public class WorkHoursListDay {
	private String cpf;
	private int day;
	private int month;
	private int year;

	public WorkHoursListDay(String cpf, int day, int month, int year) {
		this.cpf = cpf;
		this.day = day;
		this.month = month-1;
		this.year = year;
	}

	public void print() {
		WorkHoursController controller = new WorkHoursController();


		System.out.println();
		System.out.println("=======================");
		for (WorkEntry item : controller.listWorkEntry(this.cpf)) {

			if(item.getDay().get(0).get(Calendar.DAY_OF_MONTH) == day &&
				item.getDay().get(0).get(Calendar.MONTH) == month &&
					item.getDay().get(0).get(Calendar.YEAR) == year) {

				System.out.print(item.getDay().get(0).get(Calendar.DAY_OF_MONTH) + "/");
				System.out.print(item.getDay().get(0).get(Calendar.MONTH)+1+ "/");
				System.out.print(item.getDay().get(0).get(Calendar.YEAR) + " - ");

				for(int i = 0; i < item.getDay().size(); i++) {

					System.out.print(item.getDay().get(i).get(Calendar.HOUR_OF_DAY) + ":");
					System.out.print(item.getDay().get(i).get(Calendar.MINUTE) + "  ");

				}

				System.out.println("\n=======================");
			}

		}
		System.out.println("=======================");
	}
}
