package com.publica.grupo2sprint3.view;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Cep;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;
import com.publica.grupo2sprint3.model.person.physicalperson.Role;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;


public class HomeViewTest {
	
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
	public void getDisplayOutput() {
		String expectedReturn = "--------- P�gina Inicial ---------\n"
				+ "0. Logout\n"
				+ "1. Perfil\n"
				+ "2. Registro de Pontos\n"
				+ "3. Fechamento do M�s\n"
				+ "4. Colaboradores\n"
				+ "5. Controle de F�rias\n";
		
		Assert.assertEquals(HomeView.getInstance(collab).getOutput(), expectedReturn);
	}
}
