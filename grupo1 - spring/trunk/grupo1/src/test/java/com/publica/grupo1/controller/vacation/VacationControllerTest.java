package com.publica.grupo1.controller.vacation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.publica.grupo1.database.DbConfig;
import com.publica.grupo1.database.TestDBConnection;
import com.publica.grupo1.model.dao.collaborator.CollaboratorDAO;
import com.publica.grupo1.model.dao.point.PointDAO;
import com.publica.grupo1.model.entities.collaborator.Collaborator;
import com.publica.grupo1.model.entities.point.Point;

public class VacationControllerTest {
	DbConfig dbConfig;

	Session session = TestDBConnection.getSession();
	CollaboratorDAO collabDao = CollaboratorDAO.getInstance(session);
	Collaborator collab;
	VacationController vac;
	
	ArrayList<Point> points;
	
	@BeforeTest
	public void beforeAll() {
		dbConfig = new DbConfig(collabDao.getSession());
		dbConfig.populateAllTables();
		
		collab = dbConfig.collab;
		vac = new VacationController(collab, session);
		
		points = dbConfig.points;
	}
	
	@AfterMethod
	void clearCache() {
		session.clear();
	}
	
	@AfterTest
	public void afterAll() {
		dbConfig.clearTables();
		TestDBConnection.shutdown();
	}
	@Test
	public void daysToVacationTest() {
		Assert.assertEquals(
				ChronoUnit.DAYS.between(LocalDate.now(), collab.getLastVacationDate().plusYears(1)), 
				vac.daysToVacation());
	}

	@Test
	public void getCollaboratorPointsOnIntervalTest() {
		PointDAO pointDAO = PointDAO.getInstance(session);
		LocalDate date = collab.getLastVacationDate();
		
		List<Point> expected = pointDAO.getCollaboratorPointsOnInterval(collab, date, LocalDate.now());
		//LocalDate expected 
		
		Assert.assertEquals(vac.getCollaboratorPointsOnInterval(), expected);
	}

	@Test
	public void getWorkedTimeTest() {
		int hours = 0;
		ArrayList<LocalDateTime> clocks = new ArrayList<LocalDateTime>();
		
		points.forEach(o -> clocks.add(o.getDate()));

		if (clocks != null) {
			for(int i = 1; i < clocks.size(); i += 2){
				long difference = ChronoUnit.HOURS.between(clocks.get(i - 1), clocks.get(i));
				
				if(difference > 8)
					difference = 8;
				
				hours += difference;
			}
		}
		
		Assert.assertEquals(vac.getWorkedTime(), hours * 60);
	}

	@Test
	public void isVacationAvailableTest() {
		Assert.assertTrue(vac.isVacationAvailable());
	}

	@Test
	public void pointDifferenceTest() {
		int hours = 0;
		ArrayList<LocalDateTime> clocks = new ArrayList<LocalDateTime>();
		
		points.forEach(o -> clocks.add(o.getDate()));

		if (clocks != null) {
			for(int i = 1; i < clocks.size(); i += 2){
				long difference = ChronoUnit.HOURS.between(clocks.get(i - 1), clocks.get(i));
				
				if(difference > 8)
					difference = 8;
				
				hours += difference;
			}
		}
		
		Assert.assertEquals(vac.pointDifference(points), hours);
	}

	@Test
	public void vacationAllowedDaysTest() {
		Assert.assertEquals(vac.vacationAllowedDays(), 30);
	}
}



























