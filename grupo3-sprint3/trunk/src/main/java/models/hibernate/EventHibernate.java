package models.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import models.entities.events.Event;
import models.entities.person.Collaborator;

/***
 * EventHibernate Class.<br>
 * 
 * This class implements CRUD base methods for Evet. Each method opens a Session
 * Factory, does the query and closes it.
 * 
 * @author Pablo Mafessoli <mafessolip@gmail.com>
 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
 * 
 * @version 1.0.0
 */
public class EventHibernate extends HibernateDataAccess {

	/**
	 * This class implements CRUD based methods for Event. This method inserts an
	 * event. <br>
	 * 
	 * <br>
	 * A transaction takes place for the DB, which will be sent by DAO
	 * (<code>Insert</code>)
	 * 
	 * @author Pablo Mafessoli <mafessolip@gmail.com>
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * 
	 * @param event the event object to be added
	 * @return the id of the inserted object. Returns -1 if the operation failed
	 */
	@Override
	public int insert(Object object) {

		Event event = (Event) object;
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			int id = (int) session.save(event);
			session.getTransaction().commit();
			session.close();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	/**
	 * This class implements CRUD based methods for Event. This method inserts collaborators an
	 * event. <br>
	 * 
	 * <br>
	 * A transaction takes place for the DB, which will be sent by DAO
	 * (<code>Insert</code>)
	 * 
	 * @author Pablo Mafessoli <mafessolip@gmail.com>
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * 
	 * @param event the event object to be added
	 * @return the id of the inserted object. Returns -1 if the operation failed
	 */
	public int insertCollab(Collaborator collab, int id) {

		try {
			Event event = (Event) find("id", Integer.toString(id));
			event.addCollabEvent(collab);

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			id = (int) session.save(event);
			session.getTransaction().commit();
			session.close();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	/**
	 * This class implements CRUD based methods for Event. This method updates an
	 * event. <br>
	 * 
	 * <br>
	 * A transaction takes place for the DB, which will be sent by DAO
	 * (<code>Update</code>)
	 * 
	 * @author Pablo Mafessoli <mafessolip@gmail.com>
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * 
	 * @param event the event object to be updated
	 * @return the id of the inserted object.
	 */
	@Override
	public int update(Object object) {
		Event event = (Event) object;
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(event);

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Event> criteria = builder.createQuery(Event.class);
			Root<Event> root = criteria.from(Event.class);
			criteria.select(root).where(builder.equal(root.get("name"), event.getName()));

			session.getTransaction().commit();
			session.close();
			return event.getId();
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * This class implements CRUD based methods for Event. This method deletes an
	 * event. <br>
	 * 
	 * <br>
	 * A transaction takes place for the DB, which will be sent by DAO
	 * (<code>Delete</code>)
	 * 
	 * @author Pablo Mafessoli <mafessolip@gmail.com>
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * 
	 * @param event the event object to be
	 * @return the id of the inserted object.
	 */
	@Override
	public int delete(int id) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Event event = session.find(Event.class, id);

		if (event == null)
			throw new IllegalArgumentException("Event is used by another entity");

		try {
			session.delete(event);
			session.getTransaction().commit();
			session.close();
			return event.getId();
		} catch (Exception e) {
			throw new IllegalArgumentException("Event is used by another entity");
		}
	}

	/**
	 * This class implements CRUD based methods for Event. This method finds an
	 * event. <br>
	 * 
	 * <br>
	 * A transaction takes place for the DB, which will be sent by DAO
	 * (<code>Find</code>)
	 * 
	 * @author Pablo Mafessoli <mafessolip@gmail.com>
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * 
	 * @param field the string of field to be used
	 * @param value the string of value of the field selected to use on search
	 * 
	 * @return the event object found
	 */
	@Override
	public Object find(String field, String value) {

		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Event> criteira = builder.createQuery(Event.class);
			Root<Event> root = criteira.from(Event.class);
			criteira.select(root).where(builder.equal(root.get(field), value));

			List<Event> data = session.createQuery(criteira).getResultList();

			session.getTransaction().commit();
			session.close();

			return (data.size() > 0) ? data.get(0) : null;
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * This class implements CRUD based methods for Event. This method finds an
	 * event. <br>
	 * 
	 * <br>
	 * A transaction takes place for the DB, which will be sent by DAO
	 * (<code>getAll</code>)
	 * 
	 * @author Pablo Mafessoli <mafessolip@gmail.com>
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * 
	 * @return the ArrayList of the Events in the database
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> ArrayList<T> getAll() {

		try {

			Session session = sessionFactory.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Event> criteria = builder.createQuery(Event.class);
			criteria.from(Event.class);
			List<Event> data = session.createQuery(criteria).getResultList();
			session.close();
			return (ArrayList<T>) data;

		} catch (Exception e) {
			throw e;
		}

	}

}