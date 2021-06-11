package models.hibernate;

import org.hibernate.exception.ConstraintViolationException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import models.entities.events.Event;
import models.entities.security.Role;

public class EventHibernateTest {
	
	EventHibernate hibernate;
	Event event;

	@BeforeClass
	public void setTest() {
		hibernate = new EventHibernate();
		hibernate.setTestDatabase();
	}

	@Test(priority=1)
	public void addTest() {
		event = new Event();
		event.setName("Locador de Motos");
		
		try {
			int insertedId = hibernate.insert(event);
			Assert.assertNotEquals(insertedId, -1);
			event.setId(insertedId);
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test(priority=2)
	public void findTest() {
		
		try {
			Role roleFound = (Role) hibernate.find("id", String.valueOf(event.getId()));
			Assert.assertEquals(roleFound.getId(), event.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
	}

	@Test(priority=3)
	public void getAllTest() {
		try {
			Assert.assertNotNull(hibernate.getAll());
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test(priority=4)
	public void editTest() {
		event.setName("Locador de Canetas");

		try {
			Assert.assertEquals(hibernate.update(event), event.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test(priority=5)
	public void deleteTest() {
		try {
			int id = hibernate.delete(4);
			System.out.println(id + " - " + event.getId());
			Assert.assertEquals(id, event.getId());
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			Assert.fail();
			
		} catch (ConstraintViolationException e) {
			e.printStackTrace();
			Assert.fail();
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
	}
}
