package com.publica.grupo2sprint3.controller;

import java.util.ArrayList;

import com.publica.grupo2sprint3.model.dao.factory.CollaboratorFactory;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;
import com.publica.grupo2sprint3.view.vacationView.VacationViewEdit;
import com.publica.grupo2sprint3.view.vacationView.VacationViewList;
import com.publica.grupo2sprint3.view.vacationView.VacationViewMenu;

/***
 * Class responsible for the management of data from the vacation views
 * 
 * @version 2.0.0
 * 
 * @author Diego Borba <diegoborba25@gmail.com>
 * @author Caio Shimada
 */
public class VacationController extends Controller {

	private static VacationController instance;

	private VacationController(Collaborator collab) {
		super(collab);
		this.dao = new CollaboratorFactory();
	}

	public static VacationController getInstance(Collaborator collab) {
		if (instance == null) {
			instance = new VacationController(collab);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		destroyViews();
		instance = null;
	}

	private static void destroyViews() {
		VacationViewMenu.destroyInstance();
		VacationViewEdit.destroyInstance();
		VacationViewList.destroyInstance();
	}
	
	/**
	 * Method to return to the initial display from home com.publica.grupo2sprint3.view
	 * 
	 * @author Diego Borba <diegoborba25@gmail.com>
	 * @author Caio Shimada
	 */
	public void goToInitial() {
		destroyViews();
		VacationViewMenu.getInstance(collab).display();
	}

	/**
	 * Redirects user to the edit page of the Collaborator selected by id.
	 * 
	 * @author Caio SHimada
	 */
	@Override
	public void edit(int id) {
		Collaborator collabFound = (Collaborator) find(id);
		VacationViewEdit.getInstance(collab, collabFound).display();
	}

	/**
	 * Recovers and redirects user to com.publica.grupo2sprint3.view of Collaborator Listing.
	 * 
	 * @author Caio Shimada
	 */
	@Override
	public void list() {
		ArrayList<Collaborator> collabsFound = getCollabs();
		VacationViewList.getInstance(collab, collabsFound).display();
	}

	/**
	 * Finds a Collaborator by id.
	 * 
	 * @author Caio Shimada
	 */
	@Override
	public Object find(int id) {
		return dao.findById(id);
	}

	/***
	 * Method that modifies the information of the vacation control. First find out
	 * what are the final values, and then change.
	 * 
	 * @author Diego Borba <diegoborba25@gmail.com>
	 * @author Caio Shimada
	 * 
	 * @param cpf  the cpf of the user whose vacation will be updated
	 * @param data the input data of the user to change the info
	 * @return <code>true</code> if it was successfully updated; <code>false</code>
	 *         otherwise
	 */
	public boolean update(String cpf, String[] data) {

		Collaborator updateCollab = (Collaborator) dao.findByIdentifier(cpf);

		if ((data[0]).equals("s"))
			updateCollab.setCanVacation(true);
		else
			updateCollab.setCanVacation(false);

		updateCollab.setInVacation(((data[1]).equals("s")) ? true : false);

		updateCollab.setVacationSize(data[2]);

		return dao.updateByIdentifier(cpf, updateCollab);

	}

	/**
	 * Method that separates collaborators from the scope from the user's access
	 * level.
	 * 
	 * @author Diego Borba <diegoborba25@gmail.com>
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * 
	 * @return Collaborators found (ArrayList<Collaborator>)
	 */
	public ArrayList<Collaborator> getCollabs() {
		ArrayList<Collaborator> collabs = new ArrayList<Collaborator>();

		if (collab.getRole().getAccessLevel() == AccessLevel.TOTAL) {
			collabs = dao.getAll();
		}

		if (collab.getRole().getAccessLevel() == AccessLevel.ADVANCED) {

			Department department = collab.getRole().getDepartment();
			for (Object coll : dao.getAll()) {
				if (((Collaborator) coll).getRole().getDepartment() == department) {
					collabs.add((Collaborator) coll);
				}
			}
		}

		return collabs;
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean remove(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
