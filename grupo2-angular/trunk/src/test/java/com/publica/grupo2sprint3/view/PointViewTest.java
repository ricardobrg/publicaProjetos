package com.publica.grupo2sprint3.view;

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
import com.publica.grupo2sprint3.view.pointView.PointViewMenu;

public class PointViewTest {

	Collaborator collab;
	Role role;
	PointViewMenu pointViewMenu;
	
	Cep cep = Cep.getInstance("89025-420");

	Contact contact = new Contact("(91) 98181-8181", "carolsantos@publica.com");
	Address address = new Address(cep);
	
	@BeforeMethod
	public void setTest() {
		Department department = new Department("Desenvolvimento");
		role = new Role("Desenvolvedor", department, 2000);
		role.setAccessLevel(AccessLevel.TOTAL);
		
		collab = new Collaborator("Caio Shimada", contact, address, "741.918.140-36", "510.87976.81-6", role, "20/05/1998", 0, 8);
		pointViewMenu = new PointViewMenu(collab);
		//Point point1 = new Point(collab, "11/11/2020", 560);
		//Point point2 = new Point(collab, "12/11/2020", 660);
	}
	
	@Test
	public void viewMainMenuTest() {
		String expectedReturn = "\n--------- Registro de Pontos ---------\n" + 
				"\n# Informe o número da opção desejada: \n"+
				"\n0. Voltar\n" +
				"1. Novo Registro de Ponto\n" +
				"2. Consultar Registros de Ponto\n" + 
				"3. Consultar Registros por Setor\n" + 
				"4. Editar Registros de Ponto\n" +
				"5. Deletar Registros de Ponto\n";
		Assert.assertEquals(pointViewMenu.getOutput(), expectedReturn);
	}
}
