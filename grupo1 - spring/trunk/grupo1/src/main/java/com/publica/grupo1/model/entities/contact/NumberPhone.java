package com.publica.grupo1.model.entities.contact;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.publica.grupo1.util.validation.number.TelephoneNumber;

@Entity
public class NumberPhone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String dd;
	private String number;

	public NumberPhone(String dd, String number) {
		this.dd = dd;
		this.setNumber(number);
	}
	
	public NumberPhone() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDd() {
		return dd;
	}

	public void setDd(String dd) {
		this.dd = dd;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String retornaTelefoneFormatado(String dd, String number) {
		String formatedPhone = "(" + dd + ") " + number;
		if (TelephoneNumber.validateTelephone(formatedPhone))
			return formatedPhone;
		return null;

	}

}
