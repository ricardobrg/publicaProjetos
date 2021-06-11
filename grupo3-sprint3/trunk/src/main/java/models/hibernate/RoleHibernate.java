package models.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import models.entities.security.Role;

/***
 * RoleHibernate Class.<br>
 * This class implements CRUD base methods for Role. Each method opens a Session
 * Factory, does the query and closes it.
 * 
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @version 1.0.0
 */
public class RoleHibernate {
	
	private SessionFactory sessionFactory;
	
	public RoleHibernate() {
		this.sessionFactory = HibernateConector.getSessionFactory();
	}
	
	public void setTestDatabase() {
		this.sessionFactory = HibernateConector.getTestSessionFactory();
	}

	/***
	 * RoleHibernate Add.<br>
	 * This class implements CRUD base methods for Role. This add method adds role
	 * and commits this transaction to DB, which will be sent by the DAO.
	 * 
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * 
	 * @param event the Role object to be inserted
	 * @return the id of the inserted object. Returns -1
	 * 		   if the operation failed
	 * 
	 * @throws Exception
	 */
	public int insert(Object object) throws Exception {
		Role role = (Role) object;
		
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();

			int id = (int) session.save(role);
			session.getTransaction().commit();

			return id;
			
		} catch (Exception e) {
			throw e;
		}
	}

	/***
	 * RoleHibernate Update.<br>
	 * This class implements CRUD base methods for Role. This update method edits a
	 * role and commits this transaction to DB, which will be sent by the DAO.
	 * 
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * 
	 * @param event the Role object to be updated
	 * @return the id of the inserted object. Returns -1
	 * 		   if the operation failed
	 * 
	 * @throws Exception
	 */
	public int update(Object object) throws Exception {
		Role role = (Role) object;
		
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(role);

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Role> criteria = builder.createQuery(Role.class);
			Root<Role> root = criteria.from(Role.class);
			criteria.select(root).where(builder.equal(root.get("name"), role.getName()));

			session.getTransaction().commit();
			session.close();
			
			return role.getId();

		} catch (Exception e) {
			throw e;
		}
	}

	/***
	 * RoleHibernate Delete.<br>
	 * This class implements CRUD base methods for Role. This delete method deletes
	 * a role and commits this transaction to DB, which will be sent by the DAO.
	 * 
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * @author Caio Shimada
	 * 
	 * @param id the id of the Role to be deleted
	 * @return the id of the inserted object. Returns -1
	 * 		   if the operation failed
	 * 
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public int delete(int id) throws IllegalArgumentException, Exception {

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Role role = session.find(Role.class, id);
		
		if (role == null) 
			throw new IllegalArgumentException("Role doesn't exist");
		
		try {
			session.delete(role);
			session.getTransaction().commit();
			session.close();
			
			return role.getId();
			
		} catch(Exception e) {
			throw new Exception("Role is used by another entity");
		}
		
	}

	/***
	 * RoleHibernate Find.<br>
	 * This class implements CRUD base methods for Role. This find method finds a
	 * role via a field and value and commits this transaction to DB, which will 
	 * be sent by the DAO.
	 * 
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * 
	 * @param field the string of field to be used
	 * @param value the string of value of the field selected to use on search
	 * @return the Role object found
	 * 
	 * @throws Exception
	 */
	public Object find(String field, String value) throws Exception {
		
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
		    CriteriaQuery<Role> criteria = builder.createQuery(Role.class);
		    Root<Role> root = criteria.from(Role.class);
		    criteria.select(root).where(builder.equal(root.get(field), value));
		    
		    List<Role> data = session.createQuery(criteria).getResultList();

			session.getTransaction().commit();
			session.close();
			
			return (data.size() > 0) ? data.get(0) : null;

		} catch (Exception e) {
			throw e;
		}
	}

	/***
	 * RoleHibernate Get All.<br>
	 * This class implements CRUD base methods for Role. This method lists all roles
	 * and commits this transaction to DB, which will be sent by the DAO.
	 * 
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * 
	 * @return the ArrayList of the Roles in the database
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T> ArrayList<T> getAll() throws Exception {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Role> criteria = builder.createQuery(Role.class);
			criteria.from(Role.class);
			List<Role> data = session.createQuery(criteria).getResultList();
			session.close();

			return (ArrayList<T>) data;
			
		} catch (Exception e) {
			throw e;
		}
		
	}

}
