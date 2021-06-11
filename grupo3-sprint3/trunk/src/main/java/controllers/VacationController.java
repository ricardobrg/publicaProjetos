package controllers;

import api.dto.VacationDTO;
import models.entities.person.Collaborator;
import models.entities.vacation.Vacation;
import models.entities.workHours.HoursCalc;
import models.hibernate.CollaboratorHibernate;
import models.hibernate.WorkHoursHibernate;
import utils.convertions.DateConversions;

/***
 * Class responsible for the management of data from Vacation.
 * 
 * @version 2.0.0
 * 
 * @author Diego Borba <diegoborba25@gmail.com>
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 * @author Caio Shimada
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
	 * @author Caio SHimada
	 * 
	 * @param cpf  the String of the cpf of the collaborator
	 * @param date the date as string of the closing vacation day
	 * @return the vacation information about a collaborator
	 */
	public VacationDTO getVacationList(String cpf, String date) {
		CollaboratorHibernate collabDAO = new CollaboratorHibernate();
		WorkHoursHibernate workEntryDAO = new WorkHoursHibernate();
		VacationDTO vacDTO = new VacationDTO();

		Collaborator collab = (Collaborator) collabDAO.find("cpf", cpf);

		int workedHours = 0;
		if (collab.getLastVacationDate() == null) 
			workedHours = HoursCalc.pointDifference(workEntryDAO.findBetweenDates(
						  cpf,
						  DateConversions.convert(collab.getAdmissionDate()),
					      DateConversions.convert(date)));
		else 
			workedHours = HoursCalc.pointDifference(workEntryDAO.findBetweenDates(
						  cpf,
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
