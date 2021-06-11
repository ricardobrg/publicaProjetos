package com.publica.grupo2sprint3.view.departmentView;

import com.publica.grupo2sprint3.controller.DepartmentController;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.view.View;

/**
 * Abstract class for the Department Views.
 * 
 * It has the Controller for the Department.
 * 
 * @author Caio Shimada
 *
 */
public abstract class DepartmentView extends View{
	
	protected DepartmentController controller;

	public DepartmentView(Collaborator collab) {
		super(collab);
		this.controller = DepartmentController.getInstance(collab);
	}

}
