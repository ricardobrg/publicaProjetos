package com.publica.grupo1.model.dao.collaborator;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.database.TestDBConnection;
import com.publica.grupo1.model.dao.HibernateDAO;
import com.publica.grupo1.model.entities.collaborator.Collaborator;

/**
 * Class that makes the basic actions of class Collaborator, like as : insert,
 * searching, updating and deleting.
 * 
 * This class uses only one session provided by {@link DBConnection} or, in test
 * cases, the {@link TestDBConnection} class, this classes close the session.
 * 
 * @author Diego Leon
 * 
 * 
 */
public class CollaboratorDAO implements HibernateDAO<Collaborator> {

	private static CollaboratorDAO instance; // Instantiating the class itself to implement the Singleton pattern
	private Session session;

	/**
	 * Method responsible for instantiating the CollaboratorDAO class.
	 * 
	 * @param session the database session.
	 * 
	 * @author Pablo Mafessoli <mafessolip@gmail.com>
	 * 
	 * @return instance
	 */
	public static CollaboratorDAO getInstance(Session session) {
		if (instance == null)
			instance = new CollaboratorDAO(session);
		return instance;
	}

	private CollaboratorDAO(Session session) {
		this.session = session;
	}

	@Override
	public Collaborator selectById(int id) {
		Collaborator selectedCollaborator = session.get(Collaborator.class, id);
		return selectedCollaborator;
	}

	public Collaborator selectByCPF(String cpf) {
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Collaborator> criteria = cb.createQuery(Collaborator.class);

		Root<Collaborator> root = criteria.from(Collaborator.class);
		criteria.where(cb.like(root.get("cpf"), cpf));

		return session.createQuery(criteria).getResultList().get(0);
	}

	@Override
	public int insert(Collaborator objectToInsert) {
		objectToInsert.setRawPasswordHash(objectToInsert.getPassword());
		int id = (int) session.save(objectToInsert);
		return id;
	}

	@Override
	public void delete(Collaborator objectToDelete) {
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		session.delete(objectToDelete);
		session.getTransaction().commit();
	}

	@Override
	public void update(Collaborator objectToUpdate) {
		if (!session.getTransaction().isActive())
			session.beginTransaction();
		session.update(objectToUpdate);
		session.getTransaction().commit();
	}

	@Override
	public List<Collaborator> getAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Collaborator> criteria = builder.createQuery(Collaborator.class);
		criteria.from(Collaborator.class);
		List<Collaborator> selectedCollaborators = session.createQuery(criteria).getResultList();
		return selectedCollaborators;
	}

	public static void setInstance(CollaboratorDAO instance) {
		CollaboratorDAO.instance = instance;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
