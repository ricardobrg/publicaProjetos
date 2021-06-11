package controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;

import models.entities.person.Collaborator;
import models.entities.workHours.HoursCalc;
import models.entities.workHours.WorkEntry;
import models.hibernate.CollaboratorHibernate;
import models.hibernate.WorkHoursHibernate;
import utils.convertions.DateConversions;

/**
 * WorkHoursController. <br></br>
 * This class connects the model and the view,
 * and is used to communicate between classes in the model and view.
 * Searches for an specific CPF and returns the list of Work Entrys.
 * It is used to keep track of Work Hours.
 * <br></br>P.S.: ClockIn = Bater Ponto
 * 
 * @version 2.0.0
 * 
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 * @author Caio Shimada
 * 
 */
public class WorkHoursController {

	private WorkHoursHibernate workEntryDAO;
	private String currentCPF;

	public WorkHoursController(String cpf) {
		this.currentCPF = cpf;
		this.workEntryDAO = new WorkHoursHibernate();
	}

	/**
	 * Clocks in the collaborator.
	 * 
	 * It inserts a new work entry.
	 * 
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Caio Shimada
	 * 
     * @return the id of the inserted object. Returns -1
     * 		   if the operation failed
	 */
	public int clockIn() {
		CollaboratorHibernate collabDAO = new CollaboratorHibernate();
		Collaborator collab = (Collaborator) collabDAO.find("cpf", this.currentCPF);
		WorkEntry we = new WorkEntry(collab);
		we.clockIn();
		return workEntryDAO.insert(we);
	}
	
	/**
	 * Finds an Work Entry by its id.
	 * 
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Caio Shimada
	 * 
	 * @param id the id of the desired work entry
	 * @return the Work Entry found
	 */
	public WorkEntry find(int id) {
		return (WorkEntry) workEntryDAO.find("id", String.valueOf(id));
	}

	/**
	 * Retrieves an ArrayList of Work Entries of the collaborators between two dates.
	 * 
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Caio Shimada
	 * 
	 * @param date1 the string of the initial date
	 * @param date2 the string of the final date
	 * @return the ArrayList of the work entries between this period
	 */
	public ArrayList<LocalDateTime> showHours(String date1, String date2) {
		return workEntryDAO.findBetweenDates(
						this.currentCPF,
						DateConversions.convert(date1), 
						DateConversions.convert(date2));
	}
	
	/**
	 * Calculates the hours worked between a period of dates.
	 * 
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Caio Shimada
	 * 
	 * @param date1	the string of the initial date
	 * @param date2 the string of the final date
	 * @return the amount of hours worked between this period
	 */
	public int calculateHours(String date1, String date2) {
		ArrayList<LocalDateTime> clocks = workEntryDAO.findBetweenDates(
						this.currentCPF,
						DateConversions.convert(date1), 
						DateConversions.convert(date2));
		
		return HoursCalc.pointDifference(clocks);
	}

	/**
	 * Lists every WorkEntry existing in the database
	 * 
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Caio Shimada
	 * 
	 * @return the ArrayList of existing Work Entries
	 */
	public ArrayList<WorkEntry> listAll() {
		return workEntryDAO.getAll();
	}

}
