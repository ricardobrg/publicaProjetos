package com.publica.grupo2sprint3.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.publica.grupo2sprint3.model.dao.factory.HibernateConnectorFactory;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;

/*
 * Class for Department Services that allows adding, list, editing and
 * remove the collaborator's department.
 * 
 * @author Augusto Kalahary
 * @author Jess� Amaro
 */

public class DepartmentDAO implements DAO {

	Session session = HibernateConnectorFactory.getSessionFactory().openSession();

	public static ArrayList<Department> departments = new ArrayList<Department>();
	private static DepartmentDAO instance;

	private DepartmentDAO() {}

	public static DepartmentDAO getInstance() {
		if (instance == null)
			instance = new DepartmentDAO();
		return instance;
	}

	/**
	 * Register a new department if its name is valid and it doesn't already exists
	 * 
	 * @author Augusto Kalahary
	 * @author Jess� Amaro
	 */
	@Override
	public boolean add(Object newDepartment) {
		
		try {
			Session session = HibernateConnectorFactory.getSessionFactory().openSession();
			if( this.findById(((Department) newDepartment).getId()) != null)
				return true;			
			if(((Department) newDepartment).getManager() != null)
				CollaboratorDAO.getInstance().add(((Department) newDepartment).getManager());
			session.beginTransaction();
			session.save(newDepartment);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/***
	 * Get the index and return the department wich correspond with it
	 * 
	 * @author Diego Borba <diegoborba25@gmail.com>
	 * @author Jess� Amaro <jesse.amaro@gmail.com>
	 */
	@Override
	public Object findById(int id) {
		session = HibernateConnectorFactory.getSessionFactory().openSession();
		Department department = session.find(Department.class, id);
		session.close();
		return department;
	}

	/**
	 * Remove a department by ID
	 * 
	 * @author Augusto Kalahary
	 * @author Jess� Amaro
	 */

	/**
	 * Search department from the name
	 * 
	 * It will be useful to search and verify if a department already exist.
	 *
	 * @author Augusto Kalahary
	 * @author Jess� Amaro <jesse.amaro@gmail.com>
	 *
	 * @param name data to be searched
	 */
	@Override
	public Department findByIdentifier(String identifier) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Department> criteria = builder.createQuery(Department.class);
		Root<Department> rootDepartment = criteria.from(Department.class);
		criteria.select(rootDepartment).where(builder.like(rootDepartment.<String>get("name"), identifier));

		List<Department> departments = session.createQuery(criteria).getResultList();
		if (departments.size() != 0)
			return departments.get(0);

		return null;
	}

	@Override
	public boolean updateById(int id, Object department) {
		Session session = HibernateConnectorFactory.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(department);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	@Override
	public boolean updateByIdentifier(String identifier, Object department) {
		Session session = HibernateConnectorFactory.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(department);
		session.update(department);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	@Override
	public boolean removeById(int id) {
		session = HibernateConnectorFactory.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.remove(session.find(Department.class, id));
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean removeByIdentifier(String identifier) {
		session = HibernateConnectorFactory.getSessionFactory().openSession();
		Department depart = (Department) findByIdentifier(identifier);
		try {
			session.beginTransaction();
			session.remove(depart);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Consult all departments
	 * 
	 * @author Augusto Kalahary
	 * 
	 * @return department's list
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Department> getAll() {
		Session session = HibernateConnectorFactory.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Department> criteria = builder.createQuery(Department.class);
		criteria.from(Department.class);
		ArrayList<Department> departments = (ArrayList<Department>) session.createQuery(criteria).getResultList();
		return departments;
	}

}
