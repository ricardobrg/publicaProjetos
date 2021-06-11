package com.publica.grupo2sprint3.controller;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Cep;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;
import com.publica.grupo2sprint3.model.person.physicalperson.Role;

public class RoleControllerTest {
	
	Cep cep = Cep.getInstance("745245-452");

	Contact contact = new Contact("(91) 98181-8181", "carolsantos@publica.com");
	Address address = new Address(cep);
	public Department department,departmentFalse = new Department();
	
	@BeforeTest
	public void setTest() {
		Department department1 = new Department("Desenvolvimento");
		
		Role role1 = new Role("Desenvolvedor", department1, 2000);
		Collaborator maneger = new Collaborator("Caio Shimada", contact, address, "748.666.100-42", "510.87976.81-6", role1, "20/05/1998", false, 0,
				8);
		department = new Department("Desenvolvimento",maneger);
		departmentFalse = new Department("404", maneger);
	}

	@Test
	public void testCreateNewRole_True() {
		Collaborator collab = new Collaborator();
		RoleController roleController = new RoleController(collab);
		
		String role = "Recruiter";
		double salary = 6000;

		assertTrue(roleController.inputRole(role, department, salary));
	}

	@Test
	public void testCreateNewRole_FalseNameRole() {
		Collaborator collab = new Collaborator();
		RoleController roleController = new RoleController(collab);
		
		String role = "R2-D2";
		double salary = 6000;
		
		assertFalse(roleController.inputRole(role, department, salary));
	}

	@Test
	public void testCreateNewRole_FalseNameDepartment() {
		Collaborator collab = new Collaborator();
		RoleController roleController = new RoleController(collab);
		
		String role = "Developer";
		double salary = 6000;
	
		
		assertFalse(roleController.inputRole(role, departmentFalse, salary));
	}

	@Test
	public void testCreateNewRole_FalseSalary() {
		Collaborator collab = new Collaborator();
		RoleController roleController = new RoleController(collab);
		
		String role = "Developer";
		double salary = -12000;
		
		assertFalse(roleController.inputRole(role, department, salary));
	}

}
