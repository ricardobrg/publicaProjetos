package com.publica.grupo2sprint3.model.person;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.lang.NonNull;

/**
 * Superclass that allows inheritance from Person to FisicaPerson and JuridicaPerson.
 * 
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
 *
 * Version: 1.0.0
 */

@Entity
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	@NonNull
	protected String name; 
	
	@OneToOne(cascade=CascadeType.ALL)
	protected Contact contact;
	
	@ManyToOne(cascade=CascadeType.ALL)
	protected Address address;
	
	public Person() {} // DEFAULT CONSTRUCTOR

	public Person(String name, Contact contact, Address address) {
		this.name = name;
		this.contact = contact;
		this.address = address;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (!name.equals(null) && !name.isEmpty()) {
			this.name = name;
		}
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
}



