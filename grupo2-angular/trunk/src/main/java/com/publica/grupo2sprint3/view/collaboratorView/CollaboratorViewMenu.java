
package com.publica.grupo2sprint3.view.collaboratorView;

import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;

/**
 * This com.publica.grupo2sprint3.view has methods for showing the main page of the Collaborator's window.
 * 
 * This View is only available to Access Levels Advanced, Total and Admin.<br>
 * Advanced Access Level users can only list the collaborators in their
 * department.<br>
 * Total and Admin Access Levels users can create a new collaborator, as well as
 * list every collaborator.
 * 
 * @author Caio Shimada <xcaiosr@gmail.com>
 * @author Diego Borba <diegoborba25@gmail.com>
 * 
 * @version 2.0.0
 */
public class CollaboratorViewMenu extends CollaboratorView {

	private static CollaboratorViewMenu instance;

	private CollaboratorViewMenu(Collaborator collab) {
		super(collab);
	}

	public static CollaboratorViewMenu getInstance(Collaborator collab) {
		if (instance == null) {
			instance = new CollaboratorViewMenu(collab);
		}
		return instance;
	}

	public static void destroyInstance() {
		instance = null;
	}

	/**
	 * It sets the output with the options to be printed on the com.publica.grupo2sprint3.view.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * 
	 * @return the string set up ready to be printed
	 */
	public String getOutput() {
		String output = "\n--------- Colaboradores ---------\n";

		if (access == AccessLevel.BASIC) {
			output += "Você não deveria estar aqui. Redirecionando\n";
		}

		if (access.id <= AccessLevel.ADVANCED.id) {
			output += "0. Voltar\n" + "1. Lista de Colaboradores\n";
		}

		if (access == AccessLevel.TOTAL || access == AccessLevel.ADMIN) {
			output += "2. Cadastrar Novo Colaborador\n";
		}

		return output;
	}

	/**
	 * Reads the option selected by the user and calls the correct method in the
	 * Controller.
	 * 
	 * It verifies if the user has access to the option selected.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 */
	protected void readInput() {

		int input = Main.scan.nextInt();
		switch (input) {
		case 0:
			controller.goToHome();
			break;
		case 1:
			if (access.id <= AccessLevel.ADVANCED.id)
				controller.list();
			else
				System.out.println("Ação Não Permitida");
			break;
		case 2:
			if (access == AccessLevel.TOTAL || access == AccessLevel.ADMIN)
				controller.create();
			else
				System.out.println("Ação Não Permitida");
			break;
		default:
			System.out.println("Opção Inválida");
			break;
		}

	}

}
