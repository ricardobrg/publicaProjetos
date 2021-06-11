package com.publica.grupo2sprint3.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.publica.grupo2sprint3.model.dao.factory.HibernateConnectorFactory;
import com.publica.grupo2sprint3.model.person.Contact;

public class ContactDAO implements DAO {
	
	private static ContactDAO instance;

	ContactDAO() {

	}

	public static ContactDAO getInstance() {
		if (instance == null)
			instance = new ContactDAO();
		return instance;
	}

	/**
	 * Register a new Contact in database.
	 * <br>
	 * @author Ana <carolsantos2002@gmail.com>
	 * @author Jess� <jesse.amaro7@gmail.com>
	 * 
	 * @param new contact to be registered.
	 */
	@Override
	public boolean add(Object contact) {
		Session session = HibernateConnectorFactory.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(contact);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	/**
	 * Find a Contact in database for your id. 
	 * <br>
	 * @author Ana <carolsantos2002@gmail.com>
	 * @author Jess� <jesse.amaro7@gmail.com>
	 * 
	 * @param id 
	 */
	@Override
	public Object findById(int id) {
		Session session = HibernateConnectorFactory.getSessionFactory().openSession();
		Contact contact = session.find(Contact.class, id);
		return contact;
	}

	/**
	 * Find a Contact in database for your identifier: name. 
	 * <br>
	 * @author Ana <carolsantos2002@gmail.com>
	 * @author Jess� <jesse.amaro7@gmail.com>
	 * 
	 * @param identifier : name.
	 */
	@Override
	public Contact findByIdentifier(String identifier) {
		Session session = HibernateConnectorFactory.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Contact> criteria = builder.createQuery(Contact.class);
		Root<Contact> rootContact = criteria.from(Contact.class);
		criteria.select(rootContact).where(builder.like(rootContact.<String>get("phone"), identifier));

		List<Contact> contacts = session.createQuery(criteria).getResultList();
		return contacts.get(0);
	}

	/**
	 * Update a Contact in database for your id. 
	 * <br>
	 * @author Ana <carolsantos2002@gmail.com>
	 * @author Jess� <jesse.amaro7@gmail.com>
	 * 
	 * @param id
	 * @param newContact
	 */
	@Override
	public boolean updateById(int id, Object newContact) {
		Session session = HibernateConnectorFactory.getSessionFactory().openSession();
		session = HibernateConnectorFactory.getSession();
		((Contact) newContact).setId(id);
		session.beginTransaction();
		session.saveOrUpdate(newContact);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	/**
	 * Update a contact for your identifier: phone.
	 * <br>
	 * @author Ana <carolsantos2002@gmail.com>
	 * @author Jess� <jesse.amaro7@gmail.com>
	 * 
	 * @param identifier
	 * @param newContact
	 */
	@Override
	public boolean updateByIdentifier(String identifier, Object newContact) {
		Session session = HibernateConnectorFactory.getSessionFactory().openSession();
		Contact cont = (Contact) findByIdentifier(identifier);
		try {
			session.beginTransaction();
			session.save(cont);
			session.remove(cont);
			session.save(newContact);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Remove a contact for your id.
	 * <br>
	 * @author Ana <carolsantos2002@gmail.com>
	 * @author Jess� <jesse.amaro7@gmail.com>
	 * 
	 * @param identifier
	 */
	@Override
	public boolean removeById(int id) {
		Contact cont = (Contact) findById(id);
		Session session = HibernateConnectorFactory.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(cont);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	/**
	 * Remove a contact for your identifier: phone.
	 * <br>
	 * @author Ana <carolsantos2002@gmail.com>
	 * @author Jess� <jesse.amaro7@gmail.com>
	 * 
	 * @param identifier
	 */
	@Override
	public boolean removeByIdentifier(String identifier) {
		Session session = HibernateConnectorFactory.getSessionFactory().openSession();
		Contact cont = (Contact) findByIdentifier(identifier);
		try {
			session.beginTransaction();
			session.remove(cont);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Get all contacts in database.
	 * <br>
	 * @author Ana <carolsantos2002@gmail.com>
	 * @author Jess� <jesse.amaro7@gmail.com>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Contact> getAll() {
		Session session = HibernateConnectorFactory.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Contact> criteria = builder.createQuery(Contact.class);
		criteria.from(Contact.class);
		ArrayList<Contact> contacts = (ArrayList<Contact>) session.createQuery(criteria).getResultList();
		return contacts;
	}
}
