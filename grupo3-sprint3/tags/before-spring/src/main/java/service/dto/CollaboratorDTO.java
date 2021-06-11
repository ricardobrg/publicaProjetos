package service.dto;

import model.entities.person.FisicaPerson;
import model.entities.security.Role;

/**
 * Class that extends FisicaPerson (and Person). Represents a Collaborator
 * inside the company.
 *
 * @version 1.1.2
 *
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 * @author Pedro Vinicius Hostert <pedrohostertt@gmail.com>
 */

public class CollaboratorDTO extends FisicaPerson {

	private String admissionDate;
	private String lastVacationDate;
	private Role role;
	private int workHours;
	private double salary;

	public CollaboratorDTO() {
		super();
	}

	public String getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public double getSalary() {
		return this.salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getWorkHours() {
		return workHours;
	}

	public void setWorkHours(int workHours) {
			this.workHours = workHours;
	}
	
	public String getLastVacationDate() {
		return lastVacationDate;
	}

	public void setLastVacationDate(String lastVacationDate) {
		this.lastVacationDate = lastVacationDate;
	}

}










