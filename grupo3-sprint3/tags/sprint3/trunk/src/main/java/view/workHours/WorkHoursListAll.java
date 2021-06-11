package view.workHours;

import java.util.Calendar;

import controller.workHours.WorkHoursController;
import model.WorkEntry;

/***
 * WorkHoursListAll. 
 * Lists all Days, Months and Years + Hour and Minute of Day. 
 * This information is unique to each CPF in database.
 * 
 * @version 1.0.0
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 */
public class WorkHoursListAll {

	private String cpf;
	
	public WorkHoursListAll(String cpf) {
		this.cpf = cpf;
	}
	
	public void print() {
		WorkHoursController controller = new WorkHoursController();
		
		
		System.out.println();
		System.out.println("=======================");
		for (WorkEntry item : controller.listWorkEntry(this.cpf)) {
			
			System.out.print(item.getDay().get(0).get(Calendar.DAY_OF_MONTH) + "/");
			System.out.print(item.getDay().get(0).get(Calendar.MONTH)+1 + "/");
			System.out.print(item.getDay().get(0).get(Calendar.YEAR) + " - ");
			
			for(int i = 0; i < item.getDay().size(); i++) {
				
				System.out.print(item.getDay().get(i).get(Calendar.HOUR_OF_DAY) + ":");
				System.out.print(item.getDay().get(i).get(Calendar.MINUTE) + "  ");

			}
			
			System.out.println("\n=======================");
			
		}
		System.out.println("=======================");
	}
}
