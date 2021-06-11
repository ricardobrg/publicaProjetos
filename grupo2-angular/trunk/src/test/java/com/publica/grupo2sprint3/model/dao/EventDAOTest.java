package com.publica.grupo2sprint3.model.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Event;

public class EventDAOTest {
	EventDAO dao = EventDAO.getInstance();
	CollaboratorDAO collabDao = CollaboratorDAO.getInstance();
	LocalDateTime date = LocalDateTime.of(2021, 1, 24, 10, 0, 0);
	Event event1 = new Event("Pascoa", "Evento de Pascoa", (Collaborator)collabDao.getAll().get(0), date);

	@Test
	public void addTest() {
		dao.add(event1);
	}

	@Test
	public void findByIdTest() {
		Assert.assertNotNull(dao.findById(1));
	}

	@Test
	public void findByIdentifierTest() {
		Event event = (Event) dao.findByIdentifier("PÃ¡scoa");
		Assert.assertEquals(event.getName(), event1.getName());
	}

	@Test
	public void getInstanceTest() {
		Assert.assertEquals(dao.getClass(),
				new EventDAO().getClass());
	}

	@Test
	public void removeByIdTest() {
		Assert.assertEquals(dao.removeById(1), true);
	}

	@Test
	public void removeByIdentifierTest() {
		Assert.assertEquals(dao.removeByIdentifier("PÃ¡scoa"), true);
	}

	@Test
	public void updateByIdTest() {
		Event event = (Event) dao.findById(7);
		event.setDescription("Evento de PÃ¡scoa com chocolates");
		
		Assert.assertEquals(dao.updateById(7, event), true);
		
	}

	@Test
	public void updateByIdentifierTest() {
		Event event = (Event) dao.findById(7);
		event.setDescription("Evento de PÃ¡scoa com chocolates e balas");
		Assert.assertEquals(dao.updateByIdentifier("PÃ¡scoa", event), true);
	}
	
	@Test
	public void getAllTest() {
		ArrayList<Event> array = dao.getAll();
		System.out.println(array.get(0).getStartTime());
		Assert.assertNotNull(array);
	}
}
