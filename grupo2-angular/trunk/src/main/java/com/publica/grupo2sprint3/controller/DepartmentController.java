package com.publica.grupo2sprint3.controller;

import java.util.ArrayList;

import com.publica.grupo2sprint3.model.dao.CollaboratorDAO;
import com.publica.grupo2sprint3.model.dao.DepartmentDAO;
import com.publica.grupo2sprint3.model.dao.factory.DepartmentFactory;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;
import com.publica.grupo2sprint3.model.util.Validators;
import com.publica.grupo2sprint3.view.departmentView.DepartmentViewCreate;
import com.publica.grupo2sprint3.view.departmentView.DepartmentViewEdit;
import com.publica.grupo2sprint3.view.departmentView.DepartmentViewList;
import com.publica.grupo2sprint3.view.departmentView.DepartmentViewMenu;

/**
 * This com.publica.grupo2sprint3.controller handles DepartmentView requests.
 * 
 * It implements the methods required for handling redirections and actions.
 * 
 * @version 1.0.0
 * 
 * @author Caio Shimada <xcaiosr@gmail.com>
 */
public class DepartmentController extends Controller {

	private static DepartmentController instance;

	private DepartmentController(Collaborator collab) {
		super(collab);
		this.dao = new DepartmentFactory();
	}


	public static DepartmentController getInstance(Collaborator collab) {
		if (instance == null) {
			instance = new DepartmentController(collab);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		destroyViews();
		instance = null;
	}

	private static void destroyViews() {
		DepartmentViewMenu.destroyInstance();
		DepartmentViewMenu.destroyInstance();
		DepartmentViewEdit.destroyInstance();
		DepartmentViewList.destroyInstance();
	}
	
	/**
	 * Returns user to the initial com.publica.grupo2sprint3.view page.
	 * 
	 * @author Caio Shimada
	 */
	@Override
	public void goToInitial() {
		destroyViews();
		DepartmentViewMenu.getInstance(collab).display();
	}

	/**
	 * Redirects user to the department creation com.publica.grupo2sprint3.view.
	 * 
	 * @author Caio Shimada
	 */
	@Override
	public void create() {
		DepartmentViewCreate.getInstance(collab).display();
	}

	/**
	 * Retrieves the Department to be edited by id and redirects user to the
	 * Department edit com.publica.grupo2sprint3.view.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param the id of the department to be edited
	 */
	@Override
	public void edit(int id) {
		Department department = (Department) find(id);
		DepartmentViewEdit.getInstance(collab, department).display();
	}

	/**
	 * Retrieves the Department to be edited by name and redirects user to the
	 * Department edit com.publica.grupo2sprint3.view.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param name the name of the department to be edited
	 */
	public void edit(String name) {
		//Department department = (Department) find(name);
		//DepartmentDAO.getInstance().updateByIdentifier(name, department);
		//DepartmentViewEdit.getInstance(collab, department).display();
	}

	/**
	 * Retrieves existing Departments and returns then to the List com.publica.grupo2sprint3.view.
	 * 
	 * @author Caio Shimada
	 */
	@Override
	public void list() {
		ArrayList<Department> departments = getDepartments();
		DepartmentViewList.getInstance(collab, departments).display();
	}

	/**
	 * Retrieves the Department to be shown by id and sends user to the display
	 * Department info com.publica.grupo2sprint3.view.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com
	 * 
	 * @param id the id of the department whose info will be shown
	 */
	@Override
	public void show(int id) {
		// TODO Auto-generated method stub

	}

	/**
	 * Finds Department by id.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * 
	 * @param id an int of the id of the department to be searched
	 * @return the Department found
	 */
	@Override
	public Object find(int id) {
		return (Department) dao.findById(id);
	}

	/**
	 * Finds Department by name.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * 
	 * @param id an int of the name of the department to be searched
	 * @return the Department found
	 */
	public Object find(String name) {
		return (Department) dao.findByIdentifier(name);
	}

	/**
	 * Stores a new Department.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param name the name of the department to be created
	 * @return <code>true</code> if Department was added; <code>false</code>
	 *         otherwise
	 */
	public boolean add(String name) {
		if (!isInputValid(name))
			return false;

		Department department = new Department(name);
		
		return dao.add(department);
	}
	
	public boolean add(Object obj) {
		return dao.add(obj);
	}

	/**
	 * Updates the information of an existing Department identified by id.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param id
	 * @param name
	 * @param cpf
	 * @return <code>true</code> if Department was updated; <code>false</code>
	 *         otherwise
	 */
	public boolean update(int id, String name, String cpf) {
		//Collaborator collab = null;

		if (cpf == "null")
			if (!isInputValid(name))
				return false;
			else {
				if (!isInputValid(name, cpf)) {
					return false;
				}
				collab = (Collaborator) CollaboratorDAO.getInstance().findByIdentifier(cpf);
			}

		Department newDepartment = new Department(name);
		
		return dao.updateById(id, newDepartment);
	}
	
	public boolean update(int id, Object obj) {
		return dao.updateById(id, obj);
	}

	/**
	 * Updates the information of an existing Department identified by name.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param id
	 * @param name
	 * @param cpf
	 * @return <code>true</code> if Department was updated; <code>false</code>
	 *         otherwise
	 */
	public boolean update(String name, String newName, String cpf) {
		//Collaborator collab = null;

		if (cpf == "") {
			if (!isInputValid(newName))
				return false;
		} else {
			if (!isInputValid(newName, cpf)) {
				return false;
			}
			collab = (Collaborator) CollaboratorDAO.getInstance().findByIdentifier(cpf);
		}

		Department newDepartment = new Department(newName);
		
		return dao.updateByIdentifier(name, newDepartment);
	}
	
	public boolean update (String identifier, Department department) {
		return DepartmentDAO.getInstance().updateByIdentifier(identifier, department);
	}

	/**
	 * Removes a Department identified by id.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param id the id of the department to be deleted
	 * @return <code>true</code> if Department was deleted; <code>false</code>
	 *         otherwise
	 */
	@Override
	public boolean remove(int id) {
		return dao.removeById(id);
	}

	/**
	 * Removes a Department identified by name.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param name the name of the department to be deleted
	 * @return <code>true</code> if Department was deleted; <code>false</code>
	 *         otherwise
	 */
	public boolean remove(String name) {
		return dao.removeByIdentifier(name);
	}

	/**
	 * Validates user input, verifying if the Department already exists.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param name the name of the department
	 * @return <code>true</code> if input is valid; <code>false</code> otherwise
	 */
	public boolean isInputValid(String name) {
		if (dao.findByIdentifier(name) != null)
			return false;
		return true;
	}

	/**
	 * Validates user input, verifying if the Department already exists, if the cpf
	 * of the manager is valid, and if he has minimum AccessLevel to be a manager.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param name the name of the department
	 * @param cpf the cpf of the manager
	 * @return <code>true</code> if input is valid; <code>false</code> otherwise
	 */
	public boolean isInputValid(String name, String cpf) {
		if (dao.findByIdentifier(name) != null)
			return false;

		if (!Validators.isCpfValid(cpf))
			return false;

		Collaborator collab = (Collaborator) CollaboratorDAO.getInstance().findByIdentifier(cpf);

		if (collab == null)
			return false;

		if (collab.getRole().getAccessLevel().id > AccessLevel.ADVANCED.id)
			return false;

		return true;
	}

	/**
	 * Retrieves all Departments.
	 * 
	 * @author Caio Shimada
	 * 
	 * @return an arraylist of the departments
	 */
	public ArrayList<Department> getDepartments() {
		return dao.getAll();
	}

}
