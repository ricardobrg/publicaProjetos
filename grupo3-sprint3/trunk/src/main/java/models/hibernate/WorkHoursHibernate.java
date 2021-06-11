package models.hibernate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import models.entities.person.Collaborator;
import models.entities.workHours.WorkEntry;

/**
 * Connection class between java Work Entry model and the database. The
 * connection is made through Hibernate.
 *
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
 * 
 * @version 1.0.0
 */
public class WorkHoursHibernate extends HibernateDataAccess {

	CollaboratorHibernate collabHibernate;

	public WorkHoursHibernate() {
		collabHibernate = new CollaboratorHibernate();
	}
	
	@Override
	public void setTestDatabase() {
		sessionFactory = HibernateConector.getTestSessionFactory();
		collabHibernate.setTestDatabase();
	}

	/**
	 * Adds information about a new entry in the database
	 * 
	 * @param object the WorkEntry object to be inserted
	 * @return the id of the inserted object. Returns -1 if the operation failed
	 * 
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 */
	@Override
	public int insert(Object object) {
		WorkEntry workEntry = (WorkEntry) object;

		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();

			int id = (int) session.save(workEntry);

			session.getTransaction().commit();
			session.close();

			return id;

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	/**
	 * Finds work entries between two given dates
	 * 
	 * @param cpf   the cpf of the collaborator whose points belong to
	 * @param date1 LocalDateTime of the start date
	 * @param date2 LocalDateTime of the end date
	 * @return an ArrayList of LocalDateTime of the work entries found
	 * 
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 */
	public ArrayList<LocalDateTime> findBetweenDates(String cpf, LocalDateTime date1, LocalDateTime date2) {
		ArrayList<LocalDateTime> data1 = new ArrayList<LocalDateTime>();
		Collaborator collab = (Collaborator) collabHibernate.find("cpf", cpf);

		try {

			Session session = sessionFactory.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<WorkEntry> criteria = builder.createQuery(WorkEntry.class);
			Root<WorkEntry> root = criteria.from(WorkEntry.class);
			Predicate collabPredicate = builder.equal(root.get("collaborator"), collab);
			criteria.select(root)
					.where(builder.and((builder.between(root.get("clock"), date1, date2)), collabPredicate));
			List<WorkEntry> data = session.createQuery(criteria).getResultList();

			session.close();
			
			for (WorkEntry item : data) {
				data1.add(item.getClock());
			}
			
			return data1;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Returns a complete list of entries saved in the database
	 * 
	 * @return a List of Collaborators in the database
	 * 
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Jonathas Rocha de Souza <jonathasrochadesouza@gmail.com>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> ArrayList<T> getAll() {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<WorkEntry> criteria = builder.createQuery(WorkEntry.class);

			criteria.from(Collaborator.class);
			List<WorkEntry> data = session.createQuery(criteria).getResultList();

			session.getTransaction().commit();
			session.close();
			return (ArrayList<T>) data;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int update(Object object) {
		return -1;
	}

	@Override
	public int delete(int id) {
		return -1;
	}

	@Override
	public Object find(String field, String value) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<WorkEntry> criteria = builder.createQuery(WorkEntry.class);
			Root<WorkEntry> root = criteria.from(WorkEntry.class);
			criteria.select(root).where(builder.equal(root.get(field), value));
			List<WorkEntry> data = session.createQuery(criteria).getResultList();

			session.getTransaction().commit();
			session.close();

			return (data.size() > 0) ? data.get(0) : null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
