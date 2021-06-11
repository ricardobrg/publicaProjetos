package controller;

import model.dao.CollaboratorDAO;
import model.dao.WorkEntryDAO;
import model.entities.person.Collaborator;
import model.entities.vacation.Vacation;
import model.entities.workHours.HoursCalc;
import service.dto.VacationDTO;
import utils.convertions.DateConversions;

/***
 * Class responsible for the management of data from Vacation.
 * 
 * @version 1.5.0
 * @author Diego Borba <diegoborba25@gmail.com>
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 */
public class VacationController {

	/***
	 * Lists all information about the Vacation of a specific collaborator. It shows
	 * if the collaborator can take Vacation, how many vacation days are available,
	 * is currently in vacation and the payment.
	 * 
	 * @author Diego Borba <diegoborba25@gmail.com>
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * 
	 * @param cpf  String
	 * @param date the date as string of the closing vacation day
	 * @return the vacation information about a collaborator
	 */
	public VacationDTO getVacationList(String cpf, String date) {
		CollaboratorDAO collabDAO = new CollaboratorDAO();
		VacationDTO vacDTO = new VacationDTO();
		WorkEntryDAO workEntryDAO = new WorkEntryDAO(cpf);

		Collaborator collab = collabDAO.findByCpf(cpf);

		int workedHours = 0;
		if (collab.getLastVacationDate() == null) 
			workedHours = HoursCalc.pointDifference(workEntryDAO.findBetweenDates(
						  DateConversions.convert(collab.getAdmissionDate()),
					      DateConversions.convert(date)));
		else 
			workedHours = HoursCalc.pointDifference(workEntryDAO.findBetweenDates(
					      DateConversions.convert(collab.getLastVacationDate()),
					      DateConversions.convert(date)));

		int days = Vacation.vacationAllowedAndDays(workedHours);

		vacDTO.setName(collab.getName());
		vacDTO.setCpf(collab.getCpf());
		vacDTO.setVacationDays(days);
		vacDTO.setVacationPayment(Vacation.VacationPayment(collab.getSalary(), days));
		vacDTO.setCanVacation(Vacation.canVacation(days));

		return vacDTO;
	}
	
}
