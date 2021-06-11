package com.publica.grupo1.model.dao.contact;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.database.TestDBConnection;
import com.publica.grupo1.model.dao.HibernateDAO;
import com.publica.grupo1.model.entities.contact.NumberPhone;

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
public class NumberPhoneDAO implements HibernateDAO<NumberPhone> {

	private static NumberPhoneDAO instance;
	private Session session;

	private NumberPhoneDAO(Session session) {
		this.session = session;
	}

	/***
	 * If the NumberPhoneDAO was never created returns a new instance, otherwise
	 * returns the current instance. *
	 * 
	 * @return the current instance of NumberPhoneDAO.
	 */
	public static NumberPhoneDAO getInstance(Session session) {
		if (instance == null)
			instance = new NumberPhoneDAO(session);
		return instance;
	}

	/***
	 * @return NumberPhone object that contains given id
	 */
	@Override
	public NumberPhone selectById(int id) {
		NumberPhone numberPhone = session.get(NumberPhone.class, id);
		return numberPhone;
	}

	/***
	 * Saves given object in database.
	 * 
	 * @return int: number of inserts
	 */
	@Override
	public int insert(NumberPhone object) {
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
	public void delete(NumberPhone object) {
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		session.delete(object);
		session.getTransaction().commit();
	}

	/***
	 * Replaces object in table that haves the same ID as the one passed as
	 * argument.
	 * 
	 * @return <code>void</code>
	 */
	@Override
	public void update(NumberPhone object) {
		if(!session.getTransaction().isActive()) 
			session.beginTransaction();
		session.update(object);
		session.getTransaction().commit();
	}

	/**
	 * @return a List with every object in the NumberPhone table.
	 */
	@Override
	public List<NumberPhone> getAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<NumberPhone> criteria = builder.createQuery(NumberPhone.class);
		criteria.from(NumberPhone.class);
		List<NumberPhone> list = session.createQuery(criteria).getResultList();
		return list;
	}

	public List<NumberPhone> getAllByDDD(String dd) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<NumberPhone> criteria = builder.createQuery(NumberPhone.class);
		Root<NumberPhone> root = criteria.from(NumberPhone.class);
		criteria.where(builder.like(root.get("dd"), dd));
		List<NumberPhone> list = session.createQuery(criteria).getResultList();
		return list;
	}

	/***
	 * Substitutes current instance with the one passed as parameter.
	 * 
	 * @param instance
	 */
	public static void setInstance(NumberPhoneDAO instance) {
		NumberPhoneDAO.instance = instance;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}






















