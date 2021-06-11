package com.publica.grupo1.model.entities.permissions;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Capability {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;

	public Capability() {} // DEFAULT CONSTRUCTOR
	
	public Capability(String name) {
		this.name = name;
	}
	
	public Capability(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	/* GETTERS E SETTERS */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
