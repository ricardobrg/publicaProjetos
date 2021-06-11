package com.publica.grupo2sprint3.view.pointView;

import com.publica.grupo2sprint3.controller.PointController;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.view.View;

/**
 * <br> Abstract class for the Point Views. <br> 
 * <br>
 * 
 * This class contains the Controller used in the points com.publica.grupo2sprint3.view.
 * 
 * @version 1.1.0
 * 
 * @author Jessé Amaro <jesse.amaro7@gmail.com>
 */
public abstract class PointView extends View {
	
	protected PointController controller;

	public PointView(Collaborator collab) {
		super(collab);
		this.controller = PointController.getInstance(collab);
	}
	
	public void redirectToPointMenu() {
		PointController.getInstance(collab).goToInitial();
	}
}
