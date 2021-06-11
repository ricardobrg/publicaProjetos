package view.workHours;

import controller.workHours.WorkHoursWorkEntryController;

/**
 * WorkHoursWorkEntry Class.
 * This class registers a new Work Entry. 
 * Receives the current hour, minute and day. 
 * 
 * @version 1.0.0	
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 */
public class WorkHoursWorkEntry {

	public WorkHoursWorkEntry(String cpf) {
		
		WorkHoursWorkEntryController workEntry = new WorkHoursWorkEntryController();	
		workEntry.addWorkEntry(cpf);
		
	}
	
}
