package com.publica.grupo2sprint3.model.person.physicalperson;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;

/**
 * This class provides unites tests for methods inside Role Class as
 * getters e setters.
 * 
 * @author Vinicius Roosevelt
 * @author Pablo Mafessoli
 * 
 * @version 1.0.0
 *
 */
public class RoleTest {
  
	Department department = new Department("Deus");
	Role testObject = new Role("Dev. Python", department, 3000);
	
	@Test
	public void getNameTest() {
		Assert.assertEquals(testObject.getName(), "Dev. Python");
		Assert.assertNotNull(testObject);
	}
	
	@Test
	public void setNameTest() {
		testObject.setName("Nome mudado");
		Assert.assertEquals(testObject.getName(), "Nome mudado");
		Assert.assertNotNull(testObject);
	}
	
	@Test
	public void getSalaryTest() {
		Assert.assertEquals(testObject.getSal(), 3000);
		Assert.assertNotEquals(testObject.getSal(), 3000);
		Assert.assertNotEquals(testObject.getSal(), 2000);
		Assert.assertNotEquals(testObject.getSal(), 10);
		Assert.assertNotEquals(testObject.getSal(), 0);
		Assert.assertNotNull(testObject.getSal());
	}
	
	@Test
	public void setAccesLevelTest() {
		testObject.setAccessLevel(AccessLevel.BASIC);
		Assert.assertEquals(testObject.getAccessLevel(), AccessLevel.BASIC);
		Assert.assertNotEquals(testObject.getAccessLevel(), AccessLevel.ADVANCED);
		Assert.assertNotEquals(testObject.getAccessLevel(), AccessLevel.TOTAL);
		Assert.assertNotNull(testObject);
		testObject.setAccessLevel(AccessLevel.ADVANCED);
		Assert.assertEquals(testObject.getAccessLevel(), AccessLevel.ADVANCED);		
	}
	
	@Test
	public void getAccessLevelTest() {
		testObject.setAccessLevel(AccessLevel.TOTAL);
		Assert.assertEquals(testObject.getAccessLevel(), AccessLevel.TOTAL);
		Assert.assertNotEquals(testObject.getAccessLevel(), AccessLevel.ADVANCED);
		Assert.assertNotEquals(testObject.getAccessLevel(), AccessLevel.BASIC);
		Assert.assertNotNull(testObject);
	}
	
	@Test
	public void toStringTest() {
		Department department = new Department("Deus");
		Role role = new Role("Dev. Python", department, 3000);
		
		String result = "Cargo: Dev. Python\n"
				+ "Departamento: Deus\n"
				+ "Gerente: N�o Cadastrado!\n";
				
		
		Assert.assertEquals(role.toString(), result);
	}
	
}

























