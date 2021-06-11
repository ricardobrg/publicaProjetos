package com.publica.grupo2sprint3.model.person.physicalperson;

import javax.persistence.Entity;

import org.springframework.lang.NonNull;

import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.Person;
import com.publica.grupo2sprint3.model.util.Validators;

/**
 *	Class that extends Person. Represents a FisicaPerson.
 *
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
 *
 * Version: 1.0.0
 */

@Entity
public class PhysicalPerson extends Person {
	@NonNull
	private String cpf;
	
	private String pis;

	public PhysicalPerson() {} // DEFAULT CONSTRUCTOR
	
	public PhysicalPerson(String name, Contact contact, Address address, 
			String cpf, String pis) {
		
		super(name, contact, address);
		this.setCpf(cpf);
		//if(!(pis.equals(null)))
			//this.setPis(pis);
	}

	public PhysicalPerson(String name, String cpf) {
		this.name = name;
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		if (Validators.isCpfValid(cpf) == true && !cpf.equals(null) && !cpf.isEmpty()) {
			this.cpf = cpf;
		} else {
			cpf = this.cpf;
		}
	}

	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		if (Validators.isPisValid(pis) == true && !pis.isEmpty()) {
			this.pis = pis;
		}
	}

}
