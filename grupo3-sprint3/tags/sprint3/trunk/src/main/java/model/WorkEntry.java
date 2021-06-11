package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import model.entities.person.Collaborator;

/**
 * 
 * WorkEntry Class.
 * Stands for "Bater Ponto" in Portuguese.
 * Receives a collaborator, month and day (directly from the system's hour).
 * If the number of registries is odd, returns an error message.
 * 
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 */
public class WorkEntry {

	ArrayList<Calendar> day;
	ArrayList<ArrayList<Calendar>> month;
	Collaborator collaborator;

	public WorkEntry(Collaborator collaborator) {	
		day = new ArrayList<Calendar>();
		month = new ArrayList<ArrayList<Calendar>>();	
		this.collaborator = collaborator;
	}
	
	public WorkEntry(Collaborator collaborator, ArrayList<ArrayList<Calendar>> month,
			ArrayList<Calendar> day) {
		this.collaborator = collaborator;
		this.month = month;
		this.day = day;
	}
	
	public void clockIn() {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);

		if(day.size() == 0) {
			day.add(calendar);
		}else {

			if(day.get(day.size()-1).get(Calendar.DAY_OF_MONTH)
					== calendar.get(Calendar.DAY_OF_MONTH)) {
				day.add(calendar);
			}else {
				if(day.size() % 2 == 1) {
					System.out.println("A saída não foi registrada!!");
					System.out.println("Avise o RH!");
					day.add(null);
				}
				month.add(day);
				day.clear();
			}
		}
	}

	public ArrayList<Calendar> getDay(){
		return this.day;
	}
	
	public ArrayList<ArrayList<Calendar>> getMonth(){
		return this.month;
	}
	
	public void clearMonth(){
		this.month.clear();
	}
	
	public Calendar getFirstDay() {
		return month.get(0).get(0);
	}
	
	public Calendar getLastDay() {
		return month.get(month.size()-1).get(0);
	}
	
	public Collaborator getCollaborator() {
		return this.collaborator;
	}
	
}
