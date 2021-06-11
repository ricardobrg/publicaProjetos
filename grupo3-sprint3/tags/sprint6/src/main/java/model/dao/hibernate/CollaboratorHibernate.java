package model.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import model.entities.person.Collaborator;
import model.entities.person.Endereco;

/**
 * Class for accessing the database using Hibernate.<br>
 * 
 * It implements methods for inserting, updating, deleting, finding by id or cpf
 * and selecting all Collaborators.
 * 
 * @version 1.5.0
 * 
 * @author Caio Shimada
 *
 */
public class CollaboratorHibernate extends HibernateDataAccess{
	
	AddressHibernate addrHibernate;
	ContactHibernate conHibernate;
	
	public CollaboratorHibernate() {
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
	 * Inserts a new collaborator in the database.
	 * 
	 * Verifies if the address already exist. If it does,
	 * it will be set as current and a new won't be created.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param collaborator the Collaborator object to be added
	 * @return the id of the inserted object. Returns -1
	 * 		   if the operation failed
	 */
	@Override
	public int insert(Object object) {
		Collaborator collaborator = (Collaborator) object;
		
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();

			Endereco addr = addrHibernate.find(
					collaborator.getEndereco().getCEP(),
					collaborator.getEndereco().getComplemento());
			
			if (addr == null) 
				addrHibernate.insert(collaborator.getEndereco());
			else
				collaborator.setEndereco(addr);
			
			session.save(collaborator.getContact());

			int id = (int) session.save(collaborator);

			session.getTransaction().commit();
			session.close();
			
			return id;

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Updates an existing collaborator in the database
	 * 
	 * Verifies if the address already exist. 
	 * If it does, the existing will be set as current, and the 
	 * current will be deleted.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param collaborator the Collaborator object to be updated
	 * @return the id of the inserted object. Returns -1
	 * 		   if the operation failed
	 */
	@Override
	public int update(Object object) {
		Collaborator collaborator = (Collaborator) object;
		
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			Endereco addr = addrHibernate.find(
					collaborator.getEndereco().getCEP(),
					collaborator.getEndereco().getComplemento());
			
			int toDelete = -1;
			if (addr == null)
				addrHibernate.update(collaborator.getEndereco());
			else {
				toDelete = collaborator.getEndereco().getId();
				collaborator.setEndereco(addr);
			}

			session.update(collaborator.getContact());
			session.update(collaborator);

			if (toDelete != -1)
				addrHibernate.delete(toDelete);
			
			session.getTransaction().commit();
			session.close();
			
			return collaborator.getId();

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Deletes a collaborator in the database.
	 * 
	 * Tries to delete address and contact, if they are used by someone
	 * else, they will not be deleted.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param id the id of the Collaborator object to be deleted
	 * @return the id of the inserted object. Returns -1
	 * 		   if the operation failed
	 */
	@Override
	public int delete(int id) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();

			Collaborator collaborator = session.find(Collaborator.class, id);
			
			session.delete(collaborator);
			session.getTransaction().commit();
			
			addrHibernate.delete(collaborator.getEndereco().getId());
			conHibernate.delete(collaborator.getContact().getId());

			session.close();
			
			return collaborator.getId();

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Finds a collaborator in the database by the input and value given.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param field the string of field to be used
	 * @param value the string of value of the field selected to use on search
	 * @return the Collaborator object found
	 */
	@Override
	public Object find(String field, String value) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Collaborator> criteria = builder.createQuery(Collaborator.class);
			Root<Collaborator> root = criteria.from(Collaborator.class);
			criteria.select(root).where(builder.equal(root.get(field), value));
			List<Collaborator> data = session.createQuery(criteria).getResultList();

			session.getTransaction().commit();
			session.close();

			return (data.size() > 0) ? data.get(0) : null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retrieves every Collaborator in the database.
	 * 
	 * @author Caio Shimada
	 * 
	 * @return a List of Collaborators in the database
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> ArrayList<T> getAll() {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Collaborator> criteria = builder.createQuery(Collaborator.class);

			criteria.from(Collaborator.class);
			List<Collaborator> data = session.createQuery(criteria).getResultList();

			session.getTransaction().commit();
			session.close();
			
			return (ArrayList<T>) data;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
