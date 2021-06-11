package controller;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import model.dao.file.FileCrud;
import model.dao.file.RoleDecorator;
import model.entities.security.AccessLevel;
import model.entities.security.Role;

public class RoleControllerTest {

	RoleController controller;
	RoleDecorator dao;
	ArrayList<Role> roles = new ArrayList<>();
	
	@Test
	public void addTest() {

		Role role = new Role();
		role.setAccessLevel(AccessLevel.BASIC);
		role.setId(3);

		role.setName("Jason");
		
		JsonObject roleJson = new JsonObject();
		
		roleJson.addProperty("accessLevel", 1);
		roleJson.addProperty("id", role.getId());
		roleJson.addProperty("name", role.getName());
		
		RoleController roleController = new RoleController();
		
		roleController.add(roleJson);
		
		dao = new RoleDecorator(new FileCrud<Role>("database/role.txt", Role.class));
		
		Assert.assertEquals(role.getName(), dao.findByRole("Jason").getName());
		
		dao.deleteById(role.getId());
		
	}

	@Test
	public void deleteTest() {
		Role role = new Role();
		role.setAccessLevel(AccessLevel.BASIC);
		role.setId(3);
		role.setName("Jason");
		
		JsonObject roleJson = new JsonObject();
		
		roleJson.addProperty("accessLevel", 1);
		roleJson.addProperty("id", role.getId());
		roleJson.addProperty("name", role.getName());
		
		RoleController roleController = new RoleController();
		
		roleController.add(roleJson);
		
		dao = new RoleDecorator(new FileCrud<Role>("database/role.txt", Role.class));
		
		dao.deleteById(role.getId());
		
		Assert.assertNotEquals(role.getName(), dao.findByRole("Jason"));

	}
	@Test
	public void findTest() {
		dao = new RoleDecorator(new FileCrud<Role>("database/role.txt", Role.class));
		Role role = new Role();
		role.setAccessLevel(AccessLevel.BASIC);
		role.setId(3);
		role.setName("Python");
		
		JsonObject roleJson = new JsonObject();
		
		roleJson.addProperty("accessLevel", 1);
		roleJson.addProperty("id", role.getId());
		roleJson.addProperty("name", role.getName());
		
		RoleController roleController = new RoleController();
		
		Assert.assertNull(dao.findByRole("Python"));
		
		roleController.add(roleJson);
		
		Assert.assertEquals(role.getName(), dao.findByRole("Python").getName());
		
		dao.deleteById(role.getId());
		
		Assert.assertNull(dao.findByRole("Python"));
	}

}
