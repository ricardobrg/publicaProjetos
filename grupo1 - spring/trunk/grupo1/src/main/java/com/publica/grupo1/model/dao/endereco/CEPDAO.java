package com.publica.grupo1.model.dao.endereco;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.database.TestDBConnection;
import com.publica.grupo1.model.dao.HibernateDAO;
import com.publica.grupo1.model.entities.endereco.CEP;

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
public class CEPDAO implements HibernateDAO<CEP> {
	
	private static CEPDAO instance;
	private Session session;
	
	private CEPDAO(Session session) {
		this.session = session;
	}
	
	/***
	 * If the CEPDAO was never created returns a new instance,
	 * otherwise returns the current instance.
	 * 
	 * @return the current instance of CEPDAO.
	 */
	public static CEPDAO getInstance(Session session) {
		if (instance == null)
			instance = new CEPDAO(session);
		return instance;
	}
	/***
	 * @return CEP object that contains given id
	 */
	@Override
	public CEP selectById(int id) {
		return session.get(CEP.class, id);		
	}

	/***
	 * Saves given object in database.  
	 * @return int: number of inserts
	 */
	@Override
	public int insert(CEP object) {
		int id = (int) session.save(object);
		return id;
	}

	/***
	 * Deletes given object from the database. The criteria is automatically
	 * it's <code>id</code>.
	 * @return <code>void</code>
	 */
	@Override
	public void delete(CEP object) {
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
	public void update(CEP object) {
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		session.update(object);
		session.getTransaction().commit();
	}
	
	/***
	 * Returns a list of CEP objects where 'cep' parameter is <code>like</code>
	 * CEP.cep
	 * 
	 * @param cep
	 * @return List<CEP> 
	 */
	public CEP selectByCEP(String cep) {
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<CEP> criteria = cb.createQuery(CEP.class);
		
		Root<CEP> root = criteria.from(CEP.class);
		criteria.where(cb.like(root.get("cep"), cep));
		
		return session.createQuery(criteria).getSingleResult();		
	}

	/**
	 * @return a List with every object in the CEP table.
	 */
	@Override
	public List<CEP> getAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<CEP> criteria = builder.createQuery(CEP.class);
		criteria.from(CEP.class);
		
		return session.createQuery(criteria).getResultList();
	}

	/***
	 * Substitutes current instance with the one passed as parameter.
	 * @param instance
	 */
	public static void setInstance(CEPDAO instance) {
		CEPDAO.instance = instance;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
}





