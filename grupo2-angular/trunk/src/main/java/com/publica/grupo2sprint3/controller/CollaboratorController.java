package com.publica.grupo2sprint3.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.publica.grupo2sprint3.model.dao.factory.CollaboratorFactory;
import com.publica.grupo2sprint3.model.dao.factory.RoleFactory;
import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;
import com.publica.grupo2sprint3.model.person.physicalperson.Role;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;
import com.publica.grupo2sprint3.model.util.Validators;
import com.publica.grupo2sprint3.view.collaboratorView.CollaboratorViewCreate;
import com.publica.grupo2sprint3.view.collaboratorView.CollaboratorViewEdit;
import com.publica.grupo2sprint3.view.collaboratorView.CollaboratorViewList;
import com.publica.grupo2sprint3.view.collaboratorView.CollaboratorViewMenu;
import com.publica.grupo2sprint3.view.collaboratorView.CollaboratorViewShow;

/**
 * This com.publica.grupo2sprint3.controller handles ControllerView requests
 * 
 * It implements the methods required for handling redirections and actions
 * 
 * @version 2.0.0
 * 
 * @author Caio Shimada <xcaiosr@gmail.com>
 */
public class CollaboratorController extends Controller {

	private static CollaboratorController instance;

	private CollaboratorController(Collaborator collab) {
		super(collab);
		this.dao = new CollaboratorFactory();
	}

	public static CollaboratorController getInstance(Collaborator collab) {
		if (instance == null) {
			instance = new CollaboratorController(collab);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		destroyViews();
		instance = null;
	}
	
	private static void destroyViews() {
		CollaboratorViewMenu.destroyInstance();
		CollaboratorViewCreate.destroyInstance();
		CollaboratorViewEdit.destroyInstance();
		CollaboratorViewList.destroyInstance();
		CollaboratorViewShow.destroyInstance();
	}

	/**
	 * Destroys previous instance of the com.publica.grupo2sprint3.view and returns user to initial page of
	 * the Collaborators View.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 */
	@Override
	public void goToInitial() {
		destroyViews();
		CollaboratorViewMenu.getInstance(collab).display();
	}

	/**
	 * Sends user to the Collaborator Creation Form.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 */
	@Override
	public void create() {
		CollaboratorViewCreate.getInstance(collab).display();
	}

	/**
	 * Retrieves the Collaborator to be edited by id and sends user to the edit
	 * collaborator page.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com
	 * 
	 * @param id an int of the id of the collaborator who will be edited
	 */
	@Override
	public void edit(int id) {
		Collaborator collabFound = find(id);
		CollaboratorViewEdit.getInstance(collab, collabFound).display();
	}

	/**
	 * Retrieves the Collaborator to be edited by cpf and sends user to the edit
	 * collaborator page.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com
	 * 
	 * @param cpf the string of the cpf of the collaborator who will be edited
	 */
	public void edit(String cpf) {
		Collaborator collabFound = find(cpf);
		CollaboratorViewEdit.getInstance(collab, collabFound).display();
	}
	
	/**
	 * Retrieves existing Collaborators and returns then to the List com.publica.grupo2sprint3.view.
	 * 
	 * @author Caio Shimada
	 */
	@Override
	public void list() {
		ArrayList<Collaborator> collabs = getCollabs();
		CollaboratorViewList.getInstance(collab, collabs).display();
	}

	/**
	 * Retrieves the Collaborator to be shown by id and sends user to the display
	 * collaborator info com.publica.grupo2sprint3.view.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com
	 * 
	 * @param id the id of the collaborator whose info will be shown
	 */
	@Override
	public void show(int id) {
		Collaborator collabFound = find(id);
		CollaboratorViewShow.getInstance(collab, collabFound).display();
	}

	/**
	 * Finds Collaborator by id.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * 
	 * @param id an int of the id of the collaborator to be searched
	 * @return the Collaborator found
	 */
	@Override
	public Collaborator find(int id) {
		return (Collaborator) dao.findById(id);
	}

	/**
	 * Finds Collaborator by cpf.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * 
	 * @param cpf an stringo fthe cpf of the collaborator to be searched
	 * @return the Collaborator found
	 */
	public Collaborator find(String cpf) {
		return (Collaborator) ((CollaboratorFactory) dao).findByIdentifier(cpf);
	}
	
	/**
	 * Stores the Collaborator and sets the Admission Date as the current date in
	 * the correct format
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * 
	 * @param name
	 * @param email
	 * @param cpf
	 * @param phone
	 * @param address
	 * @param cep
	 * @param pis
	 * @param salary   in double
	 * @param workload in int
	 * @return <code>true</code> if Collaborator was added; <code>false</code>
	 *         otherwise
	 */
	public boolean add(String name, Contact contact, Address address, String cpf, String pis,
			Role role, double salary, int workload) {

		if (!isInputValid(name, contact, address, cpf, pis, role, salary, workload))
			return false;

		String admissionDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Collaborator collab = new Collaborator(name, contact, address, cpf, pis, role, admissionDate, salary,
				workload);

		return dao.add(collab);
	}

	/**
	 * Validates input then Updates an user, identifying him by Id.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param id
	 * @param name
	 * @param email
	 * @param cpf
	 * @param phone
	 * @param address
	 * @param cep
	 * @param pis
	 * @param role
	 * @param admissionDate
	 * @param salary
	 * @param workload
	 * @return <code>true</code> if successful; <code>false</code> otherwise
	 */
	public boolean update(int id, String name, Contact contact, Address address, String cpf,
			String pis, Role role, String admissionDate, double salary, int workload) {

		if (!isInputValid(name, contact, address, cpf, pis, role, salary, workload))
			return false;

		Collaborator collab = new Collaborator(name, contact, address, cpf, pis, role, admissionDate, salary,
				workload);

		return dao.updateById(id, collab);
	}

	/**
	 * Validates input then Updates an user, identifying him by cpf.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param cpf
	 * @param name
	 * @param email
	 * @param newCpf
	 * @param phone
	 * @param address
	 * @param cep
	 * @param pis
	 * @param role
	 * @param admissionDate
	 * @param salary
	 * @param workload
	 * @return <code>true</code> if successful; <code>false</code> otherwise
	 */
	public boolean update(String cpf, String name, Contact contact, Address address,  String newCpf,
			String pis, Role role, String admissionDate, double salary, int workload) {

		if (!isInputValid(name, contact, address, newCpf, pis, role, salary, workload))
			return false;

		Collaborator collab = new Collaborator(name, contact, address, newCpf, pis, role, admissionDate,
				salary, workload);

		return dao.updateByIdentifier(cpf, collab);
	}
	
	/**
	 * Removes a Collaborator identifying him by id
	 * 
	 * @author Caio Shimada
	 * 
	 * @return <code>true</code> if Collaborator was deleted; <code>false</code>
	 *         otherwise
	 */
	@Override
	public boolean remove(int id) {
		return dao.removeById(id);
	}

	/**
	 * Removes a Collaborator identifying him by cpf
	 * 
	 * @author Caio Shimada
	 * 
	 * @return <code>true</code> if Collaborator was deleted; <code>false</code>
	 *         otherwise
	 */
	public boolean remove(String cpf) {
		return dao.removeByIdentifier(cpf);
	}
	
	/**
	 * Validates user input Validates cpf, cep, email, phone, pis, workload and
	 * salary. It also verifies it the CPF already exists
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * 
	 * @param name
	 * @param email
	 * @param cpf
	 * @param phone
	 * @param address
	 * @param cep
	 * @param pis
	 * @param role
	 * @param salary
	 * @param workload
	 * @return <code>true</code> if data is valid; <code>false</code> otherwise
	 */
	public boolean isInputValid(String name, Contact contact, Address address, String cpf,
			String pis, Role role, double salary, int workload) {

		if (!Validators.isCpfValid(cpf) || !Validators.isCepValid(address.getCep().toString()) || !Validators.isEmailValid(contact.getEmail())
				|| !Validators.isPhoneValid(contact.getPhone()) || !Validators.isPisValid(pis)
				|| !Validators.isWorkHoursValid(workload) || !Validators.isSalaryValid(salary))
			return false;

		if (((CollaboratorFactory) dao).findByIdentifier(cpf) != null)
			return false;

		return true;
	}


	/**
	 * Retrieves roles and returns them to the com.publica.grupo2sprint3.view
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * 
	 * @return an ArrayList of existing roles
	 */
	public ArrayList<Role> getRoles() {
		dao = new RoleFactory();
		
		ArrayList<Role> returnList = new ArrayList<Role>();
		returnList= dao.getAll();
		
		dao = new CollaboratorFactory(); 
		return returnList;
	}

	/**
	 * Verifies the user Access Level and retrieves the Collaborators list.
	 * 
	 * If the user only has Access Level Advanced, the users returned belong to the
	 * same Department as the requesting Collaborator.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * 
	 * @return the arraylist of Collaborators found
	 */
	public ArrayList<Collaborator> getCollabs() {
		ArrayList<Collaborator> collaborators = new ArrayList<Collaborator>();

		if (collab.getRole().getAccessLevel() == AccessLevel.TOTAL) {
			collaborators = dao.getAll();
		}

		if (collab.getRole().getAccessLevel() == AccessLevel.ADVANCED) {

			Department department = collab.getRole().getDepartment();
			for (Object coll : dao.getAll()) {
				if (((Collaborator) coll).getRole().getDepartment() == department) {
					collaborators.add((Collaborator) coll);
				}
			}

		}

		return collaborators;
	}

}
