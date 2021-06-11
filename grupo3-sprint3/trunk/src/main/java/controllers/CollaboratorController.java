package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import models.entities.person.Collaborator;
import models.hibernate.CollaboratorHibernate;

/**
 * Collaborator Controller to handle View Requests and Actions.
 * 
 * @version 2.0.0
 * 
 * @author Caio Shimada
 */
public class CollaboratorController {

	private CollaboratorHibernate collaboratorDAO;

	public CollaboratorController() {
		collaboratorDAO = new CollaboratorHibernate();
	}

	/**
	 * Receives a Collaborator of a Collaborator and calls the DAO to store it in
	 * the database.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param collab a collaborator to be added
	 * @return the id of the inserted object. Returns -1 if the operation failed
	 */
	public int insert(Collaborator collab) {
		return collaboratorDAO.insert(collab);
	}

	/**
	 * Retrieves the ArrayList of existing Collaborators and returns them in an
	 * ArrayList of Collaborators of the Collaborator.
	 * 
	 * @author Caio Shimada
	 * 
	 * @return an arraylist of the collaborators found
	 * @throws SQLException
	 */
	public ArrayList<Collaborator> getAll() throws SQLException {
		return collaboratorDAO.getAll();
	}

	/**
	 * Searches a Collaborator in the database by cpf and returns it.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param cpfJson the json with a cpf to be searched
	 * @return the collaborator found
	 */
	public Collaborator findByCpf(String cpf) {
		return (Collaborator) collaboratorDAO.find("cpf", cpf);
	}

	/**
	 * Searches a Collaborator in the database by ID and returns it in a
	 * Collaborator.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param id the id in a json of the collaborator to be deleted
	 * @return the collaborator found
	 */
	public Collaborator findById(int id) {
		return (Collaborator) collaboratorDAO.find("id", String.valueOf(id));
	}

	/**
	 * Updates an existing Collaborator identifying it by Id
	 * 
	 * @author Caio Shimada
	 * 
	 * @param collab a Collaborator with the collaborator data
	 * @return the id of the update object. Returns -1 if the operation failed
	 */
	public int update(Collaborator collab) {
		return collaboratorDAO.update(collab);
	}

	/**
	 * Deletes a Collaborator using its ID
	 * 
	 * @author Caio Shimada
	 * 
	 * @param id the id of the collaborator to be deleted
	 * @return the id of the delete object. Returns -1 if the operation failed
	 */
	public int delete(int id) {
		return collaboratorDAO.delete(id);
	}

}
