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
import com.publica.grupo2sprint3.view.departmentView.DepartmentViewList;
import com.publica.grupo2sprint3.view.departmentView.DepartmentViewMenu;

public class DepartmentViewTest {
	
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
		String expectedReturn = "\n--------- Departamentos ---------\n" 
				+ "0. Voltar\n" 
				+ "1. Listar Departamentos\n"
				+ "2. Cadastrar Departamento\n";
		Assert.assertEquals(DepartmentViewMenu.getInstance(collab).getOutput(), expectedReturn);
	}
	
	@Test
	public void viewListTest() {
		ArrayList<Department> departments = new ArrayList<Department>();
		
		
		Department d1 = new Department("RH");
		Department d2 = new Department("Padaria");
		
		role = new Role("Padeiro", d2, 2000);
		//Collaborator manager = new Collaborator("Gerente", contact, address, "741.918.140-36", "510.87976.81-6", role, "20/05/1998", 0, 8);
		

		
		
		departments.add(d1);
		departments.add(d2);
		
		String expectedReturn = String.format("%-5s %-25s %-25s\n", "Id", "Gerente", "Departamento");
		for (int i = 0; i < departments.size(); i++) {
			try {
				expectedReturn += String.format("%-5d %-25s %-25s\n", i+1);
			} catch (Exception e) {
				expectedReturn += String.format("%-5d %-25s %-25s\n", i+1, "null", departments.get(i).getName());
			}

		}
		
		Assert.assertEquals(DepartmentViewList.getInstance(collab, departments).getOutput(), expectedReturn);
	}

}
