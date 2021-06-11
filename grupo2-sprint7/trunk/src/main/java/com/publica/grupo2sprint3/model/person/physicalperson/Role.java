package com.publica.grupo2sprint3.model.person.physicalperson;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.lang.NonNull;

import com.publica.grupo2sprint3.model.util.Validators;


/**
 * Class that provide the name and the salary of a object.
 * Objects of this class will be used as attribute in Collaborator Class
 * 
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 * @author Pablo Mafessoli <mafessolip@gmail.com>
 * 
 * @author Jonathas Rocha de Souza <jonathasrochadesouza@gmail.com>
 * @author Caio Shimada <xcaiosr@gmail.com>
 * 
 * Version: 1.3.0
 */

@Entity
public class Role {
	@Id
	@GeneratedValue
	private int id;
	@NonNull
	private String nameRole;
	private Double salary = 0.00;
	private AccessLevel accessLevel;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Department department;
	
	public enum AccessLevel {
		TOTAL(1),
		ADVANCED(2),
		BASIC(3),
		ADMIN(4);
		
		public int id;
		
		AccessLevel(int id) {
			this.id = id;
		}
	}

	public Role() {}

	public Role(String name, Department department, double salary) {
		this.setSal(salary);
		this.nameRole = name;
		this.department = department;
		this.accessLevel = AccessLevel.BASIC;
	}
	
	public Role(AccessLevel access) {
		this.accessLevel = access;
	}
	
	public Role(String name, Department department, double salary, AccessLevel access) {
		this.setSal(salary);
		this.nameRole = name;
		this.department = department;
		this.accessLevel = access;
	}

	public Role(String name, double salary) {
		this.setSal(salary);
		this.nameRole = name;
	}

	public void setName(String name){
		this.nameRole = name;
	}

	public void setSal(double sal) {
		if (Validators.isSalaryValid(sal)) {
			this.salary = sal;
		} else {
			throw new IllegalArgumentException("O sal�rio informado n�o � valido!");	
		}
	}

	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getName() {
		return nameRole;
	}
	
	public double getSal() {
		return salary != null ? salary : 0.00;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public AccessLevel getAccessLevel() {
		return accessLevel;
	}
	
	
	public Department getDepartment() {
		return department;
	}
	
	/***
	 * Override of the toString method to inform all fields of this
	 * object.
	 */
	@Override
	public String toString() {
		return String.format(""
				+ "Cargo: %s\n"
				+ "%s", 
				this.nameRole,
				this.department.toString());
				
	}

}
















