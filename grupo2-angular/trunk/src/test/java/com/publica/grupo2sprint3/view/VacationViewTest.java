package com.publica.grupo2sprint3.view;

import java.util.ArrayList;

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
import com.publica.grupo2sprint3.view.vacationView.VacationView;
import com.publica.grupo2sprint3.view.vacationView.VacationViewList;
import com.publica.grupo2sprint3.view.vacationView.VacationViewMenu;

public class VacationViewTest {

	VacationView vacationView;
	Collaborator collab;
	Role role;
	
	Cep cep = Cep.getInstance("45245-452");

	Contact contact = new Contact("(91) 98181-8181", "carolsantos@publica.com");
	Address address = new Address(cep);
	

	@BeforeClass
	public void setTest() {
		Department department = new Department("Desenvolvimento");
		role = new Role("Desenvolvedor", department, 2000);
		role.setAccessLevel(AccessLevel.TOTAL);

		collab = new Collaborator("Caio Shimada", contact, address, "741.918.140-36", "510.87976.81-6", role, "20/05/1998", 0, 8);

	}

	@Test
	public void viewMainMenuTest() {
		String expectedReturn = "\n--------- Gerenciador de Férias ---------\n" 
				+ "0. Voltar\n" 
				+ "1. Lista de Férias \n";
		Assert.assertEquals(VacationViewMenu.getInstance(collab).getOutput(), expectedReturn);
	}

	@Test
	public void viewListTest() {
		ArrayList<Collaborator> collabs = new ArrayList<Collaborator>();
		
		Collaborator collab2 = new Collaborator("Fulano de Tal", contact, address, "741.918.140-36", "510.87976.81-6", role, "20/05/2020", 0, 8);
		
		collabs.add(collab);
		collabs.add(collab2);
		
		String expectedReturn = String.format("%-5s %-25s %-25s %-25s %-25s \n", "Id", "Nome", "Pode tirar férias?",
					"Está de férias?", "Tempo de Férias");

		for (int i = 0; i < collabs.size(); i++) {
			String vacation = collabs.get(i).getCanVacation() ? "Pode" : "Não pode";
			String inVacation = collabs.get(i).getInVacation() ? "Está" : "Não está";
			expectedReturn += String.format("%-5d %-25s %-25s %-25s  %-25s \n", i + 1, collabs.get(i).getName(), vacation,
					inVacation, collabs.get(i).getVacationSize());
		}
		
		Assert.assertEquals(VacationViewList.getInstance(collab, collabs).getOutput(), expectedReturn);
	}
}
