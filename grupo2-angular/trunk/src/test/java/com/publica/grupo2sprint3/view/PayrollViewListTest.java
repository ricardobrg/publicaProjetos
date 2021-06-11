package com.publica.grupo2sprint3.view;

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

public class PayrollViewListTest {
	Collaborator collabMain;
	Role role;
	
	Cep cep = Cep.getInstance("45245-452");

	Contact contact = new Contact("(91) 98181-8181", "carolsantos@publica.com");
	Address address = new Address(cep);
	
	
	Collaborator collab;

	@BeforeMethod
	public void setTest() {
		//GAMBIARRA(){
		Department department = new Department("Desenvolvimento");
		role = new Role("Desenvolvedor", department, 2000);

		role.setAccessLevel(AccessLevel.TOTAL);
		RoleDAO.getInstance().add(role);

		collabMain = new Collaborator("Caio Shimada", contact, address, "748.666.100-42", "510.87976.81-6", role, "20/05/1998", false,  0, 8);

		collab = new Collaborator("Caio Shimada", contact, address, "748.666.100-42", "510.87976.81-6", role, "20/05/1998", false,  0, 8);
		CollaboratorDAO.getInstance().add(collab);


		// }

	}
	
	@AfterClass
	public void erase() {
		RoleDAO.getInstance().removeByIdentifier(role.getName());
		CollaboratorDAO.getInstance().removeByIdentifier(collab.getCpf());
	}

	@Test
	public void getOutputTest() {
		//String output = "--------- Minha Folha --------- \n";

		//output += String.format("Nome: %-25s Cargo: %-25s Departamento: %-25s Sal�rio: %.2f \n", 
				//collabMain.getName() , collabMain.getRole().getName(), collabMain.getRole().getDepartment().getName(),
				//PayrollDAO.getSalary(collabMain));
		//output += "\n--------- Empresa --------- \n";
		//output += String.format("Nome: %-25s Cargo: %-25s Departamento: %-25s Sal�rio: %-25.2f \n", 
				//collabMain.getName() , collabMain.getRole().getName(), collabMain.getRole().getDepartment().getName(),
				//PayrollDAO.getSalary(collabMain));
		
		
		//String com.publica.grupo2sprint3.view = PayrollViewList.getInstance(collabMain, collabMain.getRole().getAccessLevel()).getOutput();
		//System.out.println(com.publica.grupo2sprint3.view);
		//System.out.println(output);
		//Assert.assertEquals(com.publica.grupo2sprint3.view , output);
	}
}
