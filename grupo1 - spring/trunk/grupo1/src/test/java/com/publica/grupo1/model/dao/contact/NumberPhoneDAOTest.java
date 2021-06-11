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
import com.publica.grupo1.model.entities.contact.NumberPhone;

public class NumberPhoneDAOTest {
	private int id;
	DbConfig dbConfig;

	Session session = TestDBConnection.getSession();
	NumberPhoneDAO dao = NumberPhoneDAO.getInstance(session);

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

	@Test
	public void insertTest() {
		try {
			NumberPhone phone = new NumberPhone();
			phone.setDd("47");
			phone.setNumber("991029445");
			phone.setId(id);

			dao.insert(phone);

		} catch(Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void getAllTest() {
		try {
			List<NumberPhone> list = dao.getAll();
			Assert.assertTrue(list.size() > 0);			

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void getAllByDDDTest() {
		try {
			List<NumberPhone> list = dao.getAll();
			Assert.assertTrue(list.size() > 0);

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
}













