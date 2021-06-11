package model;

import java.util.Calendar;

import org.junit.Assert;
import org.testng.annotations.Test;

import model.entities.person.Collaborator;

public class WorkEntryTest {

	@Test
	public void getRegistersInMonthTest() {
		Collaborator colab = new Collaborator();
		
		colab.setName("Pedro");
		
		WorkEntry point = new WorkEntry(colab);
		
		point.clockIn();
		point.clockIn();
		point.clockIn();
		
		Assert.assertEquals(point.getDay().size(), 3);
		
		Assert.assertEquals(point.getDay().get(0).get(Calendar.MILLISECOND),
				point.getDay().get(1).get(Calendar.MILLISECOND));
		
	}

	@Test
	public void getRegistriesTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void registerWorkEntryTest() {
		throw new RuntimeException("Test not implemented");
	}
}
