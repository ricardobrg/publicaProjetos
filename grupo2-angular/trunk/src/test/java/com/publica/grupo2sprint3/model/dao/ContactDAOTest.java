package com.publica.grupo2sprint3.model.dao;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.person.Contact;


public class ContactDAOTest {
	ContactDAO dao = ContactDAO.getInstance();
	
	Contact contactMain1, contactMain2;
	String phone1, phone2;
	Contact contact = new Contact("(91) 98181-8181", "internet@gmail.com");
	Contact contact2 = new Contact("(47) 95123-8461", "teste@gmail.com");

	@Test
	public void addTest() {
		dao.add(contact);
		dao.add(contact2);
	}

	@Test
	public void findByIdTest() {
		Contact result =  (Contact) dao.findById(5);
		Contact result2 = (Contact) dao.findById(6);
		
		Assert.assertNotNull(result.getEmail());
		Assert.assertNotNull(result2.getEmail());
	}

	@Test
	public void findByIdentifierTest() {
		Assert.assertEquals(dao.findByIdentifier(contact.getPhone()).getPhone(),"(91) 98181-8181");
	}

	@Test
	public void removeByIdTest() {
		Assert.assertEquals(dao.removeById(5), true);
	}

	@Test
	public void removeByIdentifierTest() {
		Assert.assertEquals(dao.removeByIdentifier("(47) 95123-8461"), true);
	}

	@Test
	public void updateByIdTest() {
		Contact newContact = new Contact("(22) 91245-1265", "dada@gmail.com");
		Assert.assertEquals(dao.updateById(7, newContact), true);
		
		Contact newContact2 = new Contact("(22) 99845-1265", "carlos@gmail.com");
		Assert.assertEquals(dao.updateById(8, newContact2), true);
	}


	@Test
	public void updateByIdentifierTest() {
		Contact newContact = new Contact("(78) 12695-6578", "novoemail@gmail.com");
		Assert.assertEquals(dao.updateByIdentifier("(22) 91245-1265", newContact), true);
	}
	
	@Test
	public void getAllTest() {
		ArrayList<Contact> array = dao.getAll();
		Assert.assertNotNull(array);
	}

	@Test
	public void getInstanceTest() {
		Assert.assertEquals(dao.getClass(),
				new ContactDAO().getClass());
	}
}