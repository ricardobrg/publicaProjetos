package com.publica.grupo1.model.dao.serviceprovider;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import com.publica.grupo1.database.TestDBConnection;
import com.publica.grupo1.model.dao.HibernateDAO;
import com.publica.grupo1.model.entities.serviceprovider.PaymentType;
/**
 * Class that makes the basic actions of class Collaborator, like as : insert,
 * searching, updating and deleting.
 * 
 * This class uses only one session provided by {@link DBConnection} or, in test
 * cases, the {@link TestDBConnection} class, this classes close the session.
 * 
 * @author Diego
 * 
 * 
 */
public class PaymentTypeDAO implements HibernateDAO<PaymentType> {
	
	private static PaymentTypeDAO instance;
	private Session session;
	
	private PaymentTypeDAO (Session session) {
		this.session = session;
	}
	
	/***
	 * If the PaymentTypeDAO was never created returns a new instance,
	 * otherwise returns the current instance.
	 * 
	 * @return the current instance of PaymentTypeDAO.
	 */
	public static PaymentTypeDAO getInstance(Session session) {
		if (instance == null)
			instance = new PaymentTypeDAO(session);
		return instance;
	}
	
	/***
	 * @return PaymentType object that contains given id
	 */
	@Override
	public PaymentType selectById(int id) {
		PaymentType paymentType = session.get(PaymentType.class, id);
		
		return paymentType;
	}

	/***
	 * Saves given object in database.  
	 * @return int: number of inserts
	 */
	@Override
	public int insert(PaymentType object) {
		int id = (int) session.save(object);
		
		return id;
	}

	/***
	 * Deletes given object from the database. The criteria is automatically
	 * it's <code>id</code>.
	 * @return <code>void</code>
	 */
	@Override
	public void delete(PaymentType object) {
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		session.delete(object);
		session.getTransaction().commit();
	}

	/***
	 * Replaces object in table that haves the same ID as the one passed
	 * as argument.
	 * @return <code>void</code>
	 */
	@Override
	public void update(PaymentType object) {
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		session.update(object);
		session.getTransaction().commit();
	}

	/**
	 * @return a List with every object in the PaymentType table.
	 */
	@Override
	public List<PaymentType> getAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<PaymentType> criteria = builder.createQuery(PaymentType.class);
		criteria.from(PaymentType.class);
		
		return session.createQuery(criteria).getResultList();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
