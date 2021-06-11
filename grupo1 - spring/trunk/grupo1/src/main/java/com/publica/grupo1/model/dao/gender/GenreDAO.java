package com.publica.grupo1.model.dao.gender;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.database.TestDBConnection;
import com.publica.grupo1.model.dao.HibernateDAO;
import com.publica.grupo1.model.entities.gender.Genre;

/**
 * Class that makes the basic actions of class Collaborator, like as : insert,
 * searching, updating and deleting.
 * 
 * This class uses only one session provided by {@link DBConnection} or, in test
 * cases, the {@link TestDBConnection} class, this classes close the session.
 * 
 * @author Pablo
 * 
 * 
 */
public class GenreDAO implements HibernateDAO<Genre> {
	private static GenreDAO instance;
	private Session session;

	private GenreDAO(Session session) {
		this.session = session;
	}

	public static GenreDAO getInstance(Session session) {
		if (instance == null)
			instance = new GenreDAO(session);
		return instance;
	}

	@Override
	public Genre selectById(int id) {
		Genre genre = session.get(Genre.class, id);
		return genre;
	}

	@Override
	public int insert(Genre object) {
		return (int) session.save(object);
	}

	@Override
	public void delete(Genre object) {
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		session.delete(object);
		session.getTransaction().commit();
	}

	@Override
	public void update(Genre object) {
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		session.update(object);
		session.getTransaction().commit();
	}

	@Override
	public List<Genre> getAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Genre> criteria = builder.createQuery(Genre.class);
		criteria.from(Genre.class);
		List<Genre> list = session.createQuery(criteria).getResultList();
		return list;
	}

	public static void setInstance(GenreDAO instance) {
		GenreDAO.instance = instance;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
