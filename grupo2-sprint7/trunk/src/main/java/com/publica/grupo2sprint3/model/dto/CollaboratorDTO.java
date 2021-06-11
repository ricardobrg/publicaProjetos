package com.publica.grupo2sprint3.model.dto;

import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;


/***
 * Class responsible to create an CollaboratorDTO's object that is composed 
 * by a Collaborator's object, but it's limited with the informations that we want to show.
 * 
 * @author Augusto Kalahary <AuuKalaharyKW@gmail.com>
 *
 */
public class CollaboratorDTO {
	
	private String name, cpf, pis, admissionDate, lastVacation, vacationSize = "0";
	private Contact contact;
	private Address address;
	private boolean inVacation, canVacation;	
	private Double extraSalary = 0.00, grossSalary = 0.00;
	private int workHours;
	
	public CollaboratorDTO(Collaborator collab, boolean vacation) {
		if(vacation) {
			collab.generateVacationVars();
		}
		
		this.setName(collab.getName());
		this.setCpf(collab.getCpf());
		this.setPis(collab.getPis());
		this.setContact(collab.getContact());
		this.setAddress(collab.getAddress());
		this.setAdmissionDate(collab.getAdmissionDate());
		this.setLastVacation(collab.getLastVacation());
		this.setVacationSize(collab.getVacationSize());
		this.setInVacation(collab.getInVacation());
//		this.setCanVacation(collab.getCanVacation());
		this.setExtraSalary(collab.getExtraSalary());
		this.setGrossSalary(collab.getGrossSalary());
		this.setWorkHours(collab.getWorkHours());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		this.pis = pis;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getAdmissionDate() {
		return admissionDate;
	}
	
	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}
	
	public String getLastVacation() {
		return lastVacation;
	}
	
	public void setLastVacation(String lastVacation) {
		this.lastVacation = lastVacation;
	}
	
	public String getVacationSize() {
		return vacationSize;
	}
	
	public void setVacationSize(String vacationSize) {
		this.vacationSize = vacationSize;
	}
	
	public boolean getInVacation() {
		return inVacation;
	}
	
	public void setInVacation(boolean inVacation) {
		this.inVacation = inVacation;
	}
	
	public boolean getCanVacation() {
		return canVacation;
	}
	
	public void setCanVacation(boolean canVacation) {
		this.canVacation = canVacation;
	}
	
	public Double getExtraSalary() {
		return extraSalary;
	}
	
	public void setExtraSalary(Double extraSalary) {
		this.extraSalary = extraSalary;
	}
	
	public Double getGrossSalary() {
		return grossSalary;
	}
	
	public void setGrossSalary(Double grossSalary) {
		this.grossSalary = grossSalary;
	}

	public int getWorkHours() {
		return workHours;
	}

	public void setWorkHours(int workHours) {
		this.workHours = workHours;
	}
	
}

