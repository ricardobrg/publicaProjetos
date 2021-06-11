package com.publica.grupo1.model.dao.contact;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.database.TestDBConnection;
import com.publica.grupo1.model.dao.HibernateDAO;
import com.publica.grupo1.model.entities.contact.Contact;

/**
 * Class that makes the basic actions of class Collaborator, like as : insert,
 * searching, updating and deleting.
 * 
 * This class uses only one session provided by {@link DBConnection} or, in test
 * cases, the {@link TestDBConnection} class, this classes close the session.
 * 
 * @author Pedro
 * 
 * 
 */
public class ContactDAO implements HibernateDAO<Contact> {
	private static ContactDAO instance;
	private Session session;

	private ContactDAO(Session session) {
		this.session = session;
	}

	/***
	 * If the ContactDAO was never created returns a new instance, otherwise returns
	 * the current instance.
	 * 
	 * @return the current instance of ContactDAO.
	 */
	public static ContactDAO getInstance(Session session) {
		if (instance == null)
			instance = new ContactDAO(session);
		return instance;
	}

	/***
	 * @return ServiceProvider object that contains given id
	 */
	@Override
	public Contact selectById(int id) {
		Contact contact = session.get(Contact.class, id);
		return contact;
	}

	/***
	 * Saves given object in database.
	 * 
	 * @return int: number of inserts
	 */
	@Override
	public int insert(Contact object) {
		int id = (int) session.save(object);
		return id;
	}

	/***
	 * Deletes given object from the database. The criteria is automatically it's
	 * <code>id</code>.
	 * 
	 * @return <code>void</code>
	 */
	@Override
	public void delete(Contact object) {
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		session.delete(object);
		session.getTransaction().commit();
	}

	/***
	 * Deletes given object from the database. The criteria is all objects where
	 * 'email' is <code>like</code> the string passed as parameter.
	 * 
	 * @return <code>void</code>
	 */
	/*
	 * public void deleteByEmail(String email) { if(!session.getTransaction().isActive()); session.beginTransaction();
	 * CriteriaBuilder cb = session.getCriteriaBuilder(); CriteriaDelete<Contact>
	 * criteriaDelete = cb.createCriteriaDelete(Contact.class); Root<Contact> root =
	 * criteriaDelete.from(Contact.class);
	 * criteriaDelete.where(cb.like(root.get("email"), email));
	 * 
	 * session.createQuery(criteriaDelete).executeUpdate();
	 * session.getTransaction().commit(); }
	 */

	/***
	 * Replaces object in table that haves the same ID as the one passed as
	 * argument.
	 * 
	 * @return <code>void</code>
	 */
	@Override
	public void update(Contact object) {
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		session.update(object);
		session.getTransaction().commit();
	}

	/**
	 * Returs every object in the Contact table.
	 * 
	 * @return List<Contact>.
	 */
	@Override
	public List<Contact> getAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Contact> criteria = builder.createQuery(Contact.class);
		criteria.from(Contact.class);
		List<Contact> list = session.createQuery(criteria).getResultList();
		return list;
	}

	/***
	 * Substitutes current instance with the one passed as parameter.
	 * 
	 * @param instance
	 */
	public static void setInstance(ContactDAO instance) {
		ContactDAO.instance = instance;
	}

	public static ContactDAO getInstance() {
		return instance;
	}

	public Session getSession() {
		return session;
	}

}
