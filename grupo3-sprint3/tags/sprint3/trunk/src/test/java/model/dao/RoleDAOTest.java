package model.dao;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import model.entities.security.AccessLevel;
import model.entities.security.Role;

public class RoleDAOTest {

	@Test
	public void insertTest() {
		RoleDAO dao = new RoleDAO();

		Role role = new Role("Zelador");
		role.setAccessLevel(AccessLevel.MEDIUM);

		dao.addObject(role);

		int size = dao.getAll().size();
		Role object = (Role) dao.findObject(size - 1);
		Assert.assertEquals(object.getName(), role.getName());
		Assert.assertNotEquals(object.getName(), "CLEITIN");
	}

	@Test
	public void selectTest() {
		RoleDAO dao = new RoleDAO();
		Assert.assertEquals(((Role) dao.findObject(0)).getName(), "Dev. Java Jr.");
		Assert.assertEquals(((Role) dao.findObject(1)).getName(), "Dev. Java pleno");
		Assert.assertEquals(((Role) dao.findObject(2)).getName(), "Gestor de RH");
	}

	@Test
	public void deleteObjectTest() {
		RoleDAO dao = new RoleDAO();

		Role role = new Role("Zelador");
		role.setAccessLevel(AccessLevel.MEDIUM);
		dao.addObject(role);
		int oldSize = dao.getAll().size();		
		dao.delObject(dao.getAll().size() - 1);
		int newSize = dao.getAll().size();
		Assert.assertEquals(oldSize-1, newSize);
	}

	@Test
	public void selectAllObjectTest() {
		RoleDAO dao = new RoleDAO();
		ArrayList<Role> roles = dao.getAll();
		Assert.assertNotNull(roles.size());
	}
}
