package model.dao;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import model.entities.security.AccessLevel;

public class AccessLevelDAOTest {
	AccessLevelDAO accDAO;
	
	@BeforeMethod
	public void beforeAll() {
		accDAO = new AccessLevelDAO();
	}

	@Test
	public void findObjectTest() {
		System.out.println(accDAO.findObject());
	}

	@Test
	public void updateObjectTest() {
		accDAO.updateObject(AccessLevel.BASIC);
	}
}
