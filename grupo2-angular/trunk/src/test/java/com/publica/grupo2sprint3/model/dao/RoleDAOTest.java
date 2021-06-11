package com.publica.grupo2sprint3.model.dao;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Cep;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;
import com.publica.grupo2sprint3.model.person.physicalperson.Role;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;

public class RoleDAOTest {

	RoleDAO dao = RoleDAO.getInstance();
	ArrayList<Role> array = new ArrayList<Role>();
	
	Cep cep = new Cep("89120-000");
	Address address = new Address(cep);
	Contact contact = new Contact("(47) 99587-8421", "pessoabcd@gmail.com");
	Collaborator manager = new Collaborator("Pessoa",contact, address);
	Collaborator manager2 = new Collaborator("Manager2", "769.576.080-34");
	Department department = new Department("Desenvolvimento");
	Department department2 = new Department("Gestão");
	Role newRole = new Role("Desenvolvedor Java", department2, 4000.00);


	public void startDao() {
		dao.add(newRole);
	}

	@Test
	public void addTest() {
		manager.setCpf("029.982.880-81");
		RoleDAO dao = RoleDAO.getInstance();
		Role role = new Role("Desenvolvedor Java", department, 3000.00, AccessLevel.BASIC);
		Role role2 = new Role("Desenvolvedor Python", department, 3000.00, AccessLevel.BASIC);
		Role role3 = new Role("Desenvolvedor Delphi", department, 3000.00, AccessLevel.BASIC);
		Role role4 = new Role("Administrador", department2, 3000.00, AccessLevel.TOTAL);
		Assert.assertEquals(dao.add(role), true);
		Assert.assertEquals(dao.add(role2), true);
		Assert.assertEquals(dao.add(role3), true);
		Assert.assertEquals(dao.add(role4), true);
	}

	@Test
	public void findByIdTest() {
		RoleDAO dao = RoleDAO.getInstance();
		Role result = (Role) dao.findById(214);
		Assert.assertEquals(result.getName(), "Desenvolvedor Python");
	}

	@Test
	public void findByIdentifierTest() {
		RoleDAO dao = RoleDAO.getInstance();
		Role result = (Role) dao.findByIdentifier("Desenvolvedor Java");
		Assert.assertEquals(result.getName(), "Desenvolvedor Java");
	}
	
	@Test
	public void updateByIdTest() {
		RoleDAO dao = RoleDAO.getInstance();
		Role role = (Role) dao.findById(212);
		role.setName("Desenvolvedor C");
		Assert.assertEquals(dao.updateById(212, role), true);
	}

	@Test
	public void updateByIdentifierTest() {
		RoleDAO dao = RoleDAO.getInstance();

		String identifier = "Desenvolvedor";
		String newName = "Desenvolvedor Java";
		Role result = (Role) RoleDAO.getInstance().findByIdentifier(identifier);
		result.setName(newName);
		dao.updateByIdentifier(identifier, result);
		Role updatedResult = (Role) dao.findByIdentifier(newName);
		Assert.assertNotNull(updatedResult);
		
		Assert.assertNotNull(result.getId());
		Assert.assertEquals(result.getId(), updatedResult.getId());
	}


	@Test
	public void removeByIdTest() {
		RoleDAO dao = RoleDAO.getInstance();
		Assert.assertEquals(dao.removeById(206), true);
	}

	@Test
	public void removeByIdentifierTest() {
		RoleDAO dao = RoleDAO.getInstance();
		Assert.assertEquals(dao.removeByIdentifier("Desenvolvedor C"), true);
	}
	
	@Test
	public void getAllTest() {
		RoleDAO dao = RoleDAO.getInstance();
		ArrayList<Role> array = dao.getAll();
		Assert.assertNotNull(array);
	}

	@AfterMethod
	public void clear() {
		array.clear();
	}
}
