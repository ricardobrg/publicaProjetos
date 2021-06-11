package model.entities.person;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import models.entities.person.Collaborator;

public class CollaboratorTest {
	
	private Collaborator colTest;
	
	@BeforeMethod
	public void instantiate() {
		colTest = new Collaborator();
	}

	@Test
	public void setAdmissionDateTest() {		
		colTest.setAdmissionDate("29/10/1999");
		Assert.assertEquals(colTest.getAdmissionDate(), "29/10/1999");
		
		colTest.setAdmissionDate("31/11/2025");
		Assert.assertNull(colTest.getAdmissionDate());
	}

	@Test
	public void setWorkHoursTest() {		
		colTest.setWorkHours(160);
		Assert.assertEquals(colTest.getWorkHours(), 160);
		
		colTest.setWorkHours(161);
		Assert.assertEquals(colTest.getWorkHours(), 160);
		
		colTest.setWorkHours(-1);
		Assert.assertEquals(colTest.getWorkHours(), 160);
	}
	
}




