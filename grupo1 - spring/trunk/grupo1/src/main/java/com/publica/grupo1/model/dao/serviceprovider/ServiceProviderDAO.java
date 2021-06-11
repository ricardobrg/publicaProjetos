package com.publica.grupo1.model.dao.serviceprovider;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.database.TestDBConnection;
import com.publica.grupo1.model.dao.HibernateDAO;
import com.publica.grupo1.model.entities.serviceprovider.ServiceProvider;
import com.publica.grupo1.util.validation.number.CNPJValidation;

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
public class ServiceProviderDAO implements HibernateDAO<ServiceProvider> {
	private static ServiceProviderDAO instance;
	private Session session;

	private ServiceProviderDAO (Session session) {
		this.session = session;
	}

	/***
	 * If the ServiceProviderDAO was never created returns a new instance,
	 * otherwise returns the current instance.
	 * 
	 * @return the current instance of ServiceProviderDAO.
	 */
	public static ServiceProviderDAO getInstance(Session session) {
		if (instance == null)
			instance = new ServiceProviderDAO(session);
		return instance;
	}

	/***
	 * @return ServiceProvider object that contains given id
	 */
	@Override
	public ServiceProvider selectById(int id) {
		ServiceProvider serviceProvider = session.get(ServiceProvider.class, id);

		return serviceProvider;
	}

	/***
	 * Selects a Service Provider on database that contains the cnpj passed
	 * as parameter.
	 * 
	 * @param cnpj
	 * @return Service Provider
	 */
	public ServiceProvider selectByCnpj(String cnpj) {
		if(!CNPJValidation.cnpjValidation(cnpj))
			return null;

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<ServiceProvider> criteria = cb.createQuery(ServiceProvider.class);
		Root<ServiceProvider> root = criteria.from(ServiceProvider.class);
		criteria.where(cb.like(root.get("cnpj"), cnpj));

		return session.createQuery(criteria).getSingleResult();
	}

	/***
	 * Saves given object in database.  
	 * @return int: number of inserts
	 */
	@Override
	public int insert(ServiceProvider object) {
		int id = (int) session.save(object);

		return id;
	}

	/***
	 * Deletes given object from the database. The criteria is automatically
	 * it's <code>id</code>.
	 * @return <code>void</code>
	 */
	@Override
	public void delete(ServiceProvider object) {
		if(!session.getTransaction().isActive()); session.beginTransaction();
		session.delete(object);
		session.getTransaction().commit();
	}

	/***
	 * Deletes the object in database that contains the CNPJ passed as parameter.
	 * 
	 * @param cnpj
	 */

	public void deleteByCnpj(String cnpj) {
		if(!CNPJValidation.cnpjValidation(cnpj)) 
			return;
		
		if(!session.getTransaction().isActive())
			session.beginTransaction();		
		session.delete(selectByCnpj(cnpj));
		session.getTransaction().commit(); 
	}

	/***
	 * Replaces object in table that haves the same ID as the one passed
	 * as argument.
	 * @return <code>void</code>
	 */
	@Override
	public void update(ServiceProvider object) {
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		session.update(object);
		session.getTransaction().commit();
	}

	/**
	 * @return a List with every object in the ServiceProvider table.
	 */
	@Override
	public List<ServiceProvider> getAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ServiceProvider> criteria = builder.createQuery(ServiceProvider.class);
		criteria.from(ServiceProvider.class);

		return session.createQuery(criteria).getResultList();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}














