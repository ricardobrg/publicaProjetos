package model.entities.person;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import model.entities.security.Role;
import utils.authentication.ToHash;
import utils.convertions.DateConversions;
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

@Entity
@Table(name = "collaborators")
public class Collaborator extends FisicaPerson {

	@Column(name = "pbk_token")
	private String pbkToken;

	@Column(name = "pwd_hash")
	private String pwdHash;

	@Column(name = "admission_date")
	private String admissionDate;

	@Column(name = "last_vacation_date")
	private String lastVacationDate;

	@ManyToOne
	private Role role;

	@Column(name = "work_hours")
	private int workHours;

	private double salary;

	public Collaborator(String name, String cpf) {
		super(name, cpf);
	}

	public Collaborator(String name, String cep, String email, String phone, String cpf, String pis, Role role,
			String admissionDate, int workHours, double salary) {
		super(name, cep, email, phone, cpf, pis);

		this.setRole(role);
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
		if (DateValidation.isDateValid(admissionDate)) {
			this.admissionDate = admissionDate;
			if (this.lastVacationDate == null) {
				this.setLastVacationDate(this.admissionDate);
			}
		}
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
		if (DateValidation.isDateValid(lastVacationDate))
			this.lastVacationDate = lastVacationDate;
	}

	public boolean getInVacation() {
		LocalDate localDate = DateConversions.convertDate(this.lastVacationDate);
		LocalDate today = LocalDate.now();
		return localDate.compareTo(today) >= 0 ? true : false;
	}

}
