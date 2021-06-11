package com.publica.grupo2sprint3.view.roleView;

import com.publica.grupo2sprint3.controller.RoleController;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.view.View;

/**
 * This com.publica.grupo2sprint3.view has methods to show the main page of the Job and Salary window, 
 * listing the jobs and creating a new one. <br>
 * 
 * This View is only available to the Acces Level Total. 
 * Total Acces Level users can create a new role, as well as list every role. <br>
 * Returns the views the user will have when accessing the role screen.
 * 
 * @version 1.1.0
 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
 * @author Jessé Amaro <jesse.amaro7@gmail.com>
 * @author Caio Shimada <xcaiosr@gmail.com>
 */
public abstract class RoleView extends View {

	protected RoleController roleController;
	
	public RoleView(Collaborator collab) {
		super(collab);
		this.roleController = RoleController.getInstance(collab);
	}
}