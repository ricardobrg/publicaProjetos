package com.publica.grupo1.model.dao.internalevents;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.database.TestDBConnection;
import com.publica.grupo1.model.dao.HibernateDAO;
import com.publica.grupo1.model.entities.internalevents.InternalEvents;

/**
 * Class that makes basic actions of class InternalEvents, such as: inserting,
 * searching, updating and deleting.
 * 
 * This class uses only one session provided by {@link DBConnection} or, in test
 * cases, the {@link TestDBConnection} class, this classes close the session.
 * 
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @version 1.0.0
 */
public class InternalEventsDAO implements HibernateDAO<InternalEvents> {
	private static InternalEventsDAO instance;
	private Session session;

	private InternalEventsDAO(Session session) {
		this.session = session;
	}

	/**
	 * Method responsible for instantiating the InternalEventsDAO class.
	 * 
	 * @param session the database session.
	 * 
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * 
	 * @return instance
	 */
	public static InternalEventsDAO getInstance(Session session) {
		if (instance == null)
			instance = new InternalEventsDAO(session);
		return instance;
	}

	@Override
	public InternalEvents selectById(int id) {
		InternalEvents intEvent = session.get(InternalEvents.class, id);
		return intEvent;
	}

	@Override
	public int insert(InternalEvents object) {
		return (int) session.save(object);
	}

	@Override
	public void delete(InternalEvents object) {
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		session.delete(object);
		session.getTransaction().commit();
	}

	@Override
	public void update(InternalEvents object) {
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		session.update(object);
		session.getTransaction().commit();
	}

	@Override
	public List<InternalEvents> getAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<InternalEvents> criteria = builder.createQuery(InternalEvents.class);
		criteria.from(InternalEvents.class);
		List<InternalEvents> list = session.createQuery(criteria).getResultList();
		return list;
	}

	public static void setInstance(InternalEventsDAO instance) {
		InternalEventsDAO.instance = instance;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
