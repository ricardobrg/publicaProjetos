package com.publica.grupo2sprint3.model.person.physicalperson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.publica.grupo2sprint3.model.dao.AddressDAO;
import com.publica.grupo2sprint3.model.dao.CollaboratorDAO;
import com.publica.grupo2sprint3.model.dao.ContactDAO;
import com.publica.grupo2sprint3.model.dao.PointDAO;
import com.publica.grupo2sprint3.model.dao.RoleDAO;
import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;
import com.publica.grupo2sprint3.model.util.ToHash;
import com.publica.grupo2sprint3.model.util.Validators;
import com.sun.istack.NotNull;

/**
 * Class that extends FisicaPerson (and Person). Represents a Collaborator
 * inside the company.
 *
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
 * @author Diego Borba <diegoborba25@gmail.com>
 * @author Caio Shimada <xcaiosr@gmail.com>
 *
 *         Version: 1.5.0
 */

@Entity
public class Collaborator extends PhysicalPerson {
	@NotNull
	private String admissionDate;
	private String lastVacation, vacationSize = "0";
	private Boolean inVacation, canVacation;
	private Double extraSalary = 0.00, grossSalary = 0.00;
	@NotNull
	private Integer workHours = 0;
	private String user, password;
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	private Role role;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Discount> discounts;

	@OneToOne
	private Department manageDepartment;


	public Collaborator() {
	} // DEFAULT CONSTRUCTOR

	public Collaborator(String name) {
		this.name = name;
	}

	public Collaborator(String name, Contact contact) {
		this.name = name;
		this.contact = contact;
	}

	public Collaborator(String name, Contact contact, Address address) {
		super(name, contact, address, null, null);
		this.contact = contact;
	}

	public Collaborator(String name, Contact contact, Address address, Role role, Double extraSalary) {
		super(name, contact, address, null, null);
		this.contact = contact;
		this.role = role;
		this.extraSalary = extraSalary;
		generateGrossSalary();
	}

	public Collaborator(String name, Role role, Double extraSalary) {
		super(name, null, null, null, null);
		this.role = role;
		this.extraSalary = extraSalary;
		generateGrossSalary();
	}

	public Collaborator(String name, Contact contact, Address address, String cpf, String pis, Role role,
			int workhours) {
		super(name, contact, address, cpf, pis);
		this.role = role;
		this.setWorkHours(workhours);
		generateLogin();

	}

	public Collaborator(String name, Contact contact, Address address, String cpf, String pis, Role role,
			String admissionDate, double extraSalary, int workHours) {

		super(name, contact, address, cpf, pis);
		this.setAdmissionDate(admissionDate);
		this.setLastVacation(admissionDate);
		this.role = role;
		this.extraSalary = extraSalary;
		this.setWorkHours(workHours);
		generateGrossSalary();
		generateVacationVars();
		generateLogin();

	}

	public Collaborator(String name, Contact contact, Address address, String cpf, String pis, Role role,
			String admissionDate, Boolean inVacation, double extraSalary, int workHours) {

		super(name, contact, address, cpf, pis);
		this.setAdmissionDate(admissionDate);
		this.setLastVacation(admissionDate);
		this.role = role;
		this.extraSalary = extraSalary;
		this.setWorkHours(workHours);
		this.inVacation = inVacation;
		generateGrossSalary();
		generateVacationVars();
		generateLogin();

	}

	public Collaborator(String name, Contact contact, Address address, String cpf, String pis, String admissionDate,
			Boolean inVacation, double extraSalary, int workHours) {

		super(name, contact, address, cpf, pis);
		this.setAdmissionDate(admissionDate);
		this.setLastVacation(admissionDate);
		this.extraSalary = extraSalary;
		this.setWorkHours(workHours);
		this.inVacation = inVacation;
		generateVacationVars();
		generateLogin();

	}

	public Collaborator(String name, Contact contact, Address address, String cpf, String pis, Role role,
			String admissionDate, String lastVacation, double extraSalary, int workHours) {

		super(name, contact, address, cpf, pis);
		this.setAdmissionDate(admissionDate);
		this.setLastVacation(lastVacation);
		this.role = role;
		this.extraSalary = extraSalary;
		this.setWorkHours(workHours);
		generateGrossSalary();
		generateVacationVars();
		generateLogin();

	}

	public Collaborator(String name, Contact contact, Address address, String cpf, String pis, Role role,
			String admissionDate, String lastVacation, Boolean inVacation, double extraSalary, int workHours) {

		super(name, contact, address, cpf, pis);
		this.setAdmissionDate(admissionDate);
		this.setLastVacation(lastVacation);
		this.role = role;
		this.extraSalary = extraSalary;
		this.setWorkHours(workHours);
		this.inVacation = inVacation;
		generateGrossSalary();
		generateVacationVars();
		generateLogin();

	}

	public Collaborator(String name, String cpf, String pis) {
		super(name, null, null, cpf, pis);
	}

	public Collaborator(String name, String cpf, String roleName, String admissionDate, String lastVacation,
			String inVacation, String extraSalary, String workHours, String password, String user, String access) {
		super(name, null, null, cpf, null);

		this.admissionDate = admissionDate;
		this.lastVacation = lastVacation;
		this.inVacation = (inVacation == "1");
		this.extraSalary = (double) Float.parseFloat(extraSalary);
		this.workHours = Integer.parseInt(workHours);

		if (this.role == null)
			this.role = RoleDAO.getInstance().getAll().get(0);

		if (this.address == null)
			this.address = AddressDAO.getInstance().getAll().get(0);

		if (this.contact == null)
			this.contact = ContactDAO.getInstance().getAll().get(0);

		generateLogin(user, password);

	}

	public Collaborator(String name, String cpf) {
		this.name = name;
		this.setCpf(cpf);
	}

	public Collaborator(String name, Role role) {
		this.setName(name);
		this.setRole(role);
	}
	

	/**
	 * Method for calculating worked time on a day
	 * 
	 * @author Pablo Mafesosli <mafessolip@gmail.com>
	 * @author Vinicius Roosvelt <vinicius_roose@hotmail.com>
	 * 
	 * @param date : Sring
	 * @return Amount of minutes worked on a given day
	 */
	public int workedMinutesInDay(String date) {

		ArrayList<Point> filteredDates = PointDAO.getPointsByDate(this, date);

		// IF there is a odd number of points register, the system should throw an error
		if (filteredDates.size() % 2 != 0) {
			throw new IllegalArgumentException("Ultima sa�da n�o resgistrada, procure a pessoa respons�vel!");
		}

		int forIndex = 0;
		int workedTime = 0;
		for (Point item : filteredDates) {
			if (forIndex % 2 != 0) {
				int entryMinutes = item.getDayMinute();
				int outMinutes = filteredDates.get(forIndex - 1).getDayMinute();
				workedTime += entryMinutes - outMinutes;
			}
			forIndex++;
		}
		return workedTime;
	}

	/**
	 * Method for returning the worked minutes in a month.
	 * 
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
	 * @author Pablo Mafesosli <mafessolip@gmail.com>
	 * @author Vinicius Roosvelt <vinicius_roose@hotmail.com>
	 * 
	 * @param month : Int
	 * @param year  : Int
	 * @return : Int
	 */
	public int workedMinutesInMonth(int month, int year) {
		int result = 0;
		for (int dayOfMonth = 1; dayOfMonth <= 31; dayOfMonth++) {
			String leading = "";
			String monthLeading = "";

			if (dayOfMonth < 10) {
				leading = "0";
			}

			if (month < 10) {
				monthLeading = "0";
			}

			String searchDate = leading + dayOfMonth + "/" + monthLeading + month + "/" + year;
			if (Validators.isDateValid(searchDate)) {
				result += workedMinutesInDay(searchDate);
			}
		}
		return result;
	}

	/**
	 * 
	 * Method for returning worked minutes in a year. It will calculate the worked
	 * minutes from the received date until 1 year later.
	 * 
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
	 * @author Jess� Amaro <jesse.amaro7@gmail.com>
	 * @author Pablo Mafesosli <mafessolip@gmail.com>
	 * @author Vinicius Roosvelt <vinicius_roose@hotmail.com>
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * 

	 * @param year  : an int of the start year
	 * @return : Int
	 */
	public int workedMinutesInYear(int year) {
		int result = 0;

		for (int monthOfYear = 1; monthOfYear <= 12; monthOfYear++) {
			result += workedMinutesInMonth(monthOfYear, year);	
		}
		return result;
	}
	

	/**
	 * Updates vacation control of collaborator
	 * 
	 * @author Diego Borba
	 * @author Caio Shimada
	 * 
	 */
	public void generateVacationVars() {
		LocalDate date = LocalDate.now();
		this.vacationSize = Integer.toString(Vacation.vacationAllowed(this, date.getMonthValue(), date.getYear()));
		canVacation = Vacation.vacationReady(lastVacation);

	}

	/***
	 * This method generates the login informations of the collaborator. Password =
	 * the 2 first digits of phone, 3 first digits of CPF, first letter of his first
	 * name, first letter of his last name and one "!" at the final. User = nick
	 * from email, ex: xXpedrinho_matadorXx@gmail.com will have user ->
	 * xXpedrinho_matadorXx .
	 * 
	 * @author Diego Borba <diegoborba25@gmail.com>
	 */
	private void generateLogin() {
		String cpf = this.getCpf();

		String password = String.format("%s%s%s%s%s", getContact().getPhone().substring(0, 3), cpf.substring(0, 3),
				name.substring(0, 1).toLowerCase(), name.split("// ")[0].substring(0, 1), "!");

		this.user = this.getContact().getEmail().split("@")[0];
		this.password = ToHash.hashGeneratorPbk(user, password);
	}

	private void generateLogin(String user, String password) {
		this.setUser(user);
		this.password = password;
	}

	/**
	 * 
	 * This method lists all employees in a specified department, giving the
	 * possibility to pick a collaborator's point based on your sector.
	 * 
	 * @param collab
	 * 
	 * @author Jess� Amaro <jesse.amaro7@gmail.com>
	 * @author Ana <carolsantos2002@gmail.com>
	 */
	public static ArrayList<Collaborator> getCollabsByDepartment(Collaborator collab) {
		ArrayList<Collaborator> allCollabsDepartment = new ArrayList<Collaborator>();
		Department department = collab.getRole().getDepartment();

		for (Collaborator coll : CollaboratorDAO.getInstance().getAll()) {
			if (coll.getRole().getDepartment() == department) {
				allCollabsDepartment.add(coll);
			}
		}
		return allCollabsDepartment;
	}

	public void addDiscount(String name, Double value) {
		discounts.add(new Discount(name, value));
	}

	private void generateGrossSalary() {
		this.grossSalary = this.extraSalary + this.role.getSal();

		if (discounts == null || discounts.isEmpty()) {
			this.setDiscounts(new ArrayList<Discount>());
			this.addDiscount("INSS", Discount.generateInss(grossSalary));
		}
	}

	/**
	 * This method checks if the value of the access level passed by parameter
	 * is bigger or equals to the access level of the current collaborator object
	 * 
	 * @param accessLevel AccessLevel: Access Level that the collaborator must have or bigger
	 * @return boolean: If the given access level is bigger or equals to the current collaborator
	 * access level
	 */
	public boolean checkAccessLevel(AccessLevel accessLevel) {
		return this.getRole().getAccessLevel().id >= accessLevel.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(String admissionDate) {
		if (Validators.isDateValid(admissionDate) && !admissionDate.equals(null) && !admissionDate.isEmpty()) {
			this.admissionDate = admissionDate;
		}
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		if (!role.equals(null)) {
			this.role = role;
		}
	}

	public double getExtraSalary() {
		return this.extraSalary;
	}

	public void setExtraSalary(double salary) {
		if (salary >= 0) {
			this.extraSalary = salary;
		}
	}

	public int getWorkHours() {
		return workHours;
	}

	public void setWorkHours(int workHours) {
		if (Validators.isWorkHoursValid(workHours) && workHours > 0) {
			this.workHours = workHours;
		}
	}

	public String getLastVacation() {
		return lastVacation;
	}

	public void setLastVacation(String lastVacation) {
		this.lastVacation = lastVacation;
	}

	public Boolean getInVacation() {
		return inVacation;
	}

	public void setInVacation(Boolean inVacation) {
		this.inVacation = inVacation;
	}

	public String getVacationSize() {
		return vacationSize;
	}

	public void setVacationSize(String vacationSize) {

		this.vacationSize = vacationSize;
	}

	public Boolean getCanVacation() {
		return canVacation;
	}

	public void setCanVacation(Boolean canVacation) {
		this.canVacation = canVacation;
	}

	public Double getGrossSalary() {
		return grossSalary;
	}

	public List<Discount> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(ArrayList<Discount> discounts) {
		this.discounts = discounts;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (Validators.validatePassword(password)) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			this.password = passwordEncoder.encode(password);
		}
	}

	public void setGrossSalary(Double grossSalary) {
		this.grossSalary = grossSalary;
	}

	public void setExtraSalary(Double extraSalary) {
		this.extraSalary = extraSalary;
	}

	public void setWorkHours(Integer workHours) {
		this.workHours = workHours;
	}
	
	public Department getManageDepartment() {
		return manageDepartment;
	}

	public void setManageDepartment(Department manageDepartment) {
		this.manageDepartment = manageDepartment;
	}

	@Override
	public String toString() {
		return String.format(
				"" + "Nome: %s\n" + "Cpf: %s\n" + "Pis: %s\n" + "%s" + "Sal�rio Bruto: %.2f\n" + "%s" + "%s"
						+ "Data de Admiss�o: \n",
				this.name, this.getCpf(), this.getPis() != null ? this.getPis() : "N�o Cadastrado!",
				this.role != null ? this.role.toString() : "Cargo: N�o Cadastrado!\n", this.grossSalary,
				this.contact != null ? this.contact.toString() : "N�o Cadastrado!\n",
				this.address != null ? this.address.toString() : "N�o Cadastrado!\n", this.admissionDate);
	}

}
