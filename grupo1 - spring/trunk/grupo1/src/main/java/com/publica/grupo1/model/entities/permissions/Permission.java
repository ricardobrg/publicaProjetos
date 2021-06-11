package com.publica.grupo1.model.entities.permissions;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.publica.grupo1.model.entities.permissions.role.Department;
import com.publica.grupo1.model.entities.permissions.role.Role;

@Entity
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Enumerated(EnumType.STRING)
	private AccessLevel accessLevel;

	@ManyToOne
	private Role role;

	@ManyToOne
	private Department department;

	public Permission() {
	}

	public Permission(int id, AccessLevel accessLevel, Role role, Department department) {
		super();
		this.id = id;
		this.accessLevel = accessLevel;
		this.role = role;
		this.department = department;
	}

	/* GETTERS E SETTERS */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
