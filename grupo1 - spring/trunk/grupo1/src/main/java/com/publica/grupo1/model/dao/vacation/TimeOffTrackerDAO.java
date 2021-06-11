package com.publica.grupo1.model.dao.vacation;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.database.TestDBConnection;
import com.publica.grupo1.model.dao.HibernateDAO;
import com.publica.grupo1.model.entities.vacations.TimeOffTracker;
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
public class TimeOffTrackerDAO implements HibernateDAO<TimeOffTracker> {

	private static TimeOffTrackerDAO instance;
	private Session session;

	private TimeOffTrackerDAO() {
		session = DBConnection.getSession();
	}

	public static TimeOffTrackerDAO getInstance() {
		if (instance == null)
			instance = new TimeOffTrackerDAO();
		return instance;
	}

	@Override
	public TimeOffTracker selectById(int id) {
		TimeOffTracker timeOffTracker = session.get(TimeOffTracker.class, id);
		return timeOffTracker;

	}

	@Override
	public int insert(TimeOffTracker object) {
		int id = (int) session.save(object);
		return id;
	}

	@Override
	public void delete(TimeOffTracker object) {
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		session.delete(object);
		session.getTransaction().commit();
	}

	@Override
	public void update(TimeOffTracker object) {
		session.update(object);
	}

	@Override
	public List<TimeOffTracker> getAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<TimeOffTracker> criteria = builder.createQuery(TimeOffTracker.class);
		criteria.from(TimeOffTracker.class);
		List<TimeOffTracker> list = session.createQuery(criteria).getResultList();
		return list;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}















