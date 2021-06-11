package com.publica.grupo1.model.dao.point;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.hibernate.Session;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.publica.grupo1.database.DbConfig;
import com.publica.grupo1.database.TestDBConnection;
import com.publica.grupo1.model.entities.collaborator.Collaborator;
import com.publica.grupo1.model.entities.point.Point;

public class PointDAOTest {
	private int id;
	DbConfig dbConfig;

	Session session = TestDBConnection.getSession();
	PointDAO dao = PointDAO.getInstance(session);

	int month = 11;
	LocalDateTime date;

	int workload = 8;
	Collaborator collab, collab2;

	@BeforeTest
	void init() {
		dbConfig = new DbConfig(dao.getSession());
		dbConfig.populateAllTables();
		id = dbConfig.points.get(0).getId();
	}
	
	@BeforeMethod
	void clearCache() {
		session.clear();
	}

	@AfterTest
	public void afterAll() {
		dbConfig.clearTables();
		TestDBConnection.shutdown();
	}

	@Test(priority = 1)
	void shouldReturnPointById() {
		Point expectedPoint = dbConfig.points.get(0);
		Point selectedPoint = dao.selectById(id);

		assertEquals(selectedPoint.getId(), expectedPoint.getId());
		assertEquals(selectedPoint.getDate().getDayOfMonth(), expectedPoint.getDate().getDayOfMonth());
	}

	@Test(priority = 2)
	public void getAllTest() {
		try {
			List<Point> list = dao.getAll();
			assertEquals(list.size(), dbConfig.points.size());

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test(priority = 2)
	public void getCollabPointsTest() {
		try {
			List<Point> list = dao.getCollaboratorPoints(dbConfig.collab);
			
			assertEquals(list.size(), dbConfig.points.size());
			assertEquals(list.get(0).getDate(), dbConfig.points.get(0).getDate());
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test(priority = 2)
	public void shouldReturnEmptyListWhenCollaboratorNotHavePoints() {
		try {
			Collaborator collabWithoutPoints = new Collaborator();
			collabWithoutPoints.setIdCollaborator(dbConfig.collab.getIdCollaborator() + 1);
			
			assertEquals(dao.getCollaboratorPoints(collabWithoutPoints).size(), 0);
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test(priority = 3)
	public void updateTest() {
		try {
			Point pointToUpdate = dbConfig.points.get(0);
			pointToUpdate.setPointType(dbConfig.exit);
			pointToUpdate.setDate(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
			
			dao.update(pointToUpdate);
			
			assertEquals(session.get(Point.class, id).getId(), pointToUpdate.getId());
			assertEquals(session.get(Point.class, id).getPointType(), pointToUpdate.getPointType());
			assertEquals(session.get(Point.class, id).getDate(), pointToUpdate.getDate());

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test(priority = 4)
	public void deleteTest() {
		id = dbConfig.points.get(0).getId();
		dao.delete(dbConfig.points.get(0));

		assertNull(session.get(Point.class, id));
	}

	@Test(priority = 5)
	public void insertTest() {
		try {
			Point pointInserted = new Point();
			pointInserted.setPointType(dbConfig.exit);
			pointInserted.setDate(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
			
			pointInserted.setId(dao.insert(pointInserted));

			int idOfInsertedPoint = pointInserted.getId();
			
			assertEquals(session.get(Point.class, idOfInsertedPoint).getId(), pointInserted.getId());
			assertEquals(session.get(Point.class, idOfInsertedPoint).getPointType(), pointInserted.getPointType());
			assertEquals(session.get(Point.class, idOfInsertedPoint).getDate(), pointInserted.getDate());

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
