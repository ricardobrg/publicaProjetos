package com.publica.grupo2sprint3.controller;

import com.publica.grupo2sprint3.model.auth.Login;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.view.HomeView;
import com.publica.grupo2sprint3.view.LoginView;

/**
 * Class responsible for managing handling of data assigned to the login com.publica.grupo2sprint3.view
 * 
 * @version 1.0.0
 * @author Diego Borba <diegoborba25@gmail.com>
 */
public class LoginController {

	/**
	 * Method responsible for calling the com.publica.grupo2sprint3.model, and authenticate the login
	 * information
	 * 
	 * @param user
	 * @param password
	 * @return loged in/not loged(valid/invalid)
	 * 
	 * @author Diego Borba <diegoborba25@gmail.com>
	 */
	public static boolean login(String user, String password) {
		return Login.login(user, password);
	}

	/***
	 * Method that instantiates logOut within the com.publica.grupo2sprint3.view login
	 * 
	 * @author Diego Borba <diegoborba25@gmail.com>
	 * @author Caio Shimada
	 */
	public static void logout() {
		destroyControllers();
		LoginView.getInstance().display();
	}

	private static void destroyControllers() {
		CollaboratorController.destroyInstance();
		DepartmentController  .destroyInstance();
		PayrollController     .destroyInstance();
		PointController       .destroyInstance();
		ProfileController     .destroyInstance();
		ProviderController    .destroyInstance();
		RoleController		  .destroyInstance();
		VacationController	  .destroyInstance();
	}

	/***
	 * Method responsible for instantiating the employee and the home com.publica.grupo2sprint3.view class
	 * 
	 * @author Diego Borba <diegoborba25@gmail.com>
	 */
	public static void goToHome(Collaborator collab) {
		HomeView.destroyInstance();
		HomeView.getInstance(collab).display();
	}

	public static Collaborator getCollab() {
		return Login.getCollab();
	}
}
