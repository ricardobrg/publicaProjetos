package com.publica.grupo1.model.dao.permissions;



import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.database.TestDBConnection;
import com.publica.grupo1.model.dao.HibernateDAO;
import com.publica.grupo1.model.entities.permissions.role.Department;

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
public class DepartmentDAO implements HibernateDAO<Department> {

	private static DepartmentDAO instance; // Instantiating the class itself to implement the Singleton pattern
	private Session session;

	private DepartmentDAO(Session session) {
		this.session = session;
	}

	/**
	 * Method responsible for instantiating the DepartamentDAO class. Author Pablo
	 * Mafessoli <mafessolip@gmail.com>
	 * 
	 * @return instance
	 */
	public static DepartmentDAO getInstance(Session session) {
		if (instance == null)
			instance = new DepartmentDAO(session);
		return instance;
	}

	@Override
	public Department selectById(int id) {
		Department dep = session.get(Department.class, id);
		return dep;
	}

	@Override
	public int insert(Department object) {
		int id = (int) session.save(object);
		return id;
	}

	@Override
	public void delete(Department object) {
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		session.delete(object);
		session.getTransaction().commit();
	}

	@Override
	public void update(Department object) {
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		session.update(object);
		session.getTransaction().commit();
	}

	@Override
	public List<Department> getAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Department> criteria = builder.createQuery(Department.class);
		criteria.from(Department.class);
		List<Department> list = session.createQuery(criteria).getResultList();

		return list;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
