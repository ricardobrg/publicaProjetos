package com.publica.grupo1.model.entities.payroll;

import static org.testng.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.hibernate.Session;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.publica.grupo1.database.DbConfig;
import com.publica.grupo1.database.TestDBConnection;
import com.publica.grupo1.model.dao.point.PointDAO;
import com.publica.grupo1.model.entities.collaborator.Collaborator;
import com.publica.grupo1.model.entities.point.Point;
import com.publica.grupo1.model.entities.point.PointType;

public class PointRegisterTest {

	int id;
	Collaborator collab, collabNocturnal;

	PointType exit = PointType.EXIT;
	PointType entry = PointType.ENTRY;

	Session session = TestDBConnection.getSession();
	PointDAO dao = PointDAO.getInstance(session);

	DbConfig dbConfig;

	@BeforeTest
	void init() {
		dbConfig = new DbConfig(dao.getSession());
		dbConfig.populateAllTables();
		
		id = dbConfig.points.get(0).getId();
		collab = dbConfig.collab;
	}

	@BeforeMethod
	void clearCache() {
		dbConfig.deleteFromTable(Point.class.getSimpleName());
		session.clear();
	}

	@AfterTest
	public void afterAll() {
		dbConfig.clearTables();
		TestDBConnection.shutdown();
	}

	@Test
	public void shouldReturnExpectedWeekAndSundayHoursWhenCalculateMonthPoints() {
		PointRegister pointReg = new PointRegister(collab, dao);
		PointDAO dao = pointReg.getDao();

		int month = 11;

		LocalDateTime earlierDate = LocalDateTime.of(2020, month, 1, 8, 0);
		LocalDateTime laterDate = LocalDateTime.of(2020, month, 1, 16, 0);

		boolean isLeapYear = earlierDate.toLocalDate().isLeapYear();
		int monthDays = earlierDate.getMonth().length(isLeapYear);
		int sundaysInMonth = 5;

		double expectedWeekHours = collab.getPermission().getRole().getWorkLoad() * (monthDays - sundaysInMonth);
		double expectedSundayHours = collab.getPermission().getRole().getWorkLoad() * sundaysInMonth;

		for (int i = 0; i < monthDays; i++) {
			dao.insert(new Point(collab, earlierDate.plusDays(i), entry));
			dao.insert(new Point(collab, laterDate.plusDays(i), exit));
		}

		pointReg.calculateDatePoints(earlierDate.toLocalDate());

		assertEquals(pointReg.getWeekExtraTime(), 0);
		assertEquals(pointReg.getSundayExtraTime(), expectedSundayHours);
		assertEquals(pointReg.getWeekTime(), expectedWeekHours);
	}

	@Test
	public void shouldReturnExpectedWeekAndSundayHoursWhenCalculateMonthPointsWithNotClosedPoints() {
		PointRegister pointReg = new PointRegister(collab, dao);
		PointDAO dao = pointReg.getDao();

		int month = 11;

		LocalDateTime earlierDate = LocalDateTime.of(2020, month, 1, 8, 0);
		LocalDateTime laterDate = LocalDateTime.of(2020, month, 1, 16, 0);

		boolean isLeapYear = earlierDate.toLocalDate().isLeapYear();
		int monthDays = earlierDate.getMonth().length(isLeapYear);
		int sundaysInMonth = 5;

		double expectedWeekHours = collab.getPermission().getRole().getWorkLoad() * (monthDays - sundaysInMonth);
		double expectedSundayHours = collab.getPermission().getRole().getWorkLoad() * sundaysInMonth;

		for (int i = 0; i < monthDays; i++) {
			dao.insert(new Point(collab, earlierDate.plusDays(i), entry));
			dao.insert(new Point(collab, laterDate.plusDays(i), exit));
		}

		LocalDateTime notClosedPointDate = LocalDateTime.of(2020, month, 1, 17, 0);

		for (int i = 14; i < monthDays; i++) {
			dao.insert(new Point(collab, notClosedPointDate.plusDays(i), entry));
		}

		pointReg.calculateDatePoints(earlierDate.toLocalDate());

		assertEquals(pointReg.getWeekTime(), expectedWeekHours);
		assertEquals(pointReg.getWeekExtraTime(), 0);
		assertEquals(pointReg.getSundayExtraTime(), expectedSundayHours);
	}

	@Test
	public void shouldReturnExpectedWeekNormalAndExtraHoursAndSundayHoursWhenCalculateMonthPoints() {
		PointRegister pointReg = new PointRegister(collab, dao);
		PointDAO dao = pointReg.getDao();

		int month = 11;

		LocalDateTime earlierDate = LocalDateTime.of(2020, month, 1, 8, 0);
		LocalDateTime laterDate = LocalDateTime.of(2020, month, 1, 16, 0);

		LocalDateTime earlierExtraHourDate = LocalDateTime.of(2020, month, 1, 17, 0);
		LocalDateTime laterExtraHourDate = LocalDateTime.of(2020, month, 1, 18, 0);

		long extraHour = ChronoUnit.HOURS.between(earlierExtraHourDate, laterExtraHourDate);

		boolean isLeapYear = earlierDate.toLocalDate().isLeapYear();
		int monthDays = earlierDate.getMonth().length(isLeapYear);
		int sundaysInMonth = 5;

		double expectedWeekHours = collab.getPermission().getRole().getWorkLoad() * (monthDays - sundaysInMonth);
		double expectedSundayHours = (collab.getPermission().getRole().getWorkLoad() + extraHour) * sundaysInMonth;
		double expectedWeekExtraHours = (extraHour * monthDays) - sundaysInMonth;

		for (int i = 0; i < monthDays; i++) {
			dao.insert(new Point(collab, earlierDate.plusDays(i), entry));
			dao.insert(new Point(collab, laterDate.plusDays(i), exit));

			dao.insert(new Point(collab, earlierExtraHourDate.plusDays(i), entry));
			dao.insert(new Point(collab, laterExtraHourDate.plusDays(i), exit));
		}

		pointReg.calculateDatePoints(earlierDate.toLocalDate());

		assertEquals(pointReg.getWeekTime(), expectedWeekHours);
		assertEquals(pointReg.getSundayExtraTime(), expectedSundayHours);
		assertEquals(pointReg.getWeekExtraTime(), expectedWeekExtraHours);
	}

	@Test
	public void shouldReturnExpectedNocturnalAndWeekAdditionalWhenCalculateMonthPoints() {
		PointRegister pointReg = new PointRegister(collab, dao);
		PointDAO dao = pointReg.getDao();

		int month = 11;

		double expectedNocturnalWeekAdditional = 7.0;
		double expectedWeekAdditional = 1.0;

		LocalDateTime date = LocalDateTime.of(2020, month, 24, 21, 0);
		LocalDateTime laterDate = LocalDateTime.of(2020, month, 25, 5, 0);

		dao.insert(new Point(collab, date, entry));
		dao.insert(new Point(collab, laterDate, exit));

		pointReg.calculateDatePoints(date.toLocalDate());
		
		assertEquals(pointReg.getNocturnalWeekAdditional(), expectedNocturnalWeekAdditional);
		assertEquals(pointReg.getWeekExtraTime(), expectedWeekAdditional);
	}

	@Test
	public void shouldSet8HoursInSunday() {
		PointRegister pointReg = new PointRegister(collab, dao);
		ArrayList<Point> dayPoints = new ArrayList<>();

		int expectedWorkedHours = 8;

		LocalDateTime sundayDate = LocalDateTime.of(2020, 11, 1, 8, 0);

		dayPoints.add(new Point(collab, sundayDate, entry));
		dayPoints.add(new Point(collab, sundayDate.plusHours(expectedWorkedHours), exit));

		pointReg.calculateDayPoints(dayPoints);

		assertEquals(pointReg.getSundayExtraTime(), expectedWorkedHours);
		assertEquals(pointReg.getWeekExtraTime(), 0);
		assertEquals(pointReg.getWeekTime(), 0);
	}

	@Test
	public void shouldSet8NormalWorkedHoursAnd2ExtraHoursInWeekDay() {
		PointRegister pointReg = new PointRegister(collab, dao);
		ArrayList<Point> dayPoints = new ArrayList<>();

		double expectedNormalWorkedHours = 8.0;
		double expectedExtraHours = 2.0;

		LocalDateTime date = LocalDateTime.of(2020, 11, 24, 8, 0);

		dayPoints.add(new Point(collab, date, entry));
		dayPoints.add(new Point(collab, date.plusHours(10), exit));

		pointReg.calculateDayPoints(dayPoints);

		assertEquals(pointReg.getWeekTime(), expectedNormalWorkedHours);
		assertEquals(pointReg.getWeekExtraTime(), expectedExtraHours);
		assertEquals(pointReg.getSundayExtraTime(), 0);
	}

}