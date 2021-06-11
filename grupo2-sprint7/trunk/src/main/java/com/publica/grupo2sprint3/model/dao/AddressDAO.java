package com.publica.grupo2sprint3.model.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

import com.publica.grupo2sprint3.model.dao.factory.HibernateConnectorFactory;
import com.publica.grupo2sprint3.model.person.Address;

/*
 * Class for Adress Services that allows adding, list, editing and
 * remove the address.
 * 
 * @author Ana <carolsantos2002@gmail.com>
 * @author Jess� Amaro <jesse.amaro7@gmail.com>
 */

public class AddressDAO implements DAO {
	Session session = HibernateConnectorFactory.getSessionFactory().openSession();
	private static AddressDAO instance;

	public static AddressDAO getInstance() {
		if (instance == null)
			instance = new AddressDAO();
		return instance;
	}

	/**
	 * Add Address method
	 * 
	 * This method will add a new address to a specific collaborator.
	 *
	 * @author Ana <carolsanto2002@gmail.com>
	 * @author Jess� Amaro <jesse.amaro@gmail.com>
	 */
	@Override
	public boolean add(Object newAddress) {
		Session session = HibernateConnectorFactory.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(newAddress);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	/**
	 * Find Address method
	 * 
	 * This method will find a address by id.
	 *
	 * @author Ana <carolsanto2002@gmail.com>
	 * @author Jess� Amaro <jesse.amaro@gmail.com>
	 */
	@Override
	public Object findById(int id) {
		Session session = HibernateConnectorFactory.getSessionFactory().openSession(); // AQUI //
		Address address = session.find(Address.class, id);
		return address;
	}

	/**
	 * FindbyIndentifier Address method
	 * 
	 * This method will find a address by identifier.
	 *
	 * @author Ana <carolsanto2002@gmail.com>
	 * @author Jess� Amaro <jesse.amaro@gmail.com>
	 */
	@Override
	public Object findByIdentifier(String cep) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Address> criteria = builder.createQuery(Address.class);
		Root<Address> rootAddress = criteria.from(Address.class);
		criteria.select(rootAddress).where(builder.like(rootAddress.<String>get("cep"), cep));

		List<Address> addressess = session.createQuery(criteria).getResultList();
		return addressess.get(0);

	}

	/**
	 * Update Address method
	 * 
	 * This method will update the address by id.
	 *
	 * @author Ana <carolsanto2002@gmail.com>
	 * @author Jess� Amaro <jesse.amaro@gmail.com>
	 */
	@Override
	public boolean updateById(int id, Object newAddress) {
		session = HibernateConnectorFactory.getSessionFactory().openSession();
		((Address) newAddress).setId(id);
		session.beginTransaction();
		session.saveOrUpdate(newAddress);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	/**
	 * Update Address method
	 * 
	 * This method will update the address by identifier.
	 *
	 * @author Ana <carolsanto2002@gmail.com>
	 * @author Jess� Amaro <jesse.amaro@gmail.com>
	 */
	@Override
	public boolean updateByIdentifier(String identifier, Object Address) {
		session = HibernateConnectorFactory.getSession();
		session.beginTransaction();
		Address add = (Address) findByIdentifier(identifier);
		session.save(add);
		session.remove(add);
		session.save(Address);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	/**
	 * Remove Address method
	 * 
	 * This method will remove the address by id.
	 *
	 * @author Ana <carolsanto2002@gmail.com>
	 * @author Jess� Amaro <jesse.amaro@gmail.com>
	 */
	@Override
	public boolean removeById(int id) {
		Address address = (Address) findById(id);
		try {
			session = HibernateConnectorFactory.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(address);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Remove Address method
	 * 
	 * This method will remove the address by identifier.
	 *
	 * @author Ana <carolsanto2002@gmail.com>
	 * @author Jess� Amaro <jesse.amaro@gmail.com>
	 */
	@Override
	public boolean removeByIdentifier(String identifier) {
		session = HibernateConnectorFactory.getSession();
		Address address = (Address) findByIdentifier(identifier);
		try {
			session.beginTransaction();
			session.remove(address);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Getall Address method
	 * 
	 * This method will get All the address by ArrayList.
	 *
	 * @author Ana <carolsanto2002@gmail.com>
	 * @author Jess� Amaro <jesse.amaro@gmail.com>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Address> getAll() {
		session = HibernateConnectorFactory.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Address> criteria = builder.createQuery(Address.class);
		criteria.from(Address.class);
		ArrayList<Address> address = (ArrayList<Address>) session.createQuery(criteria).getResultList();
		return address;
	}
}
