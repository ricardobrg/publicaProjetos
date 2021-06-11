package com.publica.grupo1.model.dao.permissions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.fail;

import java.util.List;

import org.hibernate.Session;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.publica.grupo1.database.DbConfig;
import com.publica.grupo1.database.TestDBConnection;
import com.publica.grupo1.model.entities.permissions.role.Role;

public class RoleDAOTest {
	private int id;
	DbConfig dbConfig;

	Session session = TestDBConnection.getSession();
	RoleDAO dao = RoleDAO.getInstance(session);

	@BeforeTest
	public void beforeAll() {
		dbConfig = new DbConfig(dao.getSession());
		dbConfig.populateRoleTable();
		id = dbConfig.role.getId();
	}

	@BeforeMethod
	void clearCache() {
		session.clear();
	}

	@AfterTest
	public void afterAll() {
		dbConfig.clearTables();
		TestDBConnection.shutdown();
	}

	@Test(priority = 1)
	void shouldReturnRoleById() {
		Role expectedRole = dbConfig.role;
		Role selectedRole = dao.selectById(id);

		assertEquals(selectedRole.getId(), expectedRole.getId());
	}

	@Test(priority = 2)
	public void getAllTest() {
		try {
			List<Role> list = dao.getAll();
			assertEquals(list.size(), 1);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test(priority = 3)
	public void updateTest() {
		try {
			Role roleToUpdate = dbConfig.role;
			roleToUpdate.setName("Motorista");

			dao.update(roleToUpdate);

			assertEquals(session.get(Role.class, id).getId(), roleToUpdate.getId());
			assertEquals(session.get(Role.class, id).getName(), roleToUpdate.getName());

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test(priority = 4)
	public void deleteTest() {
		dao.delete(dbConfig.role);
		id = dbConfig.role.getId();

		assertNull(dao.selectById(id));
	}

	@Test(priority = 5)
	public void insertTest() {
		try {
			Role roleInserted = new Role();
			roleInserted.setId(dao.insert(roleInserted));

			int idOfInsertedPayroll = roleInserted.getId();

			assertEquals(session.get(Role.class, idOfInsertedPayroll).getId(), roleInserted.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
