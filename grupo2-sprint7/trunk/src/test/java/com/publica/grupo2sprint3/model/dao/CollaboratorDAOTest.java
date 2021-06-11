package com.publica.grupo2sprint3.model.dao;

import java.util.ArrayList;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Cep;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;
import com.publica.grupo2sprint3.model.person.physicalperson.Role;

public class CollaboratorDAOTest {
	
	Department department = new Department("Desenvolvimento");
	Department department2 = new Department("Suporte");
	Role role = new Role("Desenvolvedor", department, 2000);
	
	Role role2 = new Role("Suporte", department2, 2000);
	Cep cep2 = Cep.getInstance("89107-000");
	
	Contact contact2 = new Contact("(91) 98181-8181", "jesse.amaro@gmail.com");
	Address address2 = new Address(cep2);
	
	@Test
	public void addTest() { 
		CollaboratorDAO dao =  CollaboratorDAO.getInstance();
		Cep cep = new Cep("89120-000");
		
		Address address = new Address(cep);
		Contact contact = new Contact("(91) 98181-8181", "abababababa@gmail.com");
		Collaborator admin = new Collaborator("Pessoa",contact, address);
		admin.setCpf("748.666.100-42");
		Assert.assertEquals(dao.add(admin), true);
	}
	
	@Test
	public void findByIdentifierTest() { 
		CollaboratorDAO dao =  CollaboratorDAO.getInstance();
		Collaborator result = (Collaborator)dao.findByIdentifier("124.192.319-10");
		Assert.assertEquals(result.getCpf(), "124.192.319-10");
	}
	
	@Test
	public void findByIdTest() { 
		CollaboratorDAO dao =  CollaboratorDAO.getInstance();
		Collaborator result = dao.findById(9);
		Assert.assertEquals(result.getName(), "Pessoa");
	}
	
	@Test
	public void removeByIdentifierTest() { 
		CollaboratorDAO dao =  CollaboratorDAO.getInstance();
		Assert.assertEquals(dao.removeByIdentifier("748.666.100-42"), true);
	}
	
	@Test
	public void removeByIdTest() { 
		CollaboratorDAO dao =  CollaboratorDAO.getInstance();
		Assert.assertEquals(dao.removeById(10), true);
	}

	@Test
	public void updateByIdentifierTest() { 
		CollaboratorDAO dao =  CollaboratorDAO.getInstance();
		Collaborator collab = new Collaborator("Caio Shimada", "080.122.000-90");
		Assert.assertEquals(dao.updateByIdentifier("748.666.100-42", collab), true);
	}
	
	@Test
	public void updateByIdTest() { 
		CollaboratorDAO dao =  CollaboratorDAO.getInstance();
		Collaborator collab = new Collaborator("Caio Shimada", "748.666.100-42");
		Assert.assertEquals(dao.updateById(10, collab), true);
	}
	
	@Test
	public void getAllTest() {
		CollaboratorDAO dao =  CollaboratorDAO.getInstance();
		ArrayList<Collaborator> array = dao.getAll();
		Assert.assertNotNull(array);	
	}
}