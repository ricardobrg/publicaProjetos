package com.publica.grupo1.model.dao.serviceprovider;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import com.publica.grupo1.model.entities.serviceprovider.Payment;
import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.database.TestDBConnection;
import com.publica.grupo1.model.dao.HibernateDAO; 
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
public class PaymentDAO implements HibernateDAO<Payment>{

	private static PaymentDAO instance;
	private Session session;
	
	private PaymentDAO (Session session) {
		this.session = session;
	}
	
	/***
	 * If the PaymentDAO was never created returns a new instance,
	 * otherwise returns the current instance.
	 * 
	 * @return the current instance of PaymentDAO.
	 */
	public static PaymentDAO getInstance(Session session) {
		if (instance == null)
			instance = new PaymentDAO(session);
		return instance;
	}
	
	/***
	 * @return Payment object that contains given id
	 */
	@Override
	public Payment selectById(int id) {
		Payment payment = session.get(Payment.class, id);		
		return payment;
	}

	/***
	 * Saves given object in database.  
	 * @return int: number of inserts
	 */
	@Override
	public int insert(Payment object) {
		int id = (int) session.save(object);		
		return id;
	}

	/***
	 * Deletes given object from the database. The criteria is automatically
	 * it's <code>id</code>.
	 * @return <code>void</code>
	 */
	@Override
	public void delete(Payment object) {
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
	public void update(Payment object) {
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		session.update(object);
		session.getTransaction().commit();
	}

	/**
	 * @return a List with every object in the PaymentType table.
	 */

	@Override
	public List<Payment> getAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Payment> criteria = builder.createQuery(Payment.class);
		criteria.from(Payment.class);
		
		return session.createQuery(criteria).getResultList();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}



















