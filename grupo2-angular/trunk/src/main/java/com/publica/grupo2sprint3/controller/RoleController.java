package com.publica.grupo2sprint3.controller;

import java.util.ArrayList;

import com.publica.grupo2sprint3.model.dao.RoleDAO;
import com.publica.grupo2sprint3.model.dao.factory.RoleFactory;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;
import com.publica.grupo2sprint3.model.person.physicalperson.Role;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;
import com.publica.grupo2sprint3.model.util.Validators;
import com.publica.grupo2sprint3.view.roleView.RoleViewCreate;
import com.publica.grupo2sprint3.view.roleView.RoleViewDelete;
import com.publica.grupo2sprint3.view.roleView.RoleViewEdit;
import com.publica.grupo2sprint3.view.roleView.RoleViewList;
import com.publica.grupo2sprint3.view.roleView.RoleViewMenu;

/**
 * This com.publica.grupo2sprint3.controller controls the user's actions. <br>
 * 
 * This com.publica.grupo2sprint3.controller is only available to the Acces Level Total. Total Acces Level
 * users can create a new role, as well as list every role. <br>
 * Returns the views the user will have when accessing the role screen.
 * 
 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
 * @version 1.0.0
 */
public class RoleController extends Controller {

	private static RoleController instance;

	public RoleController(Collaborator collab) {
		super(collab);
		this.dao = new RoleFactory();

	}

	public static RoleController getInstance(Collaborator collab) {
		if (instance == null) {
			instance = new RoleController(collab);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		destroyViews();
		instance = null;
	}
	
	private static void destroyViews() {
		RoleViewMenu.destroyInstance();
		RoleViewCreate.destroyInstance();
		RoleViewEdit.destroyInstance();
		RoleViewList.destroyInstance();
		RoleViewDelete.destroyInstance();
	}

	/**
	 * return to com.publica.grupo2sprint3.view initial.<br>
	 * 
	 * Destroys this instance as it will no longer be used! When it is called again, it creates a new
	 * 
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 */
	@Override
	public void goToInitial() {
		destroyViews();
		RoleViewMenu.getInstance(collab).display();
	}

	/**
	 * Sends as com.publica.grupo2sprint3.view information.<br>
	 * 
	 * View instance with listing functions.
	 * 
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 */
	@Override
	public void create() {
		RoleViewCreate.getInstance(collab).display();
	}
	
	
	public boolean add(Object obj) {
		return dao.add(obj);
	}
	
	/**
	 * Sends as com.publica.grupo2sprint3.view information.<br>
	 * 
	 * View instance with listing functions.
	 * 
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 */
	@Override
	public void edit(int id) {
		
		ArrayList<Role> roles = getRoles();
		RoleViewEdit.getInstance(collab, roles).display();
	}

	/**
	 * Sends as com.publica.grupo2sprint3.view information.<br>
	 * 
	 * View instance with listing functions.
	 * 
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 */
	@Override
	public void list() {
		ArrayList<Role> roles = getRoles();
		RoleViewList.getInstance(collab, roles).display();
	}

	/**
	 * Show *
	 * 
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 */
	@Override
	public void show(int id) {
	}

	/**
	 * Finds the index of a given object
	 * 
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 */
	@Override
	public Role find(int id) {
		return (Role) dao.findById(id);
	}
	
	/**
	 * Receives the parameters passed by the com.publica.grupo2sprint3.view and treats this with the com.publica.grupo2sprint3.model.
	 * 
	 * @param int id
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 */
	@Override
	public boolean remove(int id) {
		return dao.removeById(id);
	}
	
	/**
	 * Sends as com.publica.grupo2sprint3.view information.<br>
	 * 
	 * View instance with listing functions.
	 * 
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 */
	public void delete() {
		ArrayList<Role> roles = getRoles();
		RoleViewDelete.getInstance(collab, roles).display();
	}
	
	/**
	 * Role validate inputs
	 * 
	 * @param name
	 * @param department
	 * @param salary
	 * @return boolean
	 * 
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * @author Augusto Kalahary <AuuKalaharyKW@gmail.com>
	 */
	public boolean validateRole(String name, Department department, Double salary) {
		if ((dao.findByIdentifier(name) == null 
				&& Validators.isCharactersValid(name) 
				&& Validators.isCharactersValid(department.getName()) 
				&& Validators.isSalaryValid(salary)))
			return true;
		return false;
	}
	
	/**
	 * Adds the new role to the role list
	 * 
	 * @param String     inRole - name of role
	 * @param Department department - name of department (checks if a department
	 *                   already exists)
	 * @param Double     salary - receives the salary registered for the role
	 *
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * @author Augusto Kalahary <AuuKalaharyKW@gmail.com>
	 */
	public boolean inputRole(String inputRole, Department department, Double salary) {
		if (!validateRole(inputRole, department, salary)) {
			return false;
		}

		Role role = new Role(inputRole, department, salary);
		dao.add(role);

		return true;
	}

	/**
	 * Edit a role to the role list
	 * 
	 * @param String     inRole - name of role
	 * @param Department department - name of department (checks if a department
	 *                   already exists)
	 * @param Double     salary - receives the salary registered for the role
	 *
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * @author Augusto Kalahary <AuuKalaharyKW@gmail.com>
	 */
	public boolean updateRole(int index, Role role) {
		return dao.updateById((index), role);
	}
	
	public boolean update(String identifier, Role role) {
		return RoleDAO.getInstance().updateByIdentifier(identifier, role);
	}
	
	/**
	 * Returns the number of registered roles. At the moment the actions are registered in an ArrayList.
	 * 
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 */
	public ArrayList<Role> getRoles() {
		ArrayList<Role> roles = new ArrayList<Role>();

		if (collab.getRole().getAccessLevel() == AccessLevel.TOTAL) {
			roles = dao.getAll();
		}

		if (collab.getRole().getAccessLevel() == AccessLevel.ADVANCED) {

			Department department = collab.getRole().getDepartment();
			for (Object role : dao.getAll()) {
				if (((Role) role).getDepartment() == department) {
					roles.add((Role) role);
				}
			}
		}
		return roles;
	}
}
