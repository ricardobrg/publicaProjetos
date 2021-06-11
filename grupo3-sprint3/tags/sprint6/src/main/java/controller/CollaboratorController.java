package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import model.dao.CollaboratorDAO;
import model.entities.person.Collaborator;

/**
 * Collaborator Controller to handle View Requests and Actions.
 * 
 * @version 1.0.0
 * 
 * @author Caio Shimada
 */
public class CollaboratorController {

  private CollaboratorDAO collaboratorDAO;

  public CollaboratorController() {
    collaboratorDAO = new CollaboratorDAO();
  }

  /**
   * Receives a Collaborator of a Collaborator and calls the DAO to store it in the database.
   * 
   * @author Caio Shimada
   * 
   * @param collab : the json a collaborator to be added
   */
  public int insert(Collaborator collab) {
    return collaboratorDAO.insert(collab);
  }

  /**
   * Retrieves the ArrayList of existing Collaborators and returns them in an ArrayList of
   * Collaborators of the Collaborator.
   * 
   * @author Caio Shimada
   * 
   * @return an arraylist of the collaborators found in a Json
   * @throws SQLException
   */
  public ArrayList<Collaborator> getAll() throws SQLException {
    return collaboratorDAO.getAll();
  }

  /**
   * Searches a Collaborator in the database by cpf and returns it in a JSON Object.
   * 
   * @author Caio Shimada
   * 
   * @param cpfJson the json with a cpf to be searched
   * @return the collaborator found in a json
   */
  public Collaborator findByCpf(String cpf) {
    return collaboratorDAO.findByCpf(cpf);
  }

  /**
   * Searches a Collaborator in the database by ID and returns it in a Collaborator.
   * 
   * @author Caio Shimada
   * 
   * @param id the id in a json of the collaborator to be deleted
   * @return the collaborator found in a json
   */
  public Collaborator findById(int id) {
    return collaboratorDAO.findById(id);
  }

  /**
   * Updates an existing Collaborator identifying it by Id
   * 
   * @author Caio Shimada
   * 
   * @param collab a Collaborator with the collaborator data
   */
  public int update(Collaborator collab) {
    return collaboratorDAO.update(collab);
  }

  /**
   * Deletes a Collaborator using its ID
   * 
   * @author Caio Shimada
   * 
   * @param id the id in a json of the collaborator to be deleted
   */
  public int delete(int id) {
    return collaboratorDAO.delete(id);
  }

}
