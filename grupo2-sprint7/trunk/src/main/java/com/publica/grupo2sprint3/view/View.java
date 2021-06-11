package com.publica.grupo2sprint3.view;

import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;

/**
 * Abstract com.publica.grupo2sprint3.view.
 * 
 * It contains the logged in Collaborator as well as its AccessLevel for easy access.
 * The display method is the default used in most Views to display content and read input.
 * 
 * @version 1.0.0
 * 
 * @author Caio Shimada
 */
public abstract class View {

	protected Collaborator collab;
	protected AccessLevel access;
	
	public View(Collaborator collab) {
		this.collab = collab;
		try {
			this.access = collab.getRole().getAccessLevel();
		}catch (Exception e) {
			this.access = null;
		}
		
	}
	
	/**
	 * Default display method used in most Views.
	 * 
	 * @author Caio Shimada
	 */
	public void display() {
		String output = getOutput();
		System.out.println(output);
		readInput();
	}
	
	public abstract String getOutput();
	protected abstract void readInput();

}
