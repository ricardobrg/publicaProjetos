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

public class CollaboratorControllerTest {

	Collaborator collab;
	Role role;
	CollaboratorController controller;
	Cep cep = Cep.getInstance("72322-108");
	
	Contact contact = new Contact("(91) 98181-8181", "carolsantos@publica.com");
	Address address = new Address(cep);
	
	
	@BeforeClass
	public void setTest() {
		Department department = new Department("Desenvolvimento");
		role = new Role("Desenvolvedor", department, 2000);
		role.setAccessLevel(AccessLevel.TOTAL);
		
		collab = new Collaborator("Caio Shimada", contact, address, "741.918.140-36", "510.87976.81-6", role, "20/05/1998", 0, 8);
		
		controller = CollaboratorController.getInstance(collab);
	}
	
	@Test
	public void creation_deletion_find_test() {
		String name = "Shimada";
		String email = "valido@gmail.com";
		String cpf = "198.089.050-13";
		String phone = "(47) 98989-8989";
		String pis = "566.16992.41-1";
		Role role = new Role("Desenvolvimento", new Department("Desenvolvimento"), 3000, AccessLevel.TOTAL);
		double salary = 0;
		int workload = 8;
		
		contact = new Contact(phone, email);
		address = new Address(this.cep);
		
		String newCpf = "099.183.650-21";
		Assert.assertTrue(controller.add(name, contact, address, cpf, pis, role, salary, workload));
		Assert.assertNotNull(controller.find(cpf));
		Assert.assertTrue(controller.update(cpf, name, contact, address, newCpf, pis, role, "01/12/2020", salary, workload));
		Assert.assertFalse(controller.remove(cpf));
		Assert.assertTrue(controller.remove(newCpf));
	}
	
	@Test
	public void validateNewCollaborator_ValidCollaboratorData_True() {
		String name = "Shimada";
		String email = "valido@gmail.com";
		String cpf = "198.089.050-13";
		String phone = "(47) 98989-8989";
		String pis = "566.16992.41-1";
		Role role = new Role("Desenvolvimento", new Department("Desenvolvimento"), 3000, AccessLevel.TOTAL);
		double salary = 0;
		int workload = 8;
		
		contact = new Contact(phone, email);
		address = new Address(this.cep);
		
		Assert.assertTrue(controller.isInputValid(name, contact, address, cpf, pis, role, salary, workload));
	}
	
	@Test
	public void validateNewCollaborator_InvalidEmail_False() {
		String name = "Shimada";
		String email = "valido@gmail";
		String cpf = "198.089.050-13";
		String phone = "(47) 98989-8989";
		String pis = "566.16992.41-1";
		Role role = new Role("Desenvolvimento", new Department("Desenvolvimento"), 3000, AccessLevel.TOTAL);
		double salary = 0;
		int workload = 8;
		
		contact = new Contact(phone, email);
		address = new Address(this.cep);
		
		Assert.assertFalse(controller.isInputValid(name, contact, address, cpf, pis, role, salary, workload));
	}
	
	@Test
	public void validateNewCollaborator_InvalidCpf_False() {
		String name = "Shimada";
		String email = "valido@gmail.com";
		String cpf = "555.555.555-55";
		String phone = "(47) 98989-8989";
		String pis = "566.16992.41-1";
		Role role = new Role("Desenvolvimento", new Department("Desenvolvimento"), 3000, AccessLevel.TOTAL);
		double salary = 0;
		int workload = 8;
		
		contact = new Contact(phone, email);
		address = new Address(this.cep);
		
		Assert.assertFalse(controller.isInputValid(name, contact, address, cpf, pis, role, salary, workload));
	}
	
	@Test
	public void validateNewCollaborator_InvalidPhone_False() {
		String name = "Shimada";
		String email = "valido@gmail.com";
		String cpf = "198.089.050-13";
		String phone = "478989-89898";
		String pis = "566.16992.41-1";
		Role role = new Role("Desenvolvimento", new Department("Desenvolvimento"), 3000, AccessLevel.TOTAL);
		double salary = 0;
		int workload = 8;
		
		contact = new Contact(phone, email);
		address = new Address(this.cep);
		
		Assert.assertFalse(controller.isInputValid(name, contact, address, cpf, pis, role, salary, workload));
	}	
	
	@Test
	public void validateNewCollaborator_InvalidCep_False() {
		String name = "Shimada";
		String email = "valido@gmail.com";
		String cpf = "000.000.000-12";
		String phone = "(47) 98989-8989";
		String pis = "566.16992.41-1";
		Role role = new Role("Desenvolvimento", new Department("Desenvolvimento"), 3000, AccessLevel.TOTAL);
		double salary = 0;
		int workload = 8;
		
		contact = new Contact(phone, email);
		address = new Address(this.cep);
		
		Assert.assertFalse(controller.isInputValid(name, contact, address, cpf, pis, role, salary, workload));
	}	
	
	@Test
	public void validateNewCollaborator_InvalidPis_False() {
		String name = "Shimada";
		String email = "valido@gmail.com";
		String cpf = "198.089.050-13";
		String phone = "(47) 98989-8989";
		String pis = "555.55555.55-5";
		Role role = new Role("Desenvolvimento", new Department("Desenvolvimento"), 3000, AccessLevel.TOTAL);
		double salary = 0;
		int workload = 8;
		
		contact = new Contact(phone, email);
		address = new Address(this.cep);
		
		Assert.assertFalse(controller.isInputValid(name, contact, address, cpf, pis, role, salary, workload));
	}	
	
	@Test
	public void validateNewCollaborator_InvalidSalary_False() {
		String name = "Shimada";
		String email = "valido@gmail.com";
		String cpf = "198.089.050-13";
		String phone = "(47) 98989-8989";
		String pis = "566.16992.41-1";
		Role role = new Role("Desenvolvimento", new Department("Desenvolvimento"), 3000, AccessLevel.TOTAL);
		double salary = -5;
		int workload = 8;
		
		contact = new Contact(phone, email);
		address = new Address(this.cep);
		
		Assert.assertFalse(controller.isInputValid(name, contact, address, cpf, pis, role, salary, workload));
	}
	
	@Test
	public void validateNewCollaborator_InvalidWorkload_False() {
		String name = "Shimada";
		String email = "valido@gmail.com";
		String cpf = "198.089.050-13";
		String phone = "(47) 98989-8989";
		String pis = "566.16992.41-1";
		Role role = new Role("Desenvolvimento", new Department("Desenvolvimento"), 3000, AccessLevel.TOTAL);
		double salary = 0;
		int workload = -1;
		
		contact = new Contact(phone, email);
		address = new Address(this.cep);
		
		Assert.assertFalse(controller.isInputValid(name, contact, address, cpf, pis, role, salary, workload));
	}
}
