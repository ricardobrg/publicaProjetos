package com.publica.grupo2sprint3.view.vacationView;

import com.publica.grupo2sprint3.controller.VacationController;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.view.View;

/**
 * Class responsible for inputs and outputs
 * from the vacation management com.publica.grupo2sprint3.view
 * 
 * @version: 2.0.0
 * 
 * @author Diego Borba <diegoborba25@gmail.com>
 * @author Caio Shimada
 *
 */
public abstract class VacationView extends View{

	protected VacationController controller;

	public VacationView(Collaborator collab) {
		super(collab);
		this.controller = VacationController.getInstance(collab);
	}

}

















