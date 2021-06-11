package com.publica.grupo1.model.dao.collaborator;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.fail;

import java.util.List;

import org.hibernate.Session;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.database.DbConfig;
import com.publica.grupo1.model.entities.collaborator.Collaborator;
import com.publica.grupo1.model.entities.vacations.TimeOffTracker;

public class CollaboratorDAOTest {
	private int id;
	DbConfig dbConfig;

	Session session = DBConnection.getSession();
	CollaboratorDAO dao = CollaboratorDAO.getInstance(session);

	@BeforeTest
	public void beforeAll() {
		dbConfig = new DbConfig(dao.getSession());
		dbConfig.populateAllTables();
		id = dbConfig.collab.getIdCollaborator();
	}
	
	@AfterMethod
	void clearCache() {
		session.clear();
	}
	
	@AfterTest
	public void afterAll() {
		//dbConfig.clearTables();
		DBConnection.shutdown();
	}

	@Test(priority = 1)
	void shouldReturnCollaboratorById() {
		Collaborator expectedCollaborator = dbConfig.collab;
		Collaborator selectedCollaborator = dao.selectById(id);

		assertEquals(selectedCollaborator.getIdCollaborator(), expectedCollaborator.getIdCollaborator());
		assertEquals(selectedCollaborator.getSalary(), expectedCollaborator.getSalary());
	}

	@Test(priority = 2)
	public void getAllTest() {
		try {
			List<Collaborator> list = dao.getAll();
			assertEquals(list.size(), 1);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test(priority = 3)
	public void updateTest() {
		try {
			Collaborator collaboratorToUpdate = dbConfig.collab;
			collaboratorToUpdate.setName("Papito");

			dao.update(collaboratorToUpdate);

			assertEquals(session.get(Collaborator.class, id).getIdCollaborator(), collaboratorToUpdate.getIdCollaborator());
			assertEquals(session.get(Collaborator.class, id).getName(), collaboratorToUpdate.getName());

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test(priority = 4)
	public void deleteTest() {
		/**
		 * deleting tables with id collaborator as fk.
		 */
		dbConfig.deleteFromPointsAndRelatedTables();
		dbConfig.deleteFromPayrollAndRelatedTables();
		dbConfig.deleteFromTable(TimeOffTracker.class.getSimpleName());
		
		dao.delete(dbConfig.collab);
		id = dbConfig.collab.getIdCollaborator();

		assertNull(dao.selectById(id));
	}

	@Test(priority = 5)
	public void insertTest() {
		try {
			Collaborator collaboratorInserted = new Collaborator();
			collaboratorInserted.setIdCollaborator(dao.insert(collaboratorInserted));

			int idOfInsertedCollaborator = collaboratorInserted.getIdCollaborator();

			assertEquals(session.get(Collaborator.class, idOfInsertedCollaborator).getIdCollaborator(), collaboratorInserted.getIdCollaborator());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
