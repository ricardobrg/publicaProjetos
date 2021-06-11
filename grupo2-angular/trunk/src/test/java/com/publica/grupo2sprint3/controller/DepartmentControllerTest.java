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

public class DepartmentControllerTest {
	
	Collaborator collab;
	Role role;
	DepartmentController controller;
	
	Department padaria;
	Role padeiro;
	Role garcom;
	Collaborator managerPadaria;
	Collaborator funcionarioPadaria;
	
	Cep cep = Cep.getInstance("72322-108");
	Contact contact = new Contact("(91) 98181-8181", "carolsantos@publica.com");
	Address address = new Address(cep);
	
	@BeforeClass
	public void setTest() {
		Department department = new Department("Desenvolvimento");
		role = new Role("Desenvolvedor", department, 2000);
		role.setAccessLevel(AccessLevel.TOTAL);
		
		collab = new Collaborator("Caio Shimada", contact, address, "572.982.300-26", "510.87976.81-6", role, "20/05/1998", 0, 8);
		
		controller = DepartmentController.getInstance(collab);
		
		padaria = new Department("Padaria");
		padeiro = new Role("Padeiro", padaria, 20000, AccessLevel.ADVANCED);
		garcom = new Role("Gar�om", padaria, 1000, AccessLevel.BASIC);
		
		managerPadaria = new Collaborator("Caio Shimada", contact, address, "399.024.800-63", "510.87976.81-6", padeiro, "20/05/1998", 0, 8);
		funcionarioPadaria = new Collaborator("Caio Shimada", contact, address, "741.918.140-36", "510.87976.81-6", garcom, "20/05/1998", 0, 8);
		
		CollaboratorDAO.getInstance().add(collab);
		CollaboratorDAO.getInstance().add(managerPadaria);
		CollaboratorDAO.getInstance().add(funcionarioPadaria);
	}
	
	@AfterClass
	public void erase() {
		CollaboratorDAO.getInstance().removeByIdentifier(collab.getCpf());
		CollaboratorDAO.getInstance().removeByIdentifier(managerPadaria.getCpf());
		CollaboratorDAO.getInstance().removeByIdentifier(funcionarioPadaria.getCpf());
	}
	
	@Test
	public void creation_deletion_find_test() {
		String name = "Padaria";
		
		String newName = "Mercado";
		Assert.assertTrue(controller.add(name));
		Assert.assertNotNull(controller.find(name));
		Assert.assertTrue(controller.update(name, newName, managerPadaria.getCpf()));
		Assert.assertFalse(controller.remove(name));
		Assert.assertTrue(controller.remove(newName));
	}
	
	@Test
	public void isInputValid_ValidDepartment_True() {
		String name = "Departamento 2";
		
		Assert.assertTrue(controller.isInputValid(name));
	}
	
	@Test
	public void isInputValid_ValidManager_True() {
		Assert.assertTrue(controller.isInputValid("RH", managerPadaria.getCpf()));
	}
	
	@Test
	public void isInputValid_InvalidManager_False() {
		Assert.assertFalse(controller.isInputValid("RH", funcionarioPadaria.getCpf()));
	}
}
