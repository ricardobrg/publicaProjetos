package com.publica.grupo2sprint3.view.collaboratorView;

import com.publica.grupo2sprint3.controller.CollaboratorController;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.view.View;

/**
 * Abstract class for the Collaborator Pages. 
 * 
 * It contains the Controller used in every collaborator com.publica.grupo2sprint3.view.
 * 
 * @version 1.0.0
 * 
 * @author Caio Shimada
 */
public abstract class CollaboratorView extends View{

	protected CollaboratorController controller;
	
	public CollaboratorView(Collaborator collab) {
		super(collab);
		this.controller = CollaboratorController.getInstance(collab);
	}
	
}
