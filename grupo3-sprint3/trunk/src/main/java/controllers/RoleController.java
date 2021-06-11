package controllers;

import java.util.ArrayList;

import models.entities.security.Role;
import models.hibernate.RoleHibernate;

/***
 * RoleController<br>
 * 
 * Class used to manage the roles in the application controller.
 * 
 * This controller is responsible for calling the views of the roles menus as
 * well as for applying the methods to them. It works receiving and returning
 * JsonObjects.
 * 
 * @version 2.0.0
 * 
 * @author Caio Shimada
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 * 
 */
public class RoleController {

	private RoleHibernate roleDAO;

	public RoleController() {
		roleDAO = new RoleHibernate();
	}

	/**
	 * Receives a Role and calls the DAO to store it in the database.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param role the role object to be added
     * @return the id of the inserted object.
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
     * @return the id of the deleted object.
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
	 * @param id the id of the role to be retrieved
	 * @return the role found
	 * @throws Exception
	 */
	public Role find(int id) throws Exception {
		return (Role) roleDAO.find("id", String.valueOf(id));
	}

	/**
	 * Updates an existing Role identified by its ID
	 * 
	 * @author Caio Shimada
	 * 
	 * @param the role to be edited
     * @return the id of the updated object.
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
	 * @return the ArrayList of existing roles
	 * @throws Exception
	 */
	public ArrayList<Role> getAll() throws Exception {
		return roleDAO.getAll();
	}
}
