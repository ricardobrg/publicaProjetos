package model.entities.person;

import model.entities.security.Role;
import utils.UtilsNumber;
import utils.authentication.ToHash;
import utils.validations.datetime.DateValidation;

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
public class Collaborator extends FisicaPerson {
	private String pbkToken;
	private String pwdHash;
	private String admissionDate;
	private Role role;
	private int workHours;
	private double salary;

	public Collaborator(String name, String cpf) {
		super(name, cpf);
	}

	public Collaborator(String name, String cep, String email, String phone,
			String cpf, String pis, Role role, String admissionDate, 
			int workHours, double salary) {
		super(name, cep, email, phone, cpf, pis);

		this.role = role;
		this.salary = salary;
		
		setAdmissionDate(admissionDate);		
		setWorkHours(workHours);
		
		setPbkToken(ToHash.hashGeneratorPbk(cpf, pwdHash));
	}

	public Collaborator() {

	}
	
	public String getPbkToken() {
		return pbkToken;
	}
	
	public void setPbkToken(String pbkToken) {
		this.pbkToken = pbkToken;
	}
	
	public String getPwdHash() {
		return pwdHash;
	}
	
	public void setPwdHash(String pwdHash) {
		this.pwdHash = ToHash.hashGeneratorSha(pwdHash);
	}

	public String getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(String admissionDate) {
		if (DateValidation.isDateValid(admissionDate)) 
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
		if(UtilsNumber.isValidNumber(workHours, 0, 160))
			this.workHours = workHours;
	}
}










