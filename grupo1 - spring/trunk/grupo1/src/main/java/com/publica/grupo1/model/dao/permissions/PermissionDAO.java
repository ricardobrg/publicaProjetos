package com.publica.grupo1.model.dao.permissions;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.database.TestDBConnection;
import com.publica.grupo1.model.dao.HibernateDAO;
import com.publica.grupo1.model.entities.permissions.Permission;
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
public class PermissionDAO implements HibernateDAO<Permission> {
	private static PermissionDAO instance;
	private Session session;

	private PermissionDAO(Session session) {
		this.session = session;
	}

	public static PermissionDAO getInstance(Session session) {
		if (instance == null)
			instance = new PermissionDAO(session);
		return instance;
	}

	@Override
	public Permission selectById(int id) {
		return session.get(Permission.class, id);
	}

	@Override
	public int insert(Permission object) {
		return (int) session.save(object);
	}

	@Override
	public void delete(Permission object) {
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		session.delete(object);
		session.getTransaction().commit();
	}

	@Override
	public void update(Permission object) {
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		session.update(object);
		session.getTransaction().commit();
	}

	@Override
	public List<Permission> getAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Permission> criteria = builder.createQuery(Permission.class);
		criteria.from(Permission.class);

		return session.createQuery(criteria).getResultList();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}

















