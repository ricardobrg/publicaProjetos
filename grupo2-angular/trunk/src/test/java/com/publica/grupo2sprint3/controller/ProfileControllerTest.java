package com.publica.grupo2sprint3.controller;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.dao.CollaboratorDAO;
import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Cep;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;
import com.publica.grupo2sprint3.model.person.physicalperson.Role;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;

public class ProfileControllerTest {
	
	Collaborator collab;
	Role role;
	ProfileController controller;
	Cep cep = Cep.getInstance("72322-108");

	Contact contact = new Contact("(91) 98181-8181", "carolsantos@publica.com");
	Address address = new Address(cep);
	
	
	@BeforeClass
	public void setTest() {
		Department department = new Department("Desenvolvimento");
		role = new Role("Desenvolvedor", department, 2000);
		role.setAccessLevel(AccessLevel.TOTAL);
		
		collab = new Collaborator("Caio Shimada", contact, address, "741.918.140-36", "510.87976.81-6", role, "20/05/1998", 0, 8);
		collab.setPassword("123");
		
		CollaboratorDAO.getInstance().add(collab);
		
		controller = ProfileController.getInstance(collab);
	}
	
	@AfterClass
	public void erase() {
		CollaboratorDAO.getInstance().removeByIdentifier(collab.getCpf());
	}
	
	
	@Test
	public void updatePassword_True() {
		Assert.assertTrue(controller.update(collab.getCpf(), "abc"));
	}
}
