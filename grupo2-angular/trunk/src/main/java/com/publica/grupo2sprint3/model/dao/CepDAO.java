package com.publica.grupo2sprint3.model.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

import com.publica.grupo2sprint3.model.dao.factory.HibernateConnectorFactory;
import com.publica.grupo2sprint3.model.person.Cep;

/*
 * Class for Cep Services that allows adding, list, editing and
 * remove the cep.
 * 
 * @author Ana <carolsantos2002@gmail.com>
 * @author Jessé Amaro <jesse.amaro7@gmail.com>
 */

public class CepDAO implements DAO {

	Session session; 
	private static CepDAO instance;

	public CepDAO() {

	}

	public static CepDAO getInstance() {
		if (instance == null)
			instance = new CepDAO();
		return instance;
	}

	/**
	 * Register a new Cep if its name is valid and it doesn't already exists
	 * 
	 * @author Ana <carolsantos2002@gmail.com>
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 */
	@Override
	public boolean add(Object cep) {
		session =  HibernateConnectorFactory.getSessionFactory().openSession();
		session.beginTransaction();
		session.save((Cep) cep);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	@Override
	public Object findById(int cep) {
		// return session.find(Cep.class, Cep.getInstance(String.valueOf(cep)));
		return false;
	}

	/**
	 * Search Cep from the String id cep
	 * 
	 * It will be useful to search and verify if a cep already exist.
	 *
	 * @author Ana <carolsanto2002@gmail.com>
	 * @author Jessé Amaro <jesse.amaro@gmail.com>
	 */
	@Override
	public Object findByIdentifier(String identifier) {
		session =  HibernateConnectorFactory.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Cep> criteria = builder.createQuery(Cep.class);
		Root<Cep> rootCep = criteria.from(Cep.class);
		criteria.select(rootCep).where(builder.like(rootCep.<String>get("cep"), identifier));

		List<Cep> ceps = session.createQuery(criteria).getResultList();
		if (ceps.size() != 0)
			return ceps.get(0);

		return null;
	}

	/**
	 * Update the Cep from the id cep
	 * 
	 * This method will update the cep informations.
	 *
	 * @author Ana <carolsanto2002@gmail.com>
	 * @author Jessé Amaro <jesse.amaro@gmail.com>
	 */
	@Override
	public boolean updateById(int id, Object cep) {
		session =  HibernateConnectorFactory.getSessionFactory().openSession();
		session.beginTransaction();
		Cep cep_ = (Cep) findById(id);
		session.save(cep_);
		session.remove(cep_);
		session.save(cep);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	/**
	 * Update Cep from the String id cep
	 * 
	 * It will be useful to update a cep.
	 *
	 * @author Ana <carolsanto2002@gmail.com>
	 * @author Jessé Amaro <jesse.amaro@gmail.com>
	 */
	@Override
	public boolean updateByIdentifier(String identifier, Object cep) {
		session = HibernateConnectorFactory.getSessionFactory().openSession();
		Cep cep_ = (Cep) findByIdentifier(identifier);
		try {
			session.beginTransaction();
			session.save(cep_);
			session.remove(cep_);
			session.save(cep);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
	}
}

	/**
	 * Remove Cep from the id cep
	 * 
	 * This method is used to remove a cep by id.
	 *
	 * @author Ana <carolsanto2002@gmail.com>
	 * @author Jessé Amaro <jesse.amaro@gmail.com>
	 */
	@Override
	public boolean removeById(int id) {
		session =  HibernateConnectorFactory.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.remove(session.find(Cep.class, id));
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Remove Cep from the String id cep
	 * 
	 * This method is used to remove a cep by identifier
	 *
	 * @author Ana <carolsanto2002@gmail.com>
	 * @author Jessé Amaro <jesse.amaro@gmail.com>
	 */
	@Override
	public boolean removeByIdentifier(String identifier) {
		Cep cep = (Cep) findByIdentifier(identifier);
		session =  HibernateConnectorFactory.getSessionFactory().openSession();
		session.beginTransaction();
		session.remove(cep);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	/**
	 * Get all Cep from the Cep's list
	 * 
	 * This method is to get all the Ceps from the ArrayList(ceps).
	 *
	 * @author Ana <carolsanto2002@gmail.com>
	 * @author Jessé Amaro <jesse.amaro@gmail.com>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Cep> getAll() {
		session =  HibernateConnectorFactory.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Cep> criteria = builder.createQuery(Cep.class);
		criteria.from(Cep.class);
		ArrayList<Cep> ceps = (ArrayList<Cep>) session.createQuery(criteria).getResultList();
		return ceps;
	}
}