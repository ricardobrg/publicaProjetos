package controllers;

import java.util.ArrayList;

import com.sun.jdi.connect.IllegalConnectorArgumentsException;

import models.entities.events.Event;
import models.entities.person.Collaborator;
import models.hibernate.EventHibernate;

/***
 * EventController<br>
 * 
 * Class used to manage the events in the application controller.
 * 
 * This controller is responsible for calling the views of the events menus as
 * well as for applying the methods to them. It works receiving and returning
 * JsonObjects.
 * 
 * @version 2.0.0
 * 
 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
 * @author Pablo Mafessoli
 * 
 */
public class EventController {

	private EventHibernate eventDAO;

	public EventController() {
		eventDAO = new EventHibernate();
	}

	/**
	 * Receives a events and calls the DAO to store it in the database.
	 * 
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * 
	 * @param event - the event object to be added
	 * @return the id of the inserted object
	 * @throws Exception
	 */
	public int add(Event event) throws Exception {
		return eventDAO.insert(event);
	}

	/**
	 * Deletes a event using its ID
	 * 
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * 
	 * @param id the id of the event to be deleted
	 * @return the id of the deleted object
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public int delete(int id) throws IllegalConnectorArgumentsException, Exception {
		return eventDAO.delete(id);
	}

	/**
	 * Updates an existing event identified by its ID
	 * 
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * 
	 * @param event the event to be edited
	 * @return the id of the updated object
	 * @throws Exception
	 */
	public int edit(Event event) throws Exception {
		return eventDAO.update(event);
	}

	/**
	 * Retrieves the ArrayList of existing events and returns them in an ArrayList
	 * of events.
	 * 
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * 
	 * @return the ArrayList of existing events
	 * @throws Exception
	 */
	public ArrayList<Event> getAll() throws Exception {
		return eventDAO.getAll();
	}

	/**
	 * Searches a event in the database by ID and returns it.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param id the id of the event to be retrieved
	 * @return the event found
	 * @throws Exception
	 */
	public Event find(int id) throws Exception {
		return (Event) eventDAO.find("id", String.valueOf(id));
	}

	public int insertCollab(Collaborator collab, int id) {
		return eventDAO.insertCollab(collab, id);
	}
}
