package com.publica.grupo1.model.dao.contact;

import java.util.List;

import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.publica.grupo1.database.DbConfig;
import com.publica.grupo1.database.TestDBConnection;
import com.publica.grupo1.model.entities.collaborator.Collaborator;
import com.publica.grupo1.model.entities.contact.Contact;
import com.publica.grupo1.model.entities.contact.NumberPhone;
import com.publica.grupo1.model.entities.payroll.Payroll;
import com.publica.grupo1.model.entities.point.Point;
import com.publica.grupo1.model.entities.vacations.TimeOffTracker;

public class ContactDAOTest {

	private int id;
	DbConfig dbConfig;

	Session session = TestDBConnection.getSession();
	ContactDAO dao = ContactDAO.getInstance(session);

	@BeforeTest
	public void beforeAll() {
		dbConfig = new DbConfig(dao.getSession());
		dbConfig.populateAllTables();
		id = dbConfig.contact.getId();
	}

	@AfterMethod
	void clearCache() {
		session.clear();
	}

	@AfterTest
	public void afterAll() {
		dbConfig.clearTables();
		TestDBConnection.shutdown();
	}

	@Test(priority = 1)
	public void selectByIdTest() {
		try {
			int id = dao.getAll().get(0).getId();
			Contact contact = dao.selectById(id);
			Assert.assertEquals(contact.getEmail(), dbConfig.contact.getEmail());

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 2)
	public void getAllTest() {
		try {
			List<Contact> list = dao.getAll();
			Assert.assertTrue(list.size() > 0);

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 3)
	public void updateTest() {
		try {
			NumberPhone[] phoneList = new NumberPhone[2];
			phoneList[0] = dbConfig.numberPhone;

			Contact contact = new Contact();
			contact.setEmail("ednaldo@pereira.com");
			contact.setPhoneList(phoneList);
			contact.setId(id);
			dao.update(contact);

			Assert.assertEquals(session.get(Contact.class, id).getEmail(), contact.getEmail());

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 4)
	public void deleteTest() {
		dbConfig.deleteFromTable(TimeOffTracker.class.getSimpleName());
		dbConfig.deleteFromTable(Point.class.getSimpleName());
		dbConfig.deleteFromTable(Payroll.class.getSimpleName());
		dbConfig.deleteFromTable(Collaborator.class.getSimpleName());

		dao.delete(dbConfig.contact);
		id = dbConfig.contact.getId();
		Assert.assertNull(session.get(Contact.class, id));
	}

	@Test(priority = 5)
	public void insertTest() {
		try {
			NumberPhone phone = new NumberPhone();
			phone.setId(1);


			Contact contact = new Contact();
			
			NumberPhone[] phoneList = new NumberPhone[contact.getPhoneList().length];
			phoneList[0] = phone;
			
			contact.setEmail("ednaldo@pereira.com");
			contact.setPhoneList(phoneList);
			contact.setId(dbConfig.contact.getId() + 1);
			dao.insert(contact);

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

}
