package com.publica.grupo1.model.entities.permissions.role;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int workLoad;

	private String name;

	public Role() {
	} // DEFAULT CONSTRUCTOR

	public Role(int id, int workLoad, String name) {
		super();
		this.id = id;
		this.workLoad = workLoad;
		this.name = name;
	}

	/* GETTERS E SETTERS */
	public int getWorkLoad() {
		return workLoad;
	}

	public void setWorkLoad(int workLoad) {
		this.workLoad = workLoad;
	}

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
