package com.publica.grupo2sprint3.controller;

import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.util.ClearConsole;

/**
 * The com.publica.grupo2sprint3.controller for the Home View.
 * 
 * Its main purpose is to redirect the user from the home page to the selected page.
 * 
 * @version 2.0.0
 * 
 * @author Diego Borba
 * @author Caio Shimada <xcaiosr@gmail.com>
 * @author Jessé Amaro
 * @author Jonathas Rocha
 */
public class HomeController {

	public static void goToProfile(Collaborator collab) {
		ProfileController.getInstance(collab).goToInitial();
	}

	public static void goToCollaborators(Collaborator collab) {
		CollaboratorController.getInstance(collab).goToInitial();
	}

	public static void goToRole(Collaborator collab) {
		RoleController.getInstance(collab).goToInitial();
	}
	
	public static void goToDepartments(Collaborator collab) {
		DepartmentController.getInstance(collab).goToInitial();
	}

	public static void goToPoint(Collaborator collab) {
		PointController.getInstance(collab).goToInitial();
	}

	public static void goToVacation(Collaborator collab) {
		VacationController.getInstance(collab).goToInitial();
	}
	
	public static void goToProviders(Collaborator collab) {
		ProviderController.getInstance(collab).goToInitial();
	}
	
	public static void goToPayroll(Collaborator collab) {
		PayrollController.getInstance(collab).goToInitial();
	}

	public static void clearConsole() {
		ClearConsole.clearConsole();
	}
}
