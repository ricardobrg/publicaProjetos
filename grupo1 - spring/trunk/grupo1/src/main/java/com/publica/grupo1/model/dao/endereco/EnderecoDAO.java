package com.publica.grupo1.model.dao.endereco;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.database.TestDBConnection;
import com.publica.grupo1.model.dao.HibernateDAO;
import com.publica.grupo1.model.entities.endereco.Endereco;

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
public class EnderecoDAO implements HibernateDAO<Endereco>{
	private static EnderecoDAO instance;
	private Session session;

	private EnderecoDAO (Session session) {
		this.session = session;
	}

	/***
	 * If the EnderecoDAO was never created returns a new instance,
	 * otherwise returns the current instance.
	 * 
	 * @return the current instance of EnderecoDAO.
	 */
	public static EnderecoDAO getInstance(Session session) {
		if (instance == null)
			instance = new EnderecoDAO(session);
		
		return instance;
	}

	/***
	 * @return Endereco object that contains given id
	 */
	@Override
	public Endereco selectById(int id) {
		return session.get(Endereco.class, id);
	}

	/***
	 * Saves given object in database.  
	 * @return int: number of inserts
	 */
	@Override
	public int insert(Endereco object) {
		int id = (int) session.save(object);
		return id;
	}

	/***
	 * Deletes given object from the database. The criteria is automatically
	 * it's <code>id</code>.
	 * @return <code>void</code>
	 */
	@Override
	public void delete(Endereco object) {
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
	public void update(Endereco object) {
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		session.update(object);
		session.getTransaction().commit();
	}

	/**
	 * @return a List with every object in the Endereco table.
	 */
	@Override
	public List<Endereco> getAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Endereco> criteria = builder.createQuery(Endereco.class);
		criteria.from(Endereco.class);

		return session.createQuery(criteria).getResultList();
	}
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}









