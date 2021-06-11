package com.publica.grupo2sprint3.view.departmentView;

import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;

/**
 * The Main Menu of the Department View.
 * 
 * It displays the available options which are to list the departments and to create a new one.
 * This com.publica.grupo2sprint3.view is only available to TOTAL Access Users, so there's no access level validations.
 * 
 * @version 1.0.0
 * 
 * @author Caio Shimada
 */
public class DepartmentViewMenu extends DepartmentView {

	private static DepartmentViewMenu instance;

	private DepartmentViewMenu(Collaborator collab) {
		super(collab);
	}

	public static DepartmentViewMenu getInstance(Collaborator collab) {
		if (instance == null) {
			instance = new DepartmentViewMenu(collab);
		}
		return instance;
	}

	public static void destroyInstance() {
		instance = null;
	}

	/**
	 * The menu with the available options
	 * 
	 * @author Caio Shimada
	 */
	@Override
	public String getOutput() {
		String output = "";
		
		if (access.id >= AccessLevel.ADVANCED.id) {
			output += "Você não deveria estar aqui. Redirecionando\n";
			controller.goToHome();
		}
		
		output += "\n--------- Departamentos ---------\n" 
					+ "0. Voltar\n" 
					+ "1. Listar Departamentos\n"
					+ "2. Cadastrar Departamento\n";
		return output;
	}

	/**
	 * Reads the user option and redirects him to the selected com.publica.grupo2sprint3.view.
	 * 
	 * @author Caio Shimada
	 */
	@Override
	protected void readInput() {
		int input = Main.scan.nextInt();
		switch (input) {
		case 1:
			controller.list();
			break;
		case 2:
			controller.create();
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
