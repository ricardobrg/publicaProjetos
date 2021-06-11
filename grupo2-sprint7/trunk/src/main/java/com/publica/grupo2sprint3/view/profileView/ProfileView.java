package com.publica.grupo2sprint3.view.profileView;

import com.publica.grupo2sprint3.controller.ProfileController;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.view.View;

/**
 * This is the abstract class to every Profile com.publica.grupo2sprint3.view. It sets the Controller used.
 * 
 * @version 2.0.0
 * 
 * @author Diego Borba
 * @author Caio Shimada
 *
 */
public abstract class ProfileView extends View{
	
	protected ProfileController controller;

	public 	ProfileView(Collaborator collab) {
		super(collab);
		this.controller = ProfileController.getInstance(collab);
	}
	
}











