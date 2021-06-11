package model.dao.hibernate;

import org.hibernate.exception.ConstraintViolationException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import model.entities.security.AccessLevel;
import model.entities.security.Role;

public class RoleHibernateTest {
	
	RoleHibernate hibernate;
	Role role;

	@BeforeClass
	public void setTest() {
		hibernate = new RoleHibernate();
		hibernate.setTestDatabase();
	}

	@Test(priority=1)
	public void addTest() {
		role = new Role();
		role.setName("Locador de Motos");
		role.setAccessLevel(AccessLevel.TOTAL);
		
		try {
			int insertedId = hibernate.insert(role);
			Assert.assertNotEquals(insertedId, -1);
			role.setId(insertedId);
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test(priority=2)
	public void findTest() {
		
		try {
			Role roleFound = (Role) hibernate.find("id", String.valueOf(role.getId()));
			Assert.assertEquals(roleFound.getId(), role.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
	}

	@Test(priority=3)
	public void getAllTest() {
		try {
			Assert.assertNotNull(hibernate.getAll());
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test(priority=4)
	public void editTest() {
		role.setAccessLevel(AccessLevel.TOTAL);
		role.setName("Locador de Canetas");

		try {
			Assert.assertEquals(hibernate.update(role), role.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test(priority=5)
	public void deleteTest() {
		try {
			int id = hibernate.delete(4);
			System.out.println(id + " - " + role.getId());
			Assert.assertEquals(id, role.getId());
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			Assert.fail();
			
		} catch (ConstraintViolationException e) {
			e.printStackTrace();
			Assert.fail();
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
	}

}
