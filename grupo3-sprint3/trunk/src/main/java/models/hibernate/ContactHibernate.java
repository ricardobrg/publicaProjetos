package models.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import models.entities.person.Contact;

/**
 * 
 * Is implements simple methods to inserting, finding, updating
 * and deleting contacts in the database.
 * 
 * @version 1.0.0
 * 
 * @author Caio Shimada
 *
 */
public class ContactHibernate extends HibernateDataAccess{

	/**
	 * Inserts the given contact in the database.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param contact the Contact object to be inserted
	 * @return the id of the inserted object. Returns -1
	 * 		   if the operation failed
	 */
	@Override
	public int insert(Object object) {
		Contact contact = (Contact) object;
		
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();

			int id = (int) session.save(contact);
			
			session.getTransaction().commit();
			session.close();

			return id;

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	/**
	 * Updates the given Contact.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param contact the Contact object to be updated
	 * @return the id of the inserted object. Returns -1
	 * 		   if the operation failed
	 */
	@Override
	public int update(Object object) {
		Contact contact = (Contact) object;
		
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();

			session.update(contact);
			
			session.getTransaction().commit();
			session.close();

			return contact.getId();

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Deletes a contact given its id.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param id the id of the contact to be deleted
	 * @return the id of the inserted object. Returns -1
	 * 		   if the operation failed
	 */
	@Override
	public int delete(int id) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();

			Contact contact = session.find(Contact.class, id);
			session.delete(contact);

			session.getTransaction().commit();
			session.close();
			
			return contact.getId();

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * Finds a Contact given its email and telephone.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param email the string of the email of the Contact to be found
	 * @param telephone the string of the telephone of the Contact to be found
	 * @return the found Contact object
	 */
	public Contact find(String email, String telephone) {
		
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Contact> criteria = builder.createQuery(Contact.class);
			Root<Contact> root = criteria.from(Contact.class);
			Predicate emailPred = builder.equal(root.get("email"), email);
			Predicate telephonePred = builder.equal(root.get("telephone"), telephone);
			criteria.select(root).where(builder.and(emailPred, telephonePred));
			List<Contact> data = session.createQuery(criteria).getResultList();

			session.getTransaction().commit();
			session.close();

			return (data.size() > 0) ? data.get(0) : null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public <T> ArrayList<T> getAll() {
		return null;
	}
}
