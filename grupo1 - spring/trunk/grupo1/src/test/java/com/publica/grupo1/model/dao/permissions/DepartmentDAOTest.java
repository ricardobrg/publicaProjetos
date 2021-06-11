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
import com.publica.grupo1.model.entities.permissions.role.Department;

public class DepartmentDAOTest {
	private int id;
	DbConfig dbConfig;

	Session session = TestDBConnection.getSession();
	DepartmentDAO dao = DepartmentDAO.getInstance(session);

	@BeforeTest
	public void beforeAll() {
		dbConfig = new DbConfig(dao.getSession());
		dbConfig.populateDepartmentTable();
		id = dbConfig.department.getId();
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
	void shouldReturnDepartmentById() {
		Department expectedDepartment = dbConfig.department;
		Department selectedDepartment = dao.selectById(id);

		assertEquals(selectedDepartment.getId(), expectedDepartment.getId());
	}

	@Test(priority = 2)
	public void getAllTest() {
		try {
			List<Department> list = dao.getAll();
			assertEquals(list.size(), 1);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test(priority = 3)
	public void updateTest() {
		try {
			Department DepartmentToUpdate = dbConfig.department;
			DepartmentToUpdate.setName("updated");

			dao.update(DepartmentToUpdate);

			assertEquals(session.get(Department.class, id).getId(), DepartmentToUpdate.getId());
			assertEquals(session.get(Department.class, id).getName(), DepartmentToUpdate.getName());

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test(priority = 4)
	public void deleteTest() {
		dao.delete(dbConfig.department);
		id = dbConfig.department.getId();

		assertNull(dao.selectById(id));
	}

	@Test(priority = 5)
	public void insertTest() {
		try {
			Department departmentInserted = new Department();
			departmentInserted.setId(dao.insert(departmentInserted));

			int idOfInsertedPayroll = departmentInserted.getId();

			assertEquals(session.get(Department.class, idOfInsertedPayroll).getId(), departmentInserted.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
