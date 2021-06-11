package com.publica.grupo2sprint3.model.dao;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import com.publica.grupo2sprint3.model.dao.factory.HibernateConnectorFactory;
import com.publica.grupo2sprint3.model.person.physicalperson.Discount;

/***
 * Class for manage an discount
 * 
 * @author Diego Borba <diegoborba25@gmail.com>
 * Version 1.1.0
 */
public class DiscountDAO implements DAO {
	
	private static DiscountDAO instance;
  	private Session session;
	
	DiscountDAO() {}
	
	public static DiscountDAO getInstance() {
		if (instance == null) 
			instance = new DiscountDAO();
		return instance;
	}
	
	@Override
	public boolean add(Object discount) {
		session = HibernateConnectorFactory.getSessionFactory().openSession();
		session.beginTransaction();
		session.save((Discount)discount);
		session.getTransaction().commit();
		session.close();
		return true;
	}
	
	@Override
	public Object findById(int id) {
		session = HibernateConnectorFactory.getSessionFactory().openSession();
		Discount discount = session.find(Discount.class, id);
		session.close();
		return discount;
	}
	
	@Override
	public Object findByIdentifier(String identifier) {
		session = HibernateConnectorFactory.getSessionFactory().openSession();
		session.beginTransaction();
		Discount discount = (Discount) findByIdentifier(identifier);
		session.remove(discount);
		session.getTransaction().commit();
		session.close();
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Discount> getAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Discount> criteria = builder.createQuery(Discount.class);
		criteria.from(Discount.class);
		ArrayList<Discount> discount = (ArrayList<Discount>) session.createQuery(criteria).getResultList();
		return discount;
	}
	
	@Override
	public boolean updateByIdentifier(String identifier, Object collab) {
		return false;
	}
	
	@Override
	public boolean updateById(int id, Object discount) {
		session = HibernateConnectorFactory.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(discount);
		session.getTransaction().commit();
		session.close();
		return true;
	}
	@Override
	public boolean removeById(int id) {
		session = HibernateConnectorFactory.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.remove(session.find(Discount.class, id));
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	@Override
	public boolean removeByIdentifier(String identifier) {
		session = HibernateConnectorFactory.getSessionFactory().openSession();
		session.beginTransaction();
		Discount discount = (Discount) findByIdentifier(identifier);
		session.remove(discount);
		session.getTransaction().commit();
		session.close();
		return true;
	}
}

