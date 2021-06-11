package model.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import model.entities.person.Endereco;

/**
 * 
 * Is implements simple methods to inserting, finding, updating
 * and deleting addresses in the database.
 * 
 * @version 1.0.0
 * 
 * @author Caio Shimada
 *
 */
public class AddressHibernate extends HibernateDataAccess{

	/**
	 * Inserts the given address in the database.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param address the Address object to be inserted
	 * @return the id of the inserted object.Returns -1
	 * 		   if the operation failed
	 */
	@Override
	public int insert(Object object) {
		Endereco address = (Endereco) object;
		
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
		
			int id = (int) session.save(address);
	
			session.getTransaction().commit();
			session.close();
			
			return id;
			
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Updates the given Address.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param address the Endereco object to be updated
	 * @return the id of the updated object.Returns -1
	 * 		   if the operation failed
	 */
	@Override
	public int update(Object object) {
		Endereco address = (Endereco) object;
		
		try { 
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			session.update(address);
			
			session.getTransaction().commit();
			session.close();
			
			return address.getId();
			
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Deletes an address given its id.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param id the id of the address to be deleted
	 * @return the id of the deleted object. Returns -1
	 * 		   if the operation failed
	 */
	@Override
	public int delete(int id) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();

			Endereco addr = session.find(Endereco.class, id);
			session.delete(addr);
			
			session.getTransaction().commit();
			session.close();
			return addr.getId();
			
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Finds an Address given its cep and complement.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param cep the string of the cep of the address to be found
	 * @param compl the string of the complement of the address to be found
	 * @return the found Endereco object
	 */
	@Override
	public Endereco find(String cep, String compl) {
		
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
		    CriteriaQuery<Endereco> criteria = builder.createQuery(Endereco.class);
		    Root<Endereco> root = criteria.from(Endereco.class);
			Predicate cepPred = builder.equal(root.get("cep"), cep);
			Predicate complementPred = builder.equal(root.get("complemento"), compl);
			criteria.select(root).where(builder.and(cepPred, complementPred));
		    List<Endereco> data = session.createQuery(criteria).getResultList();
			
			session.getTransaction().commit();
			session.close();
			
			return (data.size() > 0) ? data.get(0) : null;
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public <T> ArrayList<T> getAll() {
		return null;
	}

}
