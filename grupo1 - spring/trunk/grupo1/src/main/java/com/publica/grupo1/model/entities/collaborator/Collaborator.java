package com.publica.grupo1.model.entities.collaborator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.publica.grupo1.controller.services.Password;
import com.publica.grupo1.model.entities.contact.Contact;
import com.publica.grupo1.model.entities.endereco.Endereco;
import com.publica.grupo1.model.entities.gender.Genre;
import com.publica.grupo1.model.entities.permissions.Permission;
import com.publica.grupo1.util.convertion.LocalDateAdapter;

/***
 * 
 *
 * Class to define collaborators
 * 
 * This class serves to assign all the characteristics of the collaborators and
 * guarantee their operation
 *
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 * @author Pedro Vinicius Hostert <pedrohostertt@gmail.com>
 * @author Vinicius
 * @author Diego
 * @author Pablo
 */

@Entity
public class Collaborator {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCollaborator;

	private String imgUrl;

	@OneToOne
	private Permission permission;

	@OneToOne
	private Endereco endereco;

	@OneToOne
	private Contact contact;

	@OneToOne
	private Genre genre;

	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate admissionDate, birthDate, lastVacationDate;

	private double salary;
	private boolean nocturnalWork;

	private String rg, cpf, motherName, fatherName, maritalStatus, naturalness, nacionality;
	private String password, name, surname;

	/**
	 * collab.setAdmissionDateByAmericanPattern(collaboratorFromDb.get("admissao"));
	 * collab.setBirthDateByAmericanPattern(collaboratorFromDb.get("nascimento"));
	 * collab.setPassword(collaboratorFromDb.get("senha"));
	 */

	public Collaborator() {

	}

	public Collaborator(int idCollaborator, Permission permission, Endereco endereco, Contact contact, Genre genre,
			LocalDate admissionDate, LocalDate birthDate, LocalDate lastVacationDate, double salary,
			boolean nocturnalWork, String rg, String cpf, String motherName, String fatherName, String maritalStatus,
			String naturalness, String nacionality, String password, String name, String surname, Genre gender) {
		super();
		this.idCollaborator = idCollaborator;
		this.permission = permission;
		this.endereco = endereco;
		this.contact = contact;
		this.genre = gender;
		this.admissionDate = admissionDate;
		this.birthDate = birthDate;
		this.lastVacationDate = lastVacationDate;
		this.salary = salary;
		this.nocturnalWork = nocturnalWork;
		this.rg = rg;
		this.cpf = cpf;
		this.motherName = motherName;
		this.fatherName = fatherName;
		this.maritalStatus = maritalStatus;
		this.naturalness = naturalness;
		this.nacionality = nacionality;
		this.password = password;	
		this.name = name;
		this.surname = surname;
	}

	public String getAdmissionDateByAmericanPattern() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if (this.admissionDate != null) {
			String formattedString = this.admissionDate.format(formatter);
			return formattedString;
		}
		return null;
	}

	public void setAdmissionDateByAmericanPattern(String admissionDate) {
		if (admissionDate == null || admissionDate.isEmpty())
			return;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.admissionDate = LocalDate.parse(admissionDate, formatter);
	}

	public void setAdmissionDate(LocalDate admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getBirthDateByAmericanPattern() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if (this.birthDate != null) {
			String formattedString = this.birthDate.format(formatter);
			return formattedString;
		}
		return null;

	}

	public void setBirthDateByAmericanPattern(String admissionDate) {
		if (admissionDate != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			this.birthDate = LocalDate.parse(admissionDate, formatter);
		}
	}

	public boolean login(String password) {
		return Password.validatePassword(password, this);
	}

	public int getIdCollaborator() {
		return idCollaborator;
	}

	public void setIdCollaborator(int idCollaborator) {
		this.idCollaborator = idCollaborator;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Genre getgender() {
		return genre;
	}

	public void setgender(Genre gender) {
		this.genre = gender;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public LocalDate getLastVacationDate() {
		return lastVacationDate;
	}

	public void setLastVacationDate(LocalDate lastVacationDate) {
		this.lastVacationDate = lastVacationDate;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public boolean isNocturnalWork() {
		return nocturnalWork;
	}

	public void setNocturnalWork(boolean nocturnalWork) {
		this.nocturnalWork = nocturnalWork;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getNaturalness() {
		return naturalness;
	}

	public void setNaturalness(String naturalness) {
		this.naturalness = naturalness;
	}

	public String getNacionality() {
		return nacionality;
	}

	public void setNacionality(String nacionality) {
		this.nacionality = nacionality;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setRawPasswordHash(String password) {
		this.password = new BCryptPasswordEncoder().encode("Bearer" + password);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public LocalDate getAdmissionDate() {
		return admissionDate;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Collaborator) {
			Collaborator collab = (Collaborator) obj;

			return collab.getAdmissionDate().equals(admissionDate) && collab.getBirthDate().equals(birthDate)
					&& collab.getCpf().equals(cpf)
					&& (collab.getFatherName() != null & collab.getFatherName().equals(fatherName))
					// && collab.getIdCollaborator() == idCollaborator
					// && collab.getFk_contact() == contact && collab.getFk_endereco() == endereco
					// && collab.getFk_genre() == genre && collab.getFk_permission() == permission
					&& collab.getNacionality().equals(nacionality) && collab.getName().equals(name)
					&& collab.getNaturalness().equals(naturalness) && collab.getPassword().equals(password)
					&& collab.getRg().equals(rg) && collab.getSalary() == salary && collab.getSurname().equals(surname);
		}
		return false;
	}

	@Override
	public String toString() {
		return "\nID ______________________ " + idCollaborator + "\nName ____________________ " + name
				+ "\nSurname _________________ " + surname + "\nCPF _____________________ " + cpf
				+ "\nRG ______________________ " + rg + "\nSalary __________________ " + salary
				+ "\nBirth Date ______________ " + birthDate + "\nFather Name _____________ " + fatherName
				+ "\nMother Name _____________ " + motherName + "\nMarital Status __________ " + maritalStatus
				+ "\nNaturalness _____________ " + naturalness + "\nNacionality _____________ " + nacionality
				+ "\nAdmission Date __________ " + admissionDate + "\nLast Vacation Date ______ " + lastVacationDate
				+ "\nNoctural Shift __________ " + nocturnalWork + "\nfk_Contact ______________ " + contact
				+ "\nfk_Endereco _____________ " + endereco + "\nfk_Permission ___________ " + permission
				+ "\nfk_Gender _______________ " + genre + "\n";
	}

}
