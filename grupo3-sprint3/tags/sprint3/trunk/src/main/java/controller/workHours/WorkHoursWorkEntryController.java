package controller.workHours;

import model.WorkEntry;
import model.dao.WorkEntryDAO;
import model.entities.person.Collaborator;


/**
 * WorkHoursWorkEntryController <br></br>
 * This class connects the model and the view,
 * and is used to communicate between classes in the model and view.
 * The method addWorkEntry is responsible for adding a workEntry.
 * It is used to keep track of Work Entrys.
 * <br></br>P.S.: WorkEntry = Bater Ponto
 * 
 * @version 1.0.0
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 */
public class WorkHoursWorkEntryController {


	public WorkHoursWorkEntryController() {

	}

	public void addWorkEntry(String cpf) {

		WorkEntryDAO workEntryDAO = new WorkEntryDAO();
		
		Collaborator colab = new Collaborator();
		
		colab.setCpf(cpf);
		
		WorkEntry workEntry = new WorkEntry(colab);
		workEntry.clockIn();
		
		workEntryDAO.addObject(workEntry);

	}

}
