package models.hibernate;

import java.util.ArrayList;

import org.hibernate.SessionFactory;

public abstract class HibernateDataAccess {
	
	protected SessionFactory sessionFactory;
	
	public HibernateDataAccess() {
		this.sessionFactory = HibernateConector.getSessionFactory();
	}
	
	public void setTestDatabase() {
		this.sessionFactory = HibernateConector.getTestSessionFactory();
	}
	
	public abstract int insert(Object object);
	public abstract int update(Object object);
	public abstract int delete(int id);
	public abstract Object find(String field, String value);
	public abstract <T> ArrayList<T> getAll();

}
