package com.publica.grupo2sprint3.view.profileView;

import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;

/**
 * View that displays the data of the logged in user.
 * 
 * @version 1.0.0
 * 
 * @author Diego Borba
 * @author Caio Shimada
 *
 */
public class ProfileViewShow extends ProfileView{

	private static ProfileViewShow instance;
	
	private ProfileViewShow(Collaborator collab) {
		super(collab);
	}
	
	public static ProfileViewShow getInstance(Collaborator collab) {
		if (instance == null) {
			instance = new ProfileViewShow(collab);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		instance = null;
	}

	/**
	 * Sets the output with the data of the logged in user.
	 * 
	 * @author Diego Borba
	 * @author Caio Shimada
	 * 
	 * @return the string ready to be printed
	 */
	@Override
	public String getOutput() {
		String output = "Nome: " + collab.getName() + "\n"
				+ "Cargo: " + collab.getRole().getName() + "\n"
				+ "Carga Horária: " + collab.getWorkHours() + "h por dia \n"
				+ "CPF: " + collab.getCpf() + "\n"
				+ collab.getAddress().toString() + "\n"
				+ "Email: " + collab.getContact().getEmail() + "\n"
				+ "Pis: " + collab.getPis() + "\n";
		return output;
	}

	/**
	 * Reads an input to return the user to the initial page.
	 * 
	 * @author Diego Borba
	 * @author Caio Shimada
	 */
	@Override
	protected void readInput() {
		System.out.println("Pressione 'enter' para voltar");
		Main.scan.nextLine();
		Main.scan.nextLine();
		controller.goToInitial();
	}

}
