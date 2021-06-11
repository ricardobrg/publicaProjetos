package com.publica.grupo2sprint3.view.profileView;

import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;

/**
 * Main Menu of the Profile View.
 * 
 * @version 1.0.0
 * 
 * @author Caio Shimada
 *
 */
public class ProfileViewMenu extends ProfileView{

	private static ProfileViewMenu instance;
	
	private ProfileViewMenu(Collaborator collab) {
		super(collab);
	}
	
	public static ProfileViewMenu getInstance(Collaborator collab) {
		if (instance == null) {
			instance = new ProfileViewMenu(collab);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		instance = null;
	}

	/**
	 * Displays the menu options available.
	 * 
	 * @author Diego Borba
	 * @author Caio Shimada
	 * 
	 * @return the string ready to be printed
	 */
	@Override
	public String getOutput() {
		String output = "--------- Meu Perfil ---------\n" 
				+ "0. Sair \n" 
				+ "1. Ver meu perfil \n"
				+ "2. Editar senha \n";
		return output;
	}

	/**
	 * Method to read the input with the option of the user.
	 * 
	 * @author Diego Borba
	 * @author Caio Shimada
	 */
	@Override
	protected void readInput() {
		int input = Main.scan.nextInt();
		Main.scan.nextLine();
		
		switch (input) {
		case 1:
			controller.show(0);
			break;
		case 2:
			controller.edit(0);
			break;
		case 0:
			controller.goToHome();
			break;
		default:
			System.out.println("Opção Inválida");
			break;
		}	
	}
}
