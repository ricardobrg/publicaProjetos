package com.publica.grupo2sprint3.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.publica.grupo2sprint3.model.dao.factory.HibernateConnectorFactory;
import com.publica.grupo2sprint3.model.person.physicalperson.Role;

/**
 * This class implmements methods for managing registers in database.
 * 
 * 
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 * @author Pablo Mafessoli <mafessolip@gmail.com>
 * @author Caio Shimada <xcaiosr@gmail.com>
 * @author Diego Borba <diegoborba25@gmail.com>
 * @author Jonathas Rocha <jonathasrochadesouza@gmailcom>
 * @author Augusto Kalahary <AuuKalaharyKW@gmail.com>
 * @version 1.4.0
 */
public class RoleDAO implements DAO {
	private static RoleDAO instance;
	private Session session = HibernateConnectorFactory.getSessionFactory().openSession();


	RoleDAO() {}

	public static RoleDAO getInstance() {
		if (instance == null) 
			instance = new RoleDAO();
		return instance;
	}
	
	/**
	 * Method for adding a new Role on database
	 * 
	 * @param addedRole Role: Role that will be added on the database
	 * 
	 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
	 * @author Pablo Mafessoli <mafessolip@gmail.com>
	 * @author Diego Borba <diegoborba25@gmail.com>
	 * @author Augusto Kalahary <AuuKalaharyKW@gmail.com>
	 */
	@Override
	public boolean add(Object newRole) {	
		try {
			Session session = HibernateConnectorFactory.getSessionFactory().openSession();
			if(((Role) newRole).getDepartment() != null)
				DepartmentDAO.getInstance().add(((Role) newRole).getDepartment());
			session.beginTransaction();
			session.save(newRole);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/***
	 * Recives one id, and search for
	 * this id in database
	 * 
	 * @param id
	 * @return Service Provider(id)
	 * @author Diego Borba <diegoborba25@gmail.com>
	 */
	@Override
	public Object findById(int id) {
		Session session = HibernateConnectorFactory.getSessionFactory().openSession();
		Role role = session.find(Role.class, id);
		return role;
		
	}
	
	/***
	 * Recives one identifier,
	 * and search for this
	 * identifier in database
	 * 
	 * @param identifier
	 * @return Service Provider(identifier)
	 * @author Diego Borba <diegoborba25@gmail.com>
	 */
	@Override
	public Object findByIdentifier(String roleName) {
		session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Role> criteria = builder.createQuery(Role.class);
		Root<Role> rootRole = criteria.from(Role.class);
		criteria.select(rootRole).where(builder.like(rootRole.<String>get("nameRole"), roleName));
		
		List<Role> roles = session.createQuery(criteria).getResultList();
		return roles.get(0);
	}
	
	/**
	 * Method for update Role on database
	 * 
	 * @param updateRole Role: Role that will be added replacing the old Role on the database
	 * 
	 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
	 * @author Pablo Mafessoli <mafessolip@gmail.com>
	 * @author Augusto Kalahary <AuuKalaharyKW@gmail.com>
	 */
	@Override
	public boolean updateById(int id, Object role) {
		Session session = HibernateConnectorFactory.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Role newRole = (Role) role;
			if((newRole).getDepartment() != null) 
				DepartmentDAO.getInstance().add((newRole).getDepartment());
			
			newRole.setId(id);
			session.saveOrUpdate(newRole);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/***
	 * Recives one identifier, and search for
	 * this identifier in database, and update 
	 * the object to the newObject recived
	 * in parameters
	 * 
	 * @param identifier
	 * @param newProvider
	 * @return true/false(updated/not updated)
	 * @author Diego Borba <diegoborba25@gmail.com>
	 */
	@Override
	public boolean updateByIdentifier(String identifier, Object newRole) {
		session = HibernateConnectorFactory.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(newRole);
		session.update(newRole);
		session.getTransaction().commit();
		session.close();
		return true;
	}
	
	/**
	 * Remove the role wich have
	 * the passed index
	 * 
	 * @param index
	 * 
	 * @author Jonathas Rocha <jonathasrochadesouza@gmailcom>
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * @author Augusto Kalahary <AuuKalaharyKW@gmail.com>
	 */
	@Override
	public boolean removeById(int id) {
		session = HibernateConnectorFactory.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.remove(session.find(Role.class, id));
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Remove the role wich have
	 * the passed identifier
	 * 
	 * @param identifier
	 * 
	 * @author Jonathas Rocha <jonathasrochadesouza@gmailcom>
	 * @author Caio Shimada
	 * @author Augusto Kalahary <AuuKalaharyKW@gmail.com>
	 */
	@Override
	public boolean removeByIdentifier(String identifier) {
		session = HibernateConnectorFactory.getSession();
		Role role = (Role) findByIdentifier(identifier);
		role.setDepartment(null);
		session.remove(role);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	/**
	 * Retrieve all roles
	 * 
	 * @return an ArrayList of the existing roles
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * @author Augusto Kalahary <AuuKalaharyKW@gmail.com>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Role> getAll() {
		session = HibernateConnectorFactory.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Role> criteria = builder.createQuery(Role.class);
		criteria.from(Role.class);
		ArrayList<Role> roles = (ArrayList<Role>) session.createQuery(criteria).getResultList();
		session.close();
		return roles;
	}
}
