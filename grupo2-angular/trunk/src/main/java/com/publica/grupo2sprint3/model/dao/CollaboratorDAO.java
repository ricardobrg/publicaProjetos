package com.publica.grupo2sprint3.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.publica.grupo2sprint3.model.dao.factory.HibernateConnectorFactory;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;

/**
 * Class for Collaborator Services that allows adding, searching and editing
 * collaborators in an Arraylist.
 * 
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
 * @author Jess� Amaro
 * @author Diego Borba <diegoborba25@gmail.com>
 * 
 *         Version: 1.3.0
 */
public class CollaboratorDAO implements DAO {

	Session session;
	ContactDAO daoContact;
	Collaborator collaborator;
	CollaboratorDAO daoCollab;
	private static CollaboratorDAO instance;

	CollaboratorDAO() {
	}

	public static CollaboratorDAO getInstance() {
		if (instance == null)
			instance = new CollaboratorDAO();
		return instance;
	}

	/**
	 * A new session is created for adding a new Collaborator on database.
	 * 
	 * @param newCollaborator
	 * @return
	 */
	@Override
	public boolean add(Object newCollab) {
		session = HibernateConnectorFactory.getSessionFactory().openSession();
			try {
				session.beginTransaction();
				session.save(newCollab);
				session.getTransaction().commit();
				session.close();
				return true;			
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * A new session is created for searching a Collaborator on database by your id.
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Collaborator findById(int id) {
		session = HibernateConnectorFactory.getSessionFactory().openSession();
		Collaborator collab = session.find(Collaborator.class, id);
		session.close();
		return collab;

	}

	/**
	 * Method for searching Collaborator. (via ArrayList)
	 * 
	 * Searches using the cpf
	 * 
	 * @param searchCollaborator : String
	 * 
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
	 * 
	 * @return item/null
	 */
	@Override
	public Object findByIdentifier(String identifier) {
		session = HibernateConnectorFactory.getSessionFactory().openSession();
		session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Collaborator> criteria = builder.createQuery(Collaborator.class);
		Root<Collaborator> rootCollaborator = criteria.from(Collaborator.class);
		criteria.select(rootCollaborator).where(builder.like(rootCollaborator.<String>get("cpf"), identifier));

		List<Collaborator> collaborators = session.createQuery(criteria).getResultList();
		session.close();
		if (!collaborators.isEmpty())
			return collaborators.get(0);
		return null;
	}

	/**
	 * A new session is created for updating a Collaborator on database by your id.
	 * 
	 * @param id
	 * @return
	 */
	public boolean updateById(int id, Object newCollab) {
		session = HibernateConnectorFactory.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Collaborator collab = (Collaborator) newCollab;
			collab.setId(id);

//			if(((Collaborator) newCollab).getContact() != null)
//				ContactDAO.getInstance().add(((Collaborator) newCollab).getContact());
//
//			if(((Collaborator) newCollab).getAddress() != null)
//				AddressDAO.getInstance().add(((Collaborator) newCollab).getAddress());
//
			if(((Collaborator) newCollab).getRole() != null)
				RoleDAO.getInstance().add(((Collaborator) newCollab).getRole());
//
//			if(!((Collaborator) newCollab).getDiscounts().isEmpty()) {
//				for(Discount discount : ((Collaborator) newCollab).getDiscounts()){
//					DiscountDAO.getInstance().add(discount);
//				}
//			}
			session.saveOrUpdate(collab);
			session.getTransaction().commit();
			session.close();
			return true;			
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * A new session is created for updating a Collaborator on database by your
	 * identifier (cpf).
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public boolean updateByIdentifier(String identifier, Object collab) {
		Collaborator coll = (Collaborator) findByIdentifier(identifier);
		session.save(coll);
		session.remove(coll);
		session.save(collab);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	/**
	 * A new session is created for removing a Collaborator on database by your id.
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public boolean removeById(int id) {
		Collaborator collab = (Collaborator) findById(id);
		try {
			session = HibernateConnectorFactory.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(collab);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * A new session is created for searching a Collaborator on database by your
	 * identifier (cpf).
	 * 
	 * @param id
	 * @return
	 */
	public boolean removeByIdentifier(String identifier) {
		Collaborator coll = (Collaborator) findByIdentifier(identifier);
		session.remove(coll);
		session.getTransaction().commit();
		session.close();
		return true;

	}


	/**
	 * A new session is created for searching all Collaborators on database.
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Collaborator> getAll() {
		session = HibernateConnectorFactory.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Collaborator> criteria = builder.createQuery(Collaborator.class);
		criteria.from(Collaborator.class);
		ArrayList<Collaborator> collaborators = (ArrayList<Collaborator>) session.createQuery(criteria).getResultList();
		session.close();
		return collaborators;
	}
}
