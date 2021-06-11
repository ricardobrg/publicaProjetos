package com.publica.grupo2sprint3.view;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;
import com.publica.grupo2sprint3.model.person.physicalperson.Role;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;
import com.publica.grupo2sprint3.view.roleView.RoleViewMenu;

public class RoleViewTest {

	Collaborator collab;
	Role role;

	@BeforeMethod
	public void setTest() {
		collab = new Collaborator();

		Department department = new Department("Desenvolvimento");
		role = new Role("Desenvolvedor Java Full jr", department, 6000);

		role.setAccessLevel(AccessLevel.TOTAL);
		collab.setRole(role);
	}

	@Test
	public void testViewTotalUser() {
		String expectedOutput = ("\n--------- Cargos ---------\n" +
				"0. Voltar\n" + 
				"1. Consulta dos Cargos\n" +
				"2. Cadastro de Cargos\n" +
				"3. Editar Cargos\n" +
				"4. Deletar Cargos\n");

		Assert.assertEquals(RoleViewMenu.getInstance(collab).getOutput(), expectedOutput);
	}
}
