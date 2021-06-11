package com.publica.grupo2sprint3.model.person.physicalperson;

import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.util.UtilsCalendar;

public class PointTest {

	@Test
	public void convertDayMinuteTest() {
		Point point = new Point("12/01/2021", 629);
		Assert.assertEquals(point.convertDayMinute(),"10:29");
	}

	@Test
	public void getCollaboratorTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void getDateTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void getDayMinuteTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void getIdTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void setCollaboratorTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void setDateTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void setDayMinuteTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void setIdTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void toStringTest() {
		Collaborator collab = new Collaborator("Carlos");
		Calendar calendar = Calendar.getInstance();
		int minutes = (calendar.get(Calendar.HOUR_OF_DAY) * 60) + calendar.get(Calendar.MINUTE);
		String hour = UtilsCalendar.toString(minutes);
		String date = "16/12/2004";
		
		Point point = new Point(collab, date, minutes);
		
		String result = String.format(""
				+ "Colaborador: %s\n"
				+ "Data: %s\n"
				+ "Hora: %s\n", 
				collab.getName(),
				date,
				hour);
		
		Assert.assertEquals(point.toString(), result);

	}
}
