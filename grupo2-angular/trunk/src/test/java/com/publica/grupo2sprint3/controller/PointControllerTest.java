package com.publica.grupo2sprint3.controller;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Cep;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;
import com.publica.grupo2sprint3.model.person.physicalperson.Role;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;

public class PointControllerTest {
 
	Collaborator collab;
	Role role;
	PointController controller;
	Cep cep = Cep.getInstance("89025-400");
	
	Contact contact = new Contact("(91) 98282-8282", "teste@publica.com");
	Address address = new Address(cep);
	
	@BeforeClass
	public void setTest() {
		Department department = new Department("Desenvolvimento");
		role = new Role("Desenvolvedor", department, 2000);
		role.setAccessLevel(AccessLevel.TOTAL);
		
		collab = new Collaborator("Jesse Amaro", contact, address, "741.918.140-36", "510.87976.81-6", role, "27/07/1993", 0, 8);
		
		controller = PointController.getInstance(collab);
	}
	
	@Test
	public void addPoint_Test() {
		Assert.assertTrue(controller.add(collab));
	}
	
	
	
	
	
	
	
	
	
	
}
