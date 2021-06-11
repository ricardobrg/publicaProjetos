package com.publica.grupo2sprint3.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.publica.grupo2sprint3.model.dao.factory.HibernateConnectorFactory;
import com.publica.grupo2sprint3.model.person.physicalperson.Event;


public class EventDAO implements DAO {
	Session session = HibernateConnectorFactory.getSessionFactory().openSession();
	private static EventDAO instance;

	EventDAO() {}

	public static EventDAO getInstance() {
		if (instance == null)
			instance = new EventDAO();
		return instance;
	}
	
	@Override
	public boolean add(Object newEvent) {
		try {
			Session session = HibernateConnectorFactory.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(newEvent);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public Object findById(int id) {
		Event event = session.find(Event.class, id);
		return event;
	}

	@Override
	public Object findByIdentifier(String identifier) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Event> criteria = builder.createQuery(Event.class);
		Root<Event> rootEvent = criteria.from(Event.class);
		criteria.select(rootEvent).where(builder.like(rootEvent.<String>get("name"), identifier));

		List<Event> events = session.createQuery(criteria).getResultList();
		if (events.size() != 0)
			return events.get(0);

		return null;
	}

	@Override
	public boolean updateByIdentifier(String identifier, Object event) {
		Session session = HibernateConnectorFactory.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(event);
		session.update(event);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	@Override
	public boolean updateById(int id, Object event) {
		Session session = HibernateConnectorFactory.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(event);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	@Override
	public boolean removeById(int id) {
		session = HibernateConnectorFactory.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.remove(session.find(Event.class, id));
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean removeByIdentifier(String identifier) {
		session = HibernateConnectorFactory.getSessionFactory().openSession();
		Event event = (Event) findByIdentifier(identifier);
		try {
			session.beginTransaction();
			session.remove(event);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Event> getAll() {
		Session session = HibernateConnectorFactory.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Event> criteria = builder.createQuery(Event.class);
		criteria.from(Event.class);
		ArrayList<Event> events = (ArrayList<Event>) session.createQuery(criteria).getResultList();
		return events;
	}

}
