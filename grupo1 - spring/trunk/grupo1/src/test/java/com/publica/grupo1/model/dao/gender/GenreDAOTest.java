package com.publica.grupo1.model.dao.gender;

import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.publica.grupo1.database.DbConfig;
import com.publica.grupo1.database.TestDBConnection;
import com.publica.grupo1.model.entities.collaborator.Collaborator;
import com.publica.grupo1.model.entities.gender.Genre;
import com.publica.grupo1.model.entities.payroll.Payroll;
import com.publica.grupo1.model.entities.point.Point;
import com.publica.grupo1.model.entities.vacations.TimeOffTracker;

public class GenreDAOTest {
	int id;
	Session session = TestDBConnection.getSession();
	GenreDAO dao = GenreDAO.getInstance(session);
	DbConfig dbConfig = new DbConfig(session);

	@BeforeTest
	void init() {
		dbConfig = new DbConfig(dao.getSession());
		dbConfig.populateAllTables();
		id = dbConfig.genre.getIdGenre();
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
	public void getAllTest() {
		int size = dao.getAll().size();
		Assert.assertEquals(size, 1);
	}

	
	@Test(priority = 2)
	public void selectByIdTest() {
		Genre Genre = dao.getAll().get(0);
		Genre GenreFound = dao.selectById(Genre.getIdGenre());
		Assert.assertEquals(Genre.getNameGenre(), GenreFound.getNameGenre());
	}

	@Test(priority = 3)
	public void updateTest() {
		Genre Genre = dao.getAll().get(0);
		Genre.setNameGenre("Novo Nome");
		dao.update(Genre);
		Genre newGenre = dao.getAll().get(0);
		Assert.assertEquals(newGenre.getNameGenre(), "Novo Nome");
	}
	
	@Test(priority = 4)
	public void deleteTest() {
		dbConfig.deleteFromTable(Payroll.class.getSimpleName());
		dbConfig.deleteFromTable(TimeOffTracker.class.getSimpleName());
		dbConfig.deleteFromTable(Point.class.getSimpleName());
		dbConfig.deleteFromTable(Collaborator.class.getSimpleName());
		
		int oldSize = dao.getAll().size();
		Genre Genre = dao.getAll().get(0);
		dao.delete(Genre);
		int newSize = dao.getAll().size();
		Assert.assertEquals(oldSize - 1, newSize);
	}
	
	@Test(priority = 5)
	public void insertTest() {
		int oldSize = dao.getAll().size();
		Genre Genre = new Genre();
		Genre.setNameGenre("Masculino");
		dao.insert(Genre);
		int newSize = dao.getAll().size();
		Assert.assertEquals(oldSize + 1, newSize);
	}

}
