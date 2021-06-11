package controllers;

import java.util.ArrayList;

import models.entities.payroll.Payroll;
import models.entities.workHours.HoursCalc;
import models.hibernate.PayrollHibernate;
import models.hibernate.WorkHoursHibernate;

/***
 * 
 * PayrollController
 * 
 * Class used to manage the payroll in the application controller.
 * 
 * @version 2.0.0
 *
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 * @author Caio Shimada
 *  
 */
public class PayrollController {

	private PayrollHibernate payrollDAO;
	
	public PayrollController() {
		this.payrollDAO = new PayrollHibernate();
	}
	
	/**
	 * Class to generate payroll of collaborator. It is basically an insert method
	 * of a payroll generated to each collaborator.
	 * 
	 * @author Jonathas Rocha de Souza <jonathasrochadesouza@gmail.com>
	 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
	 * @author Caio Shimada
	 * 
	 * @param payroll the payroll object to be added
     * @return the id of the inserted object. Returns -1
     * 		   if the operation failed
	 */
	public int insert(Payroll payroll) {

		WorkHoursHibernate workEntryDAO = new WorkHoursHibernate();

		int hours = HoursCalc.pointDifference(workEntryDAO.findBetweenDates(
				payroll.getCollaborator().getCpf(),
				payroll.getInitDate().atStartOfDay(),
				payroll.getFinalDate().atStartOfDay()));

		payroll.calcPayment(hours);

		return payrollDAO.insert(payroll);
	}

	/**
	 * Class to find a payroll of a collaborator. The id is the payroll id
	 * 
	 * @author Jonathas Rocha de Souza <jonathasrochadesouza@gmail.com>
	 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
	 * @author Caio Shimada
	 * 
	 * @param id the id of the collaborator whose payrolls will be retrieved
	 * @return the ArrayList of payrolls found
	 */
	public ArrayList<Payroll> findByCollabId(int id) {
		return payrollDAO.findByCollab("id", String.valueOf(id));
	}

	/**
	 * Class to find a payroll of a collaborator. The id is the payroll id
	 *
	 * @author Jonathas Rocha de Souza <jonathasrochadesouza@gmail.com>
	 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
	 * @author Caio Shimada
	 * 
	 * @param id the id of the specific payroll to be retrieved
	 * @return the payroll found
	 */
	public Payroll findById(int id) {
		return (Payroll) payrollDAO.find("id", String.valueOf(id));
	}

	/**
	 * Class to find a payroll of a collaborator via CPF
	 * 
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * 
	 * @param cpf the string of cpf of the collaborator whose payroll will be retrieved
	 * @return the ArrayList of payrolls found
	 */
	public ArrayList<Payroll> findByCpf(String cpf) {
		return payrollDAO.findByCollab("cpf", cpf);
	}

	/**
	 * Class to list all Payrolls.
	 * 
	 * @author Jonathas Rocha de Souza <jonathasrochadesouza@gmail.com>
	 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
	 * 
	 * @return the ArrayList of every payrolls
	 */
	public ArrayList<Payroll> getAll() {
		return payrollDAO.getAll();
	}

}
