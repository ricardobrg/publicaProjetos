package com.publica.grupo2sprint3.view;

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
import com.publica.grupo2sprint3.view.profileView.ProfileViewShow;

public class ProfileViewTest {

	Collaborator collab;
	Role role;
	
	Cep cep = Cep.getInstance("45245-452");

	Contact contact = new Contact("(91) 98181-8181", "carolsantos@publica.com");
	Address address = new Address(cep);
	
	
	@BeforeClass
	public void setTest() {
		Department department = new Department("Desenvolvimento");
		role = new Role("Desenvolvedor", department, 2000);
		role.setAccessLevel(AccessLevel.ADVANCED);
		
		collab = new Collaborator("Caio Shimada", contact, address, "741.918.140-36", "510.87976.81-6", role, "20/05/1998", 0, 8);
	}
	
	@Test
	public void viewProfileTest() {
		String expectedReturn = "Nome: " + collab.getName() + "\n"
				+ "Cargo: " + collab.getRole().getName() + "\n"
				+ "Carga Horária: " + collab.getWorkHours() + "h por dia \n"
				+ "CPF: " + collab.getCpf() + "\n"
				+ "Endereço: " + collab.getAddress().getLocality() + "\n"
				+ "Email: " + collab.getContact().getEmail() + "\n"
				+ "Pis: " + collab.getPis() + "\n";
		Assert.assertEquals(ProfileViewShow.getInstance(collab).getOutput(), expectedReturn);
	}
}
