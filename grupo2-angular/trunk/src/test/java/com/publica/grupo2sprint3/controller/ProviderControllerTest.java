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

public class ProviderControllerTest {
	
	Collaborator collab;
	Role role;
	ProviderController controller;
	Cep cep = Cep.getInstance("745245-452");

	Contact contact = new Contact("(91) 98181-8181", "carolsantos@publica.com");
	Address address = new Address(cep);
	
	Department dpto;
	
	@BeforeClass
	public void setTest() {
		Department department = new Department("Desenvolvimento");
		role = new Role("Desenvolvedor", department, 2000);
		role.setAccessLevel(AccessLevel.TOTAL);
		
		collab = new Collaborator("Caio Shimada", contact, address, "741.918.140-36", "510.87976.81-6", role, "20/05/1998", 0, 8);
		
		controller = ProviderController.getInstance(collab);
	}
	
	@Test
	public void creation_deletion_find_test() {
		String name = "Nome Fantasia";
		String social = "Raz�o Social";
		String cnpj = "35.554.915/0001-23";
		String phone = "9198989-8989";
		String email = "email@gmail.com";
		String department = "Limpeza";
		double price = 5;
		String description = "Descri��o";
		
		contact = new Contact(phone, email);
		address = new Address(this.cep);
		dpto = new Department(department);
		
		String newCnpj = "71.023.241/0001-02";
		Assert.assertTrue(controller.add(name, social, contact, address, cnpj, dpto, price, description));
		Assert.assertNotNull(controller.find(cnpj));
		Assert.assertTrue(controller.update(cnpj, name, social, contact, address, newCnpj, dpto, price, description));
		Assert.assertFalse(controller.remove(cnpj));
		Assert.assertTrue(controller.remove(newCnpj));
	}

	@Test
	public void validateNewProvider_ValidProviderData_True() {
		String name = "Nome Fantasia";
		String social = "Raz�o Social";
		String cnpj = "79.249.690/0001-77";
		String phone = "9198989-8989";
		String email = "email@gmail.com";
		String department = "Limpeza";
		double price = 5;
		String description = "Descri��o";
		
		contact = new Contact(phone, email);
		address = new Address(this.cep);
		dpto = new Department(department);
		
		Assert.assertTrue(controller.isInputValid(name, social, contact, address, cnpj, dpto, price, description));
	}
	
	@Test
	public void validateNewProvider_InvalidCnpj_False() {
		String name = "Nome Fantasia";
		String social = "Raz�o Social";
		String cnpj = "00.123.123/0000-00";
		String phone = "9198989-8989";
		String email = "email@gmail.com";
		String department = "Limpeza";
		double price = 5;
		String description = "Descri��o";
		
		contact = new Contact(phone, email);
		address = new Address(this.cep);
		dpto = new Department(department);
		
		Assert.assertFalse(controller.isInputValid(name, social, contact, address, cnpj, dpto, price, description));
	}
	
	@Test
	public void validateNewProvider_InvalidPhone_False() {
		String name = "Nome Fantasia";
		String social = "Raz�o Social";
		String cnpj = "35.554.915/0001-23";
		String phone = "9198989-898912";
		String email = "email@gmail.com";
		String department = "Limpeza";
		double price = 5;
		String description = "Descri��o";
		
		contact = new Contact(phone, email);
		address = new Address(this.cep);
		dpto = new Department(department);
		
		Assert.assertFalse(controller.isInputValid(name, social, contact, address, cnpj, dpto, price, description));
	}
	
	@Test
	public void validateNewProvider_InvalidEmail_False() {
		String name = "Nome Fantasia";
		String social = "Raz�o Social";
		String cnpj = "35.554.915/0001-23";
		String phone = "9198989-8989";
		String email = "emailgmail.com";
		String department = "Limpeza";
		double price = 5;
		String description = "Descri��o";
		
		contact = new Contact(phone, email);
		address = new Address(this.cep);
		dpto = new Department(department);
		
		Assert.assertFalse(controller.isInputValid(name, social, contact, address, cnpj, dpto, price, description));
	}
	
	@Test
	public void validateNewProvider_InvalidCep_False() {
		String name = "Nome Fantasia";
		String social = "Raz�o Social";
		String cnpj = "00.000.000/0001-00";
		String phone = "9198989-8989";
		String email = "email@gmail.com";
		String department = "Limpeza";
		double price = 5;
		String description = "Descri��o";
		
		contact = new Contact(phone, email);
		address = new Address(this.cep);
		dpto = new Department(department);
		
		Assert.assertFalse(controller.isInputValid(name, social, contact, address, cnpj, dpto, price, description));
	}
}
