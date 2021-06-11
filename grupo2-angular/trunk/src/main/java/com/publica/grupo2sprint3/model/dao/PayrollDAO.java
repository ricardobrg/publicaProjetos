package com.publica.grupo2sprint3.model.dao;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import com.publica.grupo2sprint3.model.dao.factory.HibernateConnectorFactory;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Payroll;

/***
 * Month-end closing class, returns wages, 
 * and month-end closing information.
 * 
 * @author Diego Borba <diegoborba25@gmail.com>
 * Version 1.1.0-
 */
public class PayrollDAO implements DAO {
	
	private static PayrollDAO instance;
	
	PayrollDAO() {}
	
	public static PayrollDAO getInstance() {
		if (instance == null) 
			instance = new PayrollDAO();
		return instance;
	}
	
	@Override
	public boolean add(Object payroll) {
		Collaborator collab = ((Payroll) payroll).getCollaborator();
		CollaboratorDAO.getInstance().updateById(collab.getId(), collab);
		Session session = HibernateConnectorFactory.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(payroll);
		session.getTransaction().commit();
		session.close();
		return true;
	}
	
	@Override
	public Object findById(int id) {
		Session session = HibernateConnectorFactory.getSessionFactory().openSession();
		Payroll payroll = session.find(Payroll.class, id);
		return payroll;
	}
	
	@Override
	public Object findByIdentifier(String identifier) {
		return false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Payroll> getAll() {
		Session session = HibernateConnectorFactory.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Payroll> criteria = builder.createQuery(Payroll.class);
		criteria.from(Payroll.class);
		ArrayList<Payroll> payroll = (ArrayList<Payroll>) session.createQuery(criteria).getResultList();
		return payroll;
	}
	
	@Override
	public boolean updateByIdentifier(String identifier, Object collab) {
		return false;
	}
	@Override
	public boolean updateById(int id, Object payroll) {
		Session session = HibernateConnectorFactory.getSessionFactory().openSession();
		session.beginTransaction();
		CollaboratorDAO.getInstance().add(((Payroll) payroll).getCollaborator());
		session.saveOrUpdate(payroll);
		session.getTransaction().commit();
		session.close();
		return true;
	}
	@Override
	public boolean removeById(int id) {
		Session session = HibernateConnectorFactory.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.remove(session.find(Payroll.class, id));
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	@Override
	public boolean removeByIdentifier(String identifier) {
		Session session = HibernateConnectorFactory.getSessionFactory().openSession();
		session.beginTransaction();
		Payroll payroll = (Payroll) findByIdentifier(identifier);
		session.remove(payroll);
		session.getTransaction().commit();
		session.close();
		return true;
	}
}
