package com.publica.grupo1.model.entities.contact;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import com.publica.grupo1.util.validation.text.EmailValidation;

/***
 * This object contains information commonly used for contact, a String for
 * email and telephone number.
 * 
 * Currently used for the com.publica.grupo1.person.Person class and its
 * subclasses.
 * 
 * @version 0.0.1
 * 
 * @author Pedro Vinicius Hostert <pedrohostertt@gmail.com>
 */

@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String email;

	@OneToMany
	@OrderColumn
	private NumberPhone[] phoneList = new NumberPhone[2];

	public Contact(String email) {
		if (EmailValidation.emailValidation(email))
			this.email = email;

	}

	public Contact() {

	}

	public int getId() {
		return id;
	}

	public void setId(int idContact) {
		this.id = idContact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (EmailValidation.emailValidation(email))
			this.email = email;
	}

	public  NumberPhone[] getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(NumberPhone[] phoneList) {
		this.phoneList = phoneList;
	}

}
