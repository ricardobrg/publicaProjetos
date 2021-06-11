package controller.workHours;

import java.util.ArrayList;

import model.WorkEntry;
import model.dao.WorkEntryDAO;

/**
 * WorkHoursController. <br></br>
 * This class connects the model and the view,
 * and is used to communicate between classes in the model and view.
 * Searches for an specific CPF and returns the list of Work Entrys.
 * It is used to keep track of Work Hours.
 * <br></br>P.S.: WorkEntry = Bater Ponto
 * 
 * @version 1.0.0
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 */
public class WorkHoursController {
	
	public WorkHoursController() {} //DEFAULT CONSTRUCTOR
	
	/**
	 * Provides a List containing all work entry registries of an collaborator
	 * 
	 * @param cpf String: Collaborator used to filter the work entry registries
	 * @return ArrayList containing all work entry registries of an specific collaborator
	 */
	public ArrayList<WorkEntry> listWorkEntry(String cpf) {
		
		ArrayList<WorkEntry> workEntrys = new ArrayList<WorkEntry>();
		
		WorkEntryDAO workEntryDAO = new WorkEntryDAO();
		
		workEntrys = workEntryDAO.getByMonthYear(cpf);
	
		return workEntrys;
	}
	
}
