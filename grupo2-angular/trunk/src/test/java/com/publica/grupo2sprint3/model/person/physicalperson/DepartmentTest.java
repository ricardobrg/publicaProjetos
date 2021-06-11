package com.publica.grupo2sprint3.model.person.physicalperson;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DepartmentTest {

	Collaborator yasuo = new Collaborator("Yasuo");
	Department dep1 = new Department("League of Legends");
	
	Collaborator tiberius = new Collaborator("Tiberius");
	Department dep2 = new Department("Paladins");
	
	Collaborator vash = new Collaborator("Vash, o estouro da boiada");
	Department dep3 = new Department("Trigun");

	

	@Test
	public void getNameTest() {
		Assert.assertEquals(dep1.getName(), "League of Legends");
		Assert.assertEquals(dep2.getName(), "Paladins");
		Assert.assertEquals(dep3.getName(), "Trigun");
	}


	@Test
	public void setNameTest() {
		dep1.setName("Dota 2");
		dep2.setName("Overwatch");
		dep3.setName("Dragon Ball");
		Assert.assertEquals(dep1.getName(), "Dota 2");
		Assert.assertEquals(dep2.getName(), "Overwatch");
		Assert.assertEquals(dep3.getName(), "Dragon Ball");
	}
	
	@Test
	public void toStringTest() {
		//Collaborator vash = new Collaborator("Vash, o estouro da boiada");
		Department dep3 = new Department("Trigun");
		
		String result = "Departamento: Trigun\n"
				+ "Gerente: Vash, o estouro da boiada\n";
		
		Assert.assertEquals(dep3.toString(), result);
		
	}
	
}



















