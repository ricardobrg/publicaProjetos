package com.publica.grupo2sprint3.view.roleView;

import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;

/**
 * This View Class shows the options related to management the registered roles. 
 * 
 * @version 1.0.0
 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
 * @author Jessé Amaro <jesse.amaro7@gmail.com>
 * @author Caio Shimada <xcaiosr@gmail.com>
 */
public class RoleViewMenu extends RoleView {

	private static RoleViewMenu instance;

	public RoleViewMenu(Collaborator collab) {
		super(collab);
	}

	public static RoleViewMenu getInstance(Collaborator collab) {
		if (instance == null) {
			instance = new RoleViewMenu(collab);
		}
		return instance;
	}

	public static void destroyInstance() {
		instance = null;
	}

	/**
	 * Displays options about the role for the user. The user can select an option
	 * by pressing the specified number. <br>
	 * 
	 * Option 1 - lists the roles.<br>
	 * Option 2 - registers the roles.
	 * 
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 */	
	public String getOutput() {
		String output = ("\n--------- Cargos ---------\n");

		if (access == AccessLevel.TOTAL || access == AccessLevel.ADMIN) {
			output += "0. Voltar\n";
		}

		if (access == AccessLevel.TOTAL) {
			output += "1. Consulta dos Cargos\n";
		}

		if (access == AccessLevel.TOTAL || access == AccessLevel.ADMIN)
			output += "2. Cadastro de Cargos\n";

		if (access == AccessLevel.TOTAL) {
			output += "3. Editar Cargos\n";
			output += "4. Deletar Cargos\n";
		}

		return output;
	}

	/**
	 * Performs a call according to the selected option
	 * 
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 */
	protected void readInput() {

		int option = Main.scan.nextInt();
		switch (option) {
		case 0:
			roleController.goToHome();
			break;
		case 1:
			roleController.list();
			break;
		case 2:
			roleController.create();
			break;
		case 3:
			roleController.edit(0);
			break;
		case 4:
			roleController.delete();
			break;
		default:
			System.out.println("Opção Inválida");
			break;
		}
	}
}
