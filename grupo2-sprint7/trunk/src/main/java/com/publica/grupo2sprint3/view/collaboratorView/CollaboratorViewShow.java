package com.publica.grupo2sprint3.view.collaboratorView;

import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;

/**
 * Collaborator Data Visualization View with Singleton.
 * 
 * It displays the data of the collabFound received by the Controller and displays its data.
 * 
 * @version 1.0.0
 * 
 * @author Caio Shimada
 *
 */
public class CollaboratorViewShow extends CollaboratorView{

	private static CollaboratorViewShow instance;
	private Collaborator collabFound;
	
	private CollaboratorViewShow(Collaborator collab, Collaborator collabFound) {
		super(collab);
		this.collabFound = collabFound;
	}
	
	public static CollaboratorViewShow getInstance(Collaborator collab, Collaborator collabFound) {
		if (instance == null) {
			instance = new CollaboratorViewShow(collab, collabFound);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		instance = null;
	}

	/**
	 * It sets the data of the Collaborator to be printed.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * 
	 * @return the set up string ready to be printed
	 */
	@Override
	public String getOutput() {
		String output = "\n--------- Perfil de " + collabFound.getName() + " ---------\n"
				+ "Nome: " + collabFound.getName()
				+ "\n" + "Cargo: " + collabFound.getRole().getName()
				+ "\n" + "Carga Horária: " + collabFound.getWorkHours()
				+ "h por dia \n" + "CPF: " + collabFound.getCpf() + "\n"
				+ collabFound.getAddress().toString() + "\n"
				+ "Email: " + collabFound.getContact().getEmail() + "\n"
				+ "Pis: " + collabFound.getPis() + "\n";

		return output;
	}

	/**
	 * Reads an input and sends the user to the initial page of the Collaborator View.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 */
	@Override
	protected void readInput() {
		System.out.println("Pressione 'enter' para voltar");
		Main.scan.nextLine();
		Main.scan.nextLine();
		controller.goToInitial();
	}
	
}
