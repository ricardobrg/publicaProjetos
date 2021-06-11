package model.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import model.entities.payroll.DiscountSalary;
import model.entities.payroll.Payroll;
import model.entities.person.Collaborator;

/**
 * Class for accessing the database using Hibernate.<br>
 * 
 * It implements methods for inserting, updating, deleting, finding by id or cnpj and selecting all
 * Payrolls.
 * 
 * @version 1.6.0
 * 
 * @author Giovanni Buzzi
 * @author Caio Shimada
 *
 */
public class PayrollHibernate extends HibernateDataAccess {

  /**
   * Inserts a new Payroll in the database.
   * 
   * @author Giovanni Buzzi
   * 
   * @param payroll the Payroll object to be added
   * @return the id of the inserted object. Returns -1 if the operation failed
   */
  @Override
  public int insert(Object object) {
    Payroll payroll = (Payroll) object;

    try {
      Session session = sessionFactory.openSession();
      session.beginTransaction();

      for (DiscountSalary item : payroll.getDiscounts()) {
        session.save(item);
      }

      int id = (int) session.save(payroll);

      session.getTransaction().commit();
      session.close();

      return id;

    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  /**
   * Updates an existing Payroll in the database
   * 
   * @author Giovanni Buzzi
   * 
   * @param payroll the Payroll object to be updated
   * @return the id of the inserted object. Returns -1 if the operation failed
   */
  @Override
  public int update(Object object) {
    Payroll payroll = (Payroll) object;

    try {
      Session session = sessionFactory.openSession();
      session.beginTransaction();

      session.update(payroll.getCollaborator());
      session.update(payroll);

      session.getTransaction().commit();
      session.close();

      return payroll.getId();

    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  /**
   * Deletes a Payroll in the database.
   * 
   * @author Giovanni Buzzi
   * 
   * @param id the id of the Payroll object to be deleted
   * @return the id of the inserted object. Returns -1 if the operation failed
   */
  @Override
  public int delete(int id) {
    try {
      Session session = sessionFactory.openSession();
      session.beginTransaction();

      Payroll payroll = session.load(Payroll.class, id);
      session.delete(payroll);

      session.getTransaction().commit();
      session.close();

      return payroll.getId();

    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  /**
   * Finds a Payroll in the database given a field and value.
   * 
   * @author Giovanni Buzzi
   * 
   * @param field the string of field to be used
   * @param value the string of value of the field selected to use on search
   * @return the Payroll object found
   */
  @Override
  public Object find(String field, String value) {
    try {
      Session session = sessionFactory.openSession();
      session.beginTransaction();

      CriteriaBuilder builder = session.getCriteriaBuilder();
      CriteriaQuery<Payroll> criteria = builder.createQuery(Payroll.class);
      Root<Payroll> root = criteria.from(Payroll.class);
      criteria.select(root).where(builder.equal(root.get(field), value));

      List<Payroll> data = session.createQuery(criteria).getResultList();

      session.getTransaction().commit();
      session.close();

      return (data.size() > 0) ? data.get(0) : null;

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Finds a Payroll in the database by Collaborator.
   * 
   * @author Giovanni Buzzi
   * 
   * @param collab the Collaborator object referenced in the payroll registry to be found
   * @return the Payroll object found
   */
  public ArrayList<Payroll> findByCollab(int id) {
    try {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      Collaborator collab =
          (Collaborator) new CollaboratorHibernate().find("id", String.valueOf(id));

      CriteriaBuilder builder = session.getCriteriaBuilder();
      CriteriaQuery<Payroll> criteria = builder.createQuery(Payroll.class);
      Root<Payroll> root = criteria.from(Payroll.class);
      criteria.select(root).where(builder.equal(root.get("collaborator"), collab));

      List<Payroll> data = session.createQuery(criteria).getResultList();

      session.getTransaction().commit();
      session.close();

      return (ArrayList<Payroll>) data;

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Retrieves every Payroll in the database.
   * 
   * @author Giovanni Buzzy
   * 
   * @return a List of Payrolls in the database
   */
  @SuppressWarnings("unchecked")
  @Override
  public <T> ArrayList<T> getAll() {
    try {
      Session session = sessionFactory.openSession();
      session.beginTransaction();

      CriteriaBuilder builder = session.getCriteriaBuilder();
      CriteriaQuery<Payroll> criteria = builder.createQuery(Payroll.class);
      criteria.from(Payroll.class);
      List<Payroll> data = session.createQuery(criteria).getResultList();
      session.close();
      
      return (ArrayList<T>) data;
      
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    
  }

}
