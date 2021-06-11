package model.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import model.entities.person.ServiceProvider;


/**
 * Class for accessing the database using Hibernate.<br>
 * 
 * It implements methods for inserting, updating, deleting, finding by id or cnpj
 * and selecting all Service Providers.
 * 
 * @version 1.6.0
 * 
 * @author Giovanni Buzzi
 * @author Caio Shimada
 *
 */
public class ServiceProviderHibernate extends HibernateDataAccess{
	
	AddressHibernate addrHibernate;
	ContactHibernate conHibernate;
			
	public ServiceProviderHibernate() {
		super();
		addrHibernate = new AddressHibernate();
		conHibernate = new ContactHibernate();
	}
	
	@Override
	public void setTestDatabase() {
		sessionFactory = HibernateConector.getTestSessionFactory();
		addrHibernate.setTestDatabase();
		conHibernate.setTestDatabase();
	}

	/**
	 * Inserts a new service provider in the database.
	 * 
	 * It doesn't verify if the address exists
	 * 
	 * @author Giovanni Buzzi
	 * 
	 * @param serviceProviderModel the Service Provider object to be added
	 * @return the id of the inserted object. Returns -1
	 * 		   if the operation failed
	 */
	@Override
	public int insert(Object object) {
		ServiceProvider serviceProvider = (ServiceProvider) object;

		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			session.save(serviceProvider.getContact());		 
			session.save(serviceProvider.getEndereco());		 
			int id = (int) session.save(serviceProvider);		 
			session.getTransaction().commit();
			session.close();
			
			return id;
			
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Updates an existing Service Provider in the database
	 * 
	 * @author Giovanni Buzzi
	 * 
	 * @param serviceProvider the Service Provider object to be updated
	 * @return the id of the inserted object. Returns -1
	 * 		   if the operation failed
	 */
	@Override
	public int update(Object object) {
		ServiceProvider serviceProvider = (ServiceProvider) object;
		
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			session.update(serviceProvider.getEndereco());
			session.update(serviceProvider.getContact());
			session.update(serviceProvider);		 
			
			session.getTransaction().commit();
			session.close();
			
			return serviceProvider.getId();
			
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Deletes a Service Provider in the database.
	 * 
	 * Tries to delete address and contact, if they are used by someone
	 * else, they will not be deleted.
	 * 
	 * @author Giovanni Buzzi
	 * @author Caio Shimada
	 * 
	 * @param id the id of the Service Provider object to be deleted
	 * @return the id of the inserted object. Returns -1
	 * 		   if the operation failed
	 */
	@Override
	public int delete(int id) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			ServiceProvider serviceProvider = session.load(ServiceProvider.class, id);
			session.delete(serviceProvider);
			
			session.getTransaction().commit();
			
			addrHibernate.delete(serviceProvider.getEndereco().getId());
			conHibernate.delete(serviceProvider.getContact().getId());

			session.close();
			
			return serviceProvider.getId();
			
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Finds a Service Provider in the database given a field and value.
	 * 
	 * @author Giovanni Buzzi
	 * 
	 * @param field the string of field to be used
	 * @param value the string of value of the field selected to use on search
	 * @return the Service Provider object found
	 */
	@Override
	public Object find(String field, String value) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
		
			CriteriaBuilder builder = session.getCriteriaBuilder();
		    CriteriaQuery<ServiceProvider> criteria = builder.createQuery(ServiceProvider.class);
		    Root<ServiceProvider> root = criteria.from(ServiceProvider.class);
		    criteria.select(root).where(builder.equal(root.get(field), value));
		    
		    List<ServiceProvider> data = session.createQuery(criteria).getResultList();
			
			session.getTransaction().commit();
			session.close();
			
			return (data.size() > 0) ? data.get(0) : null;
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retrieves every Service Provider in the database.
	 * 
	 * @author Giovanni Buzzy
	 * 
	 * @return a List of Service Providers in the database
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> ArrayList<T> getAll() {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
		    CriteriaQuery<ServiceProvider> criteria = builder.createQuery(ServiceProvider.class);
		    criteria.from(ServiceProvider.class);
		    List<ServiceProvider> data = session.createQuery(criteria).getResultList();
		    session.close();
		    
		    return (ArrayList<T>) data;

		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	

}
