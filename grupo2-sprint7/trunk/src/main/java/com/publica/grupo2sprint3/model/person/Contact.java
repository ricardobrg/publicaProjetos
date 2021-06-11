package com.publica.grupo2sprint3.model.person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.publica.grupo2sprint3.model.util.Validators;

@Entity
public class Contact {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String phone, email;
	
	public Contact() {
	}

	public Contact(String phone, String email) {
		this.phone = phone;
		this.email = email;
	}
	
	public Contact(int id, String phone, String email) {
		this.id = id;
		this.phone = phone;
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		if (Validators.isPhoneValid(phone) == true) {
			this.phone = phone;
		}
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (Validators.isEmailValid(email) == true) {
			this.email = email;
		}
	}
	
	/***
	 * Override of the toString method to inform all fields of this
	 * object.
	 */
	@Override
	public String toString() {
		return String.format(""
				+ "Email: %s\n"
				+ "Telefone: %s\n",
				this.email,
				this.phone);
	}
}
