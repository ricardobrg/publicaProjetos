package com.publica.grupo1.model.dao.permissions;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.database.TestDBConnection;
import com.publica.grupo1.model.dao.HibernateDAO;
import com.publica.grupo1.model.entities.permissions.role.Role;


/**
 * Class that makes the basic actions of class Collaborator, like as : insert,
 * searching, updating and deleting.
 * 
 * This class uses only one session provided by {@link DBConnection} or, in test
 * cases, the {@link TestDBConnection} class, this classes close the session.
 * 
 * @author Vinicius
 * 
 * 
 */
public class RoleDAO implements HibernateDAO<Role> {

	private static RoleDAO instance;
	private Session session;

	/**
	 * The private constructor prevents to create multiple instances of a RoleDAO.
	 * It helps to not work with the database with more then one object.
	 */
	private RoleDAO(Session session) {
		this.session = session;
	}

	/**
	 * Verifies if already exists an instance of RoleDAO. If not, the method will
	 * iniciate it and then return.
	 * 
	 * @return a Instance of a DAO for roles
	 */
	public static RoleDAO getInstance(Session session) {
		if (instance == null) {
			instance = new RoleDAO(session);
		}
		return instance;
	}

	@Override
	public Role selectById(int id) {
		return session.get(Role.class, id);
	}
	
	public Role selectByName(String name) {
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Role> criteria = cb.createQuery(Role.class);

		Root<Role> root = criteria.from(Role.class);
		criteria.where(cb.like(root.get("name"), name));

		return session.createQuery(criteria).getResultList().get(0);
	}

	@Override
	public int insert(Role object) {
		return (int) session.save(object);
	}

	@Override
	public void delete(Role object) {
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		session.delete(object);
		session.getTransaction().commit();
	}

	@Override
	public void update(Role object) {
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		session.update(object);
		session.getTransaction().commit();
	}

	@Override
	public List<Role> getAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Role> criteria = builder.createQuery(Role.class);
		criteria.from(Role.class);

		List<Role> list = session.createQuery(criteria).getResultList();
		return list;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
