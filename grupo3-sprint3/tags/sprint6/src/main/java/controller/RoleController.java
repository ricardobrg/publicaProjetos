package controller;

import java.util.ArrayList;

import model.dao.RoleDAO;
import model.entities.security.Role;

/***
 * RoleController<br>
 * 
 * Class used to manage the roles in the application controller.
 * 
 * This controller is responsible for calling the views of the roles menus as
 * well as for applying the methods to them. It works receiving and returning
 * JsonObjects.
 * 
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 * 
 * @version 1.0.0
 */
public class RoleController {

	private RoleDAO roleDAO;

	public RoleController() {
		roleDAO = new RoleDAO();
	}

	/**
	 * Receives a Role and calls the DAO to store it in the database.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param role : the role to be added
	 * @return add from DAO
	 * @throws Exception
	 */
	public int add(Role role) throws Exception {
		return roleDAO.insert(role);
	}

	/**
	 * Deletes a Role using its ID
	 * 
	 * @author Caio Shimada
	 * 
	 * @param id the id of the role to be deleted
	 * @return delete from DAO
	 * 
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public int delete(int id) throws IllegalArgumentException, Exception {
		return roleDAO.delete(id);
	}

	/**
	 * Searches a Role in the database by ID and returns it.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param id the id of the role to be found
	 * @return find from DAO
	 * @throws Exception
	 */
	public Role find(int index) throws Exception {
		return roleDAO.find(index);
	}

	/**
	 * Updates an existing Role identified by its ID
	 * 
	 * @author Caio Shimada
	 * 
	 * @param the role to be edited
	 * @return update from DAO
	 * @throws Exception
	 */
	public int edit(Role role) throws Exception {
		return roleDAO.update(role);
	}

	/**
	 * Retrieves the ArrayList of existing Roles and returns them in an ArrayList of
	 * Roles.
	 * 
	 * @author Caio Shimada
	 * 
	 * @return getAll from DAO
	 * @throws Exception
	 */
	public ArrayList<Role> getAll() throws Exception {
		return roleDAO.getAll();
	}
}
