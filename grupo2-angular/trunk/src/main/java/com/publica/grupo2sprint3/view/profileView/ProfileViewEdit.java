package com.publica.grupo2sprint3.view.profileView;

import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;

/**
 * View to edit the password of the logged in user.
 * 
 * @version 1.0.0
 * 
 * @author Diego Borba
 * @author Caio Shimada
 *
 */
public class ProfileViewEdit extends ProfileView{

	private static ProfileViewEdit instance;
	
	private ProfileViewEdit(Collaborator collab) {
		super(collab);
	}
	
	public static ProfileViewEdit getInstance(Collaborator collab) {
		if (instance == null) {
			instance = new ProfileViewEdit(collab);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		instance = null;
	}
	
	/**
	 * Sets a string to tell the user to type the new password.
	 * 
	 * @author Diego Borba
	 * @author Caio Shimada
	 * 
	 * @return the string with the input message
	 */
	@Override
	public String getOutput() {
		return "Digite a Nova Senha: ";
	}

	/**
	 * Reads the new password and calls the com.publica.grupo2sprint3.controller to update it.
	 * 
	 * @author Diego Borba
	 * @author Caio Shimada
	 */
	@Override
	protected void readInput() {
		String newPassword = Main.scan.nextLine();
		controller.update(collab.getCpf(), newPassword);
		controller.goToInitial();
	}

}
