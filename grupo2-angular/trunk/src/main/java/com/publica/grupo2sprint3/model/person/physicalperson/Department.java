package com.publica.grupo2sprint3.model.person.physicalperson;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.lang.NonNull;



/**
 * This class implements the com.publica.grupo2sprint3.model of the department. It defines all of its attributes
 * 
 * @version 2.0.0
 * 
 * @author Augusto Kalahary <AuuKalaharyKW@gmail.com>
 * @author Diego Borba <diegoborba25@gmail.com>
 *
 */

@Entity
public class Department {
	@Id
	@GeneratedValue
	private int id;
	@NonNull
	private String name;
	
	/**
	 * Initializes a new Department object with its name
	 * 
	 * @param name	department's name
	 */
	public Department(String name) {
		this.setName(name);
	}

	public Department(Department depart) {
		this.setName(depart.getName());
	}
	
	public Department() {
		
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
		this.name = name;
	}
	
	/***
	 * Override of the toString method to inform all fields of this
	 * object.
	 */
	@Override
	public String toString() {
		return String.format("Departamento: %s\n", this.name);
	}
	
}



















