package com.publica.grupo2sprint3.controller;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.dao.CollaboratorDAO;
import com.publica.grupo2sprint3.model.dao.RoleDAO;
import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Cep;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;
import com.publica.grupo2sprint3.model.person.physicalperson.Role;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;

public class PayrollControllerTest {

	Collaborator collabMain;
	PayrollController controller;
	
	Role role;
	Collaborator collab;
	Cep cep = Cep.getInstance("72322-108");

	Contact contact = new Contact("(91) 98181-8181", "carolsantos@publica.com");
	Address address = new Address(cep);

	@BeforeMethod
	public void setTest() {
		//GAMBIARRA(){
		Department department = new Department("Desenvolvimento");
		role = new Role("Desenvolvedor", department, 2000);
		role.setAccessLevel(AccessLevel.TOTAL);
		RoleDAO.getInstance().add(role);

		this.collabMain = new Collaborator("Caio Shimada", contact, address, "748.666.100-42", "510.87976.81-6", role, "20/05/1998", false,  0, 8);

		collab = new Collaborator("Caio Shimada", contact, address, "748.666.100-42", "510.87976.81-6", role, "20/05/1998", false,  0, 8);
		CollaboratorDAO.getInstance().add(collab);

		this.controller = PayrollController.getInstance(collabMain);
		// }
	}
	
	@AfterClass
	public void erase() {
		RoleDAO.getInstance().removeByIdentifier(role.getName());
		CollaboratorDAO.getInstance().removeByIdentifier(collab.getCpf());
	}

	@Test
	public void getInstanceTest() {
		Assert.assertEquals(controller.getClass(), 
				new PayrollController(collabMain).getClass());
	}

	@Test
	public void getAdvancedListTest() {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		Department department = collabMain.getRole().getDepartment();

		ArrayList<String> collabs = new ArrayList<String>();
		ArrayList<String> roles = new ArrayList<String>();
		ArrayList<String> departments = new ArrayList<String>();
		ArrayList<String> salarys = new ArrayList<String>();

		for(Collaborator collab : CollaboratorDAO.getInstance().getAll()) {
			if(collab.getRole().getDepartment() == department) {
				collabs.add(collab.getName());
				roles.add(collab.getRole().getName());
				departments.add(collab.getRole().getName());
				salarys.add(String.valueOf(collab.getExtraSalary() + collab.getRole().getSal()));
			}
		}
		result.add(collabs);
		result.add(roles);
		result.add(departments);
		result.add(salarys);

		//Assert.assertEquals(com.publica.grupo2sprint3.controller.getAdvancedList(), result);
	}

	@Test
	public void getSalaryTest() {
		Double x = 2000.00;
		for(Collaborator collab : CollaboratorDAO.getInstance().getAll()) {
			Assert.assertEquals(collab.getGrossSalary(),x);
		}
	}

	@Test
	public void getTotalListTest() {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();

		for(Collaborator collab : CollaboratorDAO.getInstance().getAll()) {
			ArrayList<String> auxList = new ArrayList<String>();
			auxList.add(collab.getName());
			auxList.add(collab.getRole().getName());
			auxList.add(collab.getRole().getDepartment().getName());
			auxList.add(String.valueOf(collab.getExtraSalary() + collab.getRole().getSal()));
			result.add(auxList);
		}

		//Assert.assertEquals(com.publica.grupo2sprint3.controller.getTotalList(), result);
	}

}
