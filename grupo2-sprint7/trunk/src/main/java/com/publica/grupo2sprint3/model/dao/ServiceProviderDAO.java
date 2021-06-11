package com.publica.grupo2sprint3.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.publica.grupo2sprint3.model.dao.factory.HibernateConnectorFactory;
import com.publica.grupo2sprint3.model.person.legalperson.ServiceProvider;

/**
 * Class for Collaborator Services that allows adding, searching and editing
 * collaborators in an Arraylist.
 * 
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
 * @author Caio Shimada
 * @author Diego Borba <diegoborba25@gmail.com>
 * 
 * @version 2.1.0
 */
public class ServiceProviderDAO implements DAO {

	private static ServiceProviderDAO instance;
	Session session = HibernateConnectorFactory.getSessionFactory().openSession();

	ServiceProviderDAO() {

	}

	public static ServiceProviderDAO getInstance() {
		if (instance == null)
			instance = new ServiceProviderDAO();
		return instance;
	}

	/**
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
	 * @author Diego Borba <diegoborba25@gmail.com>
	 * 
	 *         Method for adding a Provider that extends from JuridicaPerson class.
	 * 
	 * @param newServiceProvider : ServiceProvider type.
	 */
	@Override
	public boolean add(Object newServiceProvider) {
		Session session = HibernateConnectorFactory.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			ServiceProvider sProvider = (ServiceProvider) newServiceProvider;
			if (sProvider.getAddress() != null)
				AddressDAO.getInstance().add(sProvider.getAddress());

			if (sProvider.getContact() != null)
				ContactDAO.getInstance().add(sProvider.getContact());

			session.save(newServiceProvider);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/***
	 * Recives one id, and search for this id in database
	 * 
	 * @param id
	 * @return Service Provider(id)
	 * @author Diego Borba <diegoborba25@gmail.com>
	 */
	@Override
	public ServiceProvider findById(int id) {
		ServiceProvider sProvider = session.find(ServiceProvider.class, id);
		return sProvider;
	}

	/***
	 * Recives one identifier, and search for this identifier in database
	 * 
	 * @param identifier
	 * @return Service Provider(identifier)
	 * @author Diego Borba <diegoborba25@gmail.com>
	 */
	@Override
	public Object findByIdentifier(String identifier) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ServiceProvider> criteria = builder.createQuery(ServiceProvider.class);
		Root<ServiceProvider> rootCollaborator = criteria.from(ServiceProvider.class);
		criteria.select(rootCollaborator).where(builder.like(rootCollaborator.<String>get("name"), identifier));

		List<ServiceProvider> providers = session.createQuery(criteria).getResultList();
		return providers.get(0);
	}

	/***
	 * Recives one id, and search for this id in database, and update the object to
	 * the newObject recived in parameters
	 * 
	 * @param id
	 * @param newProvider
	 * @return true/false(updated/not updated)
	 * @author Diego Borba <diegoborba25@gmail.com>
	 */
	@Override
	public boolean updateById(int id, Object newServiceProvider) {
		try {
			Session session = HibernateConnectorFactory.getSessionFactory().openSession();
			session.beginTransaction();
			ServiceProvider sProvider = (ServiceProvider) newServiceProvider;
			sProvider.setId(id);
			if (sProvider.getAddress() != null)
				AddressDAO.getInstance().add(sProvider.getAddress());

			if (sProvider.getContact() != null)
				ContactDAO.getInstance().add(sProvider.getContact());

			session.saveOrUpdate(sProvider);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/***
	 * Recives one identifier, and search for this identifier in database, and
	 * update the object to the newObject recived in parameters
	 * 
	 * @param identifier
	 * @param newProvider
	 * @return true/false(updated/not updated)
	 * @author Diego Borba <diegoborba25@gmail.com>
	 */
	@Override
	public boolean updateByIdentifier(String identifier, Object newProvider) {
		Session session = HibernateConnectorFactory.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(newProvider);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	/***
	 * Recives one id, and search for this id in database and remove the
	 * correspondent object.
	 * 
	 * @param id
	 * @return true/false(removed/not removed)
	 * @author Diego Borba <diegoborba25@gmail.com>
	 */
	@Override
	public boolean removeById(int id) {
		Session session = HibernateConnectorFactory.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.remove(session.find(ServiceProvider.class, id));
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/***
	 * Recives one identifier, and search for this identifier in database and remove
	 * the correspondent object.
	 * 
	 * @param identifier
	 * @return true/false(removed/not removed)
	 * @author Diego Borba <diegoborba25@gmail.com>
	 */
	public boolean removeByIdentifier(String identifier) {
		Session session = HibernateConnectorFactory.getSessionFactory().openSession();
		ServiceProvider coll = (ServiceProvider) findByIdentifier(identifier);
		session = HibernateConnectorFactory.getSessionFactory().openSession();
		session.beginTransaction();
		session.remove(coll);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	/***
	 * Method to get all providers that have in database
	 * 
	 * @return ArrayList<ServiceProvider>
	 * @author Diego Borba <diegoborba25@gmail.com>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ServiceProvider> getAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ServiceProvider> criteria = builder.createQuery(ServiceProvider.class);
		criteria.from(ServiceProvider.class);
		ArrayList<ServiceProvider> providers = (ArrayList<ServiceProvider>) session.createQuery(criteria)
				.getResultList();
		return providers;
	}
}
