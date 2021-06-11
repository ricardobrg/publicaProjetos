package com.publica.grupo2sprint3.view;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Cep;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;
import com.publica.grupo2sprint3.model.person.physicalperson.Role;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;
import com.publica.grupo2sprint3.view.collaboratorView.CollaboratorViewList;
import com.publica.grupo2sprint3.view.collaboratorView.CollaboratorViewMenu;
import com.publica.grupo2sprint3.view.collaboratorView.CollaboratorViewShow;

public class CollaboratorViewTest {
	
	Collaborator collab;
	Role role;
	
	Cep cep = Cep.getInstance("89025-420");

	Contact contact = new Contact("(91) 98181-8181", "carolsantos@publica.com");
	Address address = new Address(cep);

	
	@BeforeMethod
	public void setTest() {
		Department department = new Department("Desenvolvimento");
		role = new Role("Desenvolvedor", department, 2000);
		role.setAccessLevel(AccessLevel.TOTAL);
		
		collab = new Collaborator("Caio Shimada", contact, address, "741.918.140-36", "510.87976.81-6", role, "20/05/1998", 0, 8);
	}
	
	@Test
	public void viewMainMenuTest() {
		String expectedReturn = "\n--------- Colaboradores ---------\n" + 
				"0. Voltar\n" +
				"1. Lista de Colaboradores\n" +
				"2. Cadastrar Novo Colaborador\n";
		Assert.assertEquals(CollaboratorViewMenu.getInstance(collab).getOutput(), expectedReturn);
	}
	
	@Test
	public void viewListTest() {
		ArrayList<Collaborator> collabs = new ArrayList<Collaborator>();
		
		Collaborator collab2 = new Collaborator("Fulano de Tal", contact, address, "794.141.770-78", "510.87976.81-6", role, "20/05/1999", 0, 8);
		
		collabs.add(collab);
		collabs.add(collab2);
		
		String expectedReturn = "";
		expectedReturn += String.format("%-5s %-25s %-25s %-25s\n", "Id", "Nome", "CPF", "Cargo");
		
		for (int i = 0; i < collabs.size(); i++) {
			expectedReturn += String.format("%-5d %-25s %-25s %-25s\n", i, collabs.get(i).getName(), collabs.get(i).getCpf(),
					collabs.get(i).getRole().getName());
		}
		System.out.println(CollaboratorViewList.getInstance(collab, collabs).getOutput());
		Assert.assertEquals(CollaboratorViewList.getInstance(collab, collabs).getOutput(), expectedReturn);
	}

	@Test
	public void viewShowTest() {
		
		String expectedReturn =  "\n--------- Perfil de " + collab.getName() + " ---------\n"
				+ "Nome: " + collab.getName() + "\n"
				+ "Cargo: " + collab.getRole().getName() + "\n"
				+ "Carga Horária: " + collab.getWorkHours() + "h por dia \n"
				+ "CPF: " + collab.getCpf() + "\n"
				+ "Endereço: " + collab.getAddress()  + "\n"
				+ "Email: "	+ collab.getContact().getEmail() + "\n"
				+ "Pis: " + collab.getPis() + "\n";
		
		Assert.assertEquals(CollaboratorViewShow.getInstance(collab, collab).getOutput(), expectedReturn);
	}
}
