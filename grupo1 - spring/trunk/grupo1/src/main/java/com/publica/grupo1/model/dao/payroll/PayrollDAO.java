package com.publica.grupo1.model.dao.payroll;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.database.TestDBConnection;
import com.publica.grupo1.model.dao.HibernateDAO;
import com.publica.grupo1.model.entities.payroll.Payroll;
/**
 * Class that makes the basic actions of class Collaborator, like as : insert,
 * searching, updating and deleting.
 * 
 * This class uses only one session provided by {@link DBConnection} or, in test
 * cases, the {@link TestDBConnection} class, this classes close the session.
 * 
 * @author Diego Leon
 * 
 * 
 */
public class PayrollDAO implements HibernateDAO<Payroll> {

	private static PayrollDAO instance;
	private Session session;

	private PayrollDAO(Session session) {
		this.session = session;
	}

	public static PayrollDAO getInstance(Session session) {
		if (instance == null)
			instance = new PayrollDAO(session);
		return instance;
	}

	@Override
	public Payroll selectById(int id) {
		Payroll selectedPayroll = session.get(Payroll.class, id);
		return selectedPayroll;
	}
	
	@Override
	public int insert(Payroll object) {
		int id = (int) session.save(object);
		return id;
	}

	@Override
	public void delete(Payroll object) {
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		session.delete(object);
		session.getTransaction().commit();

	}

	@Override
	public void update(Payroll object) {
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		session.update(object);
		session.getTransaction().commit();
	}

	@Override
	public List<Payroll> getAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Payroll> criteria = builder.createQuery(Payroll.class);
		criteria.from(Payroll.class);
		List<Payroll> list = session.createQuery(criteria).getResultList();

		return list;
	}

	public Session getSession() {
		return session;
	}
	
	public void setSession(Session session) {
		this.session = session;
	}
}











