package com.publica.grupo1.controller.payroll;

import static org.testng.Assert.assertEquals;

import java.time.LocalDateTime;

import org.hibernate.Session;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.publica.grupo1.database.DbConfig;
import com.publica.grupo1.database.TestDBConnection;
import com.publica.grupo1.model.dao.point.PointDAO;
import com.publica.grupo1.model.entities.collaborator.Collaborator;
import com.publica.grupo1.model.entities.payroll.Payroll;
import com.publica.grupo1.model.entities.point.Point;
import com.publica.grupo1.model.entities.point.PointType;

public class PayrollGeneratorTest {
	Collaborator collab, collabNocturnal;
	Payroll payroll;
	PointType entry, exit;

	Session session = TestDBConnection.getSession();
	PointDAO dao = PointDAO.getInstance(session);
	DbConfig dbConfig = new DbConfig(session);
	
	
	@BeforeTest
	public void beforeAll() {
		dbConfig.populateAllTables();

		collab = dbConfig.collab;
		entry = dbConfig.entry;
		exit = dbConfig.exit;
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
	public void shouldReturnExpectedSalariesWhenCloseMonthWithWorkLoadEquals220Hours() {
		PayrollGenerator generator = new PayrollGenerator(collab, dao);
		payroll = generator.getPayroll();

		int month = 11;
		int workedHoursByDay = 8;

		LocalDateTime earlierDate = LocalDateTime.of(2020, month, 1, 8, 0);
		LocalDateTime laterDate = LocalDateTime.of(2020, month, 1, 16, 0);

		LocalDateTime earlierExtraHourDate = LocalDateTime.of(2020, month, 1, 17, 0);
		LocalDateTime laterExtraHourDate = LocalDateTime.of(2020, month, 1, 18, 0);

		boolean isLeapYear = earlierDate.toLocalDate().isLeapYear();
		int monthDays = earlierDate.getMonth().length(isLeapYear);
		int sundaysInMonth = 5;

		double expectedSundaySalary = (generator.getSalaryPerHourWorkload8() * workedHoursByDay) * sundaysInMonth
				* payroll.getSUNDAY_EXTRA_PERCENTAGE();

		int extraWeekWorkedDays = 5;
		double expectedWeekExtraSalary = (generator.getSalaryPerHourWorkload8() * extraWeekWorkedDays)
				* payroll.getWEEK_DIURNAL_EXTRA_PERCENTAGE();

		double expectedTotalSalary = collab.getSalary() + expectedSundaySalary + expectedWeekExtraSalary;

		for (int i = 0; i < monthDays; i++) {
			dao.insert(new Point(collab, earlierDate.plusDays(i), entry));
			dao.insert(new Point(collab, laterDate.plusDays(i), exit));
		}

		// add only week days
		for (int i = 1; i < 6; i++) {
			dao.insert(new Point(collab, earlierExtraHourDate.plusDays(i), entry));
			dao.insert(new Point(collab, laterExtraHourDate.plusDays(i), exit));
		}

		generator.calculateDatePayroll(earlierDate.toLocalDate());

		assertEquals(payroll.getTotalSalary(), expectedTotalSalary);
		assertEquals(collab.getSalary(), generator.getCollab().getSalary());
		assertEquals(payroll.getSundayExtraSalary(), expectedSundaySalary);
		assertEquals(payroll.getWeekExtraSalary(), expectedWeekExtraSalary);
	}

//	@Test
//	public void shouldReturnExpectedSalariesWhenCloseMonthWithWorkLoadEquals210Hours() {
//		PayrollGenerator generator = new PayrollGenerator(collabNocturnal, dao);
//		payroll = generator.getPayroll();
//		PointDAO dao = generator.getPointReg().getDao();
//
//		int month = 11;
//
//		LocalDateTime earlierDate = LocalDateTime.of(2020, month, 2, 22, 0);
//		LocalDateTime laterDate = LocalDateTime.of(2020, month, 3, 5, 0);
//
//		dao.insert(new Point(collabNocturnal, earlierDate, entry));
//		dao.insert(new Point(collabNocturnal, laterDate, exit));
//
//		long expectedHours = ChronoUnit.HOURS.between(earlierDate, laterDate);
//
//		double expectedNocturnalWeekAdditional = generator.getSalaryPerHourWorkload7()
//				* payroll.getWEEK_NOCTURNAL_ADDITIONAL() * expectedHours;
//
//		generator.calculateDatePayroll(earlierDate.toLocalDate());
//
//		assertEquals(generator.getPointReg().getNocturnalWeekAdditional(), expectedHours);
//		assertEquals(payroll.getWeekNocturnalAdditional(), expectedNocturnalWeekAdditional);
//		assertEquals(collabNocturnal.getSalary(), payroll.getSalary());
//	}
}
