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
import com.publica.grupo1.model.entities.permissions.Permission;

public class PermissionDAOTest {
	private int id;
	DbConfig dbConfig;

	Session session = TestDBConnection.getSession();
	PermissionDAO dao = PermissionDAO.getInstance(session);

	@BeforeTest
	public void beforeAll() {
		dbConfig = new DbConfig(dao.getSession());
		dbConfig.populateDepartmentTable();
		dbConfig.populateRoleTable();
		dbConfig.populatePermissionTable();
		
		id = dbConfig.permission.getId();
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
	void shouldReturnPermissionById() {
		Permission expectedPermission = dbConfig.permission;
		Permission selectedPermission = dao.selectById(id);

		assertEquals(selectedPermission.getId(), expectedPermission.getId());
	}

	@Test(priority = 2)
	public void getAllTest() {
		try {
			List<Permission> list = dao.getAll();
			assertEquals(list.size(), 1);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test(priority = 3)
	public void updateTest() {
		try {
			Permission permissionToUpdate = dbConfig.permission;
			permissionToUpdate.setAccessLevel(null);

			dao.update(permissionToUpdate);

			assertEquals(session.get(Permission.class, id).getId(), permissionToUpdate.getId());
			assertNull(session.get(Permission.class, id).getAccessLevel());

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test(priority = 4)
	public void deleteTest() {
		dao.delete(dbConfig.permission);
		id = dbConfig.permission.getId();

		assertNull(session.get(Permission.class, id));
	}

	@Test(priority = 5)
	public void insertTest() {
		try {
			int newPermissionId = dao.insert(new Permission(0, null, null, null));

			Permission permissionInserted = session.get(Permission.class, newPermissionId);

			assertEquals(permissionInserted.getId(), newPermissionId);
			assertEquals(permissionInserted.getId(), permissionInserted.getId());
			assertNull(permissionInserted.getAccessLevel());
			assertNull(permissionInserted.getDepartment());
			assertNull(permissionInserted.getRole());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
