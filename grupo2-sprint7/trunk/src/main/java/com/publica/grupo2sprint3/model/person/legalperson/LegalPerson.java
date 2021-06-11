package com.publica.grupo2sprint3.model.person.legalperson;

import javax.persistence.Entity;

import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.Person;
import com.publica.grupo2sprint3.model.util.Validators;


/**
 * Class that extends Person. Represents a JuridicaPerson.
 * 
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
 *
 * @version 1.0.0	
 */

@Entity
public class LegalPerson extends Person {
	
	private String cnpj;
	private String socialReason;  

	public LegalPerson() {} // DEFAULT CONSTRUCTOR
	public LegalPerson(String name, /* read as 'fantasyName' */
			Contact contact, Address address, String socialReason, String cnpj){

		super(name, contact, address);
		this.socialReason = socialReason; 
		this.setCnpj(cnpj);
	}

	public LegalPerson(String name, Contact contact) {
		this.name = name; 
		this.contact = contact;
	}

	public String getSocialReason() {
		return socialReason;
	}

	public void setSocialReason(String socialReason) {
		this.socialReason = socialReason;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		if (Validators.isCnpjValid(cnpj) == true) {
			this.cnpj = cnpj;
		}
	}


}

