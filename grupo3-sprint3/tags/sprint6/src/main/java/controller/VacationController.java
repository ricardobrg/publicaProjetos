package controller;

import java.util.HashMap;
import java.util.Map;

import model.dao.CollaboratorDAO;
import model.dao.WorkEntryDAO;
import model.entities.person.Collaborator;
import model.entities.vacation.Vacation;
import model.entities.workHours.HoursCalc;
import utils.convertions.StringToLocalDateTime;

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
	 * if the collaborator can take Vacation, how many vacation days are available
	 * and the payment.
	 * 
	 * @author Diego Borba <diegoborba25@gmail.com>
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * 
	 * @param cpf  String
	 * @param date the date as string of the closing vacation day
	 * @return the vacation information about a collaborator
	 */
	public Map<String, String> getVacationList(String cpf, String date) {

		CollaboratorDAO collabDAO = new CollaboratorDAO();
		WorkEntryDAO workEntryDAO = new WorkEntryDAO(cpf);

		String name, canVacation, inVacation, vacationDays, vacationPayment;

		Collaborator collab = collabDAO.findByCpf(cpf);

		int workedHours = 0;
		if (collab.getLastVacationDate() == null) {
			workedHours = HoursCalc.pointDifference(workEntryDAO.findBetweenDates(
					StringToLocalDateTime.stringToLocalDateTime(collab.getAdmissionDate()),
					StringToLocalDateTime.stringToLocalDateTime(date)));
		} else {
			workedHours = HoursCalc.pointDifference(workEntryDAO.findBetweenDates(
					StringToLocalDateTime.stringToLocalDateTime(collab.getLastVacationDate()),
					StringToLocalDateTime.stringToLocalDateTime(date)));
		}

		int days = Vacation.vacationAllowedAndDays(workedHours);
		cpf = collab.getCpf();
		name = collab.getName();
		canVacation = (days == 0 ? "Não pode tirar férias" : "Pode tirar férias");
		inVacation = "A definir";
		vacationDays = String.valueOf(days);
		vacationPayment = Vacation.VacationPayment(collab.getSalary(), days);

		Map<String, String> vacJson = new HashMap<>();
		vacJson.put("cpf", cpf);
		vacJson.put("name", name);
		vacJson.put("canVacation", canVacation);
		vacJson.put("inVacation", inVacation);
		vacJson.put("vacationDays", vacationDays);
		vacJson.put("vacationPayment", vacationPayment);

		return vacJson;
	}
}
