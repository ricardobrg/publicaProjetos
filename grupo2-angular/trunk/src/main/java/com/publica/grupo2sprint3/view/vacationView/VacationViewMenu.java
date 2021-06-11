package com.publica.grupo2sprint3.view.vacationView;

import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;

/**
 * Main Menu for the Vacation Control com.publica.grupo2sprint3.view.
 * 
 * @version 1.0.0
 * 
 * @author Diego Borba
 * @author Caio Shimada
 *
 */
public class VacationViewMenu extends VacationView {

	private static VacationViewMenu instance;

	private VacationViewMenu(Collaborator collab) {
		super(collab);
	}

	public static VacationViewMenu getInstance(Collaborator collab) {
		if (instance == null) {
			instance = new VacationViewMenu(collab);
		}
		return instance;
	}

	public static void destroyInstance() {
		instance = null;
	}

	/***
	 * Generate the initial display, put it into a String var, and return it.
	 * 
	 * @author Diego Borba <diegoborba25@gmail.com>
	 * @author Caio Shimada
	 * 
	 * @return Initial Display(String)
	 */
	@Override
	public String getOutput() {
		String output = "\n--------- Gerenciador de Férias ---------\n";

		if (access.id >= AccessLevel.BASIC.id) {
			output += "Você não deveria estar aqui. Redirecionando\n";
			controller.goToHome();
		}

		if (access.id <= AccessLevel.ADVANCED.id) {
			output += "0. Voltar\n" + "1. Lista de Férias \n";
		}

		return output;
	}

	/***
	 * Method with and switch to define the next step of the com.publica.grupo2sprint3.view
	 * 
	 * @author Diego Borba <diegoborba25@gmail.com>
	 * @author Caio Shimada
	 */
	@Override
	protected void readInput() {
		int input = Main.scan.nextInt();
		switch (input) {
		case 0:
			controller.goToHome();
			break;
		case 1:
			controller.list();
			break;
		default:
			System.out.println("Opção Inválida");
			controller.goToInitial();
			break;
		}
	}

}
