package com.publica.grupo2sprint3.model.person.physicalperson;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.dao.PointDAO;

public class VacationTest {
	
	
	Collaborator testCollab = new Collaborator();

	@BeforeTest
	public void setTest() {
		testCollab.setCpf("708.150.920-75");
	}
	
	@BeforeMethod
	public void resetTest() {
		PointDAO.getInstance().add(new Point(testCollab, "13/11/2020", (1500 * 60)));
		PointDAO.getInstance().add(new Point(testCollab, "13/11/2020", (2972 * 60)));
		PointDAO.getInstance().add(new Point(testCollab, "13/12/2020", (256 * 60)));
		PointDAO.getInstance().add(new Point(testCollab, "13/12/2020", (512 * 60)));
		PointDAO.getInstance().add(new Point(testCollab, "15/12/2021", (5 * 60)));
		PointDAO.getInstance().add(new Point(testCollab, "15/12/2021", (1930 * 60)));
	}
	
	@Test
	public void isVacationAllowed() {
		Assert.assertEquals(Vacation.vacationAllowed(testCollab, 1, 2021), 30);
		Assert.assertEquals(Vacation.vacationAllowed(testCollab, 1, 2020), 12);
	}
	
	@Test
	public void vacationReadyTest() {
		Assert.assertEquals(Vacation.vacationReady("01/01/2020"), true);
	}
	
	@Test
	public void toStringTest() {
		Collaborator collab = new Collaborator();
		
		String result = "Pode tirar f�rias? N�o pode\n"
				+ "Est� de f�rias? N�o est�\n"
				+ "Tempo de f�rias: 0\n";
		
		Assert.assertEquals(Vacation.toString(collab), result);
		
		
	}
	
}