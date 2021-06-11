package com.publica.grupo2sprint3.view;

import com.publica.grupo2sprint3.controller.HomeController;
import com.publica.grupo2sprint3.controller.LoginController;
import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;

/**
 * Implements the Home Page View.
 * 
 * It features access level verification so that the correct options are
 * displayed acording to the Collaborator's Role Access Level.
 * 
 * @version 1.0.0
 * @author Caio Shimada <xcaiosr@gmail.com>
 */
public class HomeView extends View{

	private static HomeView instance;
	
	private HomeView(Collaborator collab) {
		super(collab);
	}
	
	public static HomeView getInstance(Collaborator collab) {
		if (instance == null) {
			instance = new HomeView(collab);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		instance = null;
	}
	
	/**
	 * Sets up the String to be displayed.
	 * 
	 * Options 1 to 3 are available to all Access Levels.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * 
	 * @return the set up string ready to be displayed
	 */
	@Override
	public String getOutput() {
		HomeController.clearConsole();
		String output = "--------- Página Inicial ---------\n";
		
		if (access == AccessLevel.ADMIN) {
			output += "0. Logout\n"
					+ "1. Cadastro de Colaborador\n"
					+ "2. Cadastro de Cargo\n";
		}
		
		if (access.id <= AccessLevel.BASIC.id) {
			output += "0. Logout\n"
					+ "1. Perfil\n"
					+ "2. Registro de Pontos\n"
					+ "3. Fechamento do Mês\n";
		}
		
		if (access.id <= AccessLevel.ADVANCED.id) {
			output += "4. Colaboradores\n"
					+ "5. Controle de Férias\n";
		}

		if (access == AccessLevel.TOTAL) {
			output += "6. Cargos\n"
					+ "7. Departamentos\n"
					+ "8. Provedores de Serviços\n";
		}
		
		return output;
	}

	/**
	 * Reads the input to call the correct com.publica.grupo2sprint3.controller.
	 * 
	 * It verifies the Access Level before calling to ensure that the user has
	 * access to the option selected.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 */
	@Override
	protected void readInput() {
			
		int input = Main.scan.nextInt();
		
		switch (input) {
		case 1:
			if (access == AccessLevel.ADMIN) 
				HomeController.goToCollaborators(collab);
			else
				HomeController.goToProfile(collab);			
			break;
		case 2:
			if (access == AccessLevel.ADMIN)
				HomeController.goToRole(collab);
			else
				HomeController.goToPoint(collab);
			break;
		case 3:
			HomeController.goToPayroll(collab);
			break;
		case 4:
			if (access.id <= AccessLevel.ADVANCED.id)
				HomeController.goToCollaborators(collab);
			else
				System.out.println("Você não tem permissão para abrir essa aba");
			break;
		case 5:
			if (access.id <= AccessLevel.ADVANCED.id)
				HomeController.goToVacation(collab);
			else
				System.out.println("Você não tem permissão para abrir essa aba");
			break;
		case 6:
			if (access == AccessLevel.TOTAL)
				HomeController.goToRole(collab);
			else
				System.out.println("Você não tem permissão para abrir essa aba");
			break;
		case 7:
			if (access == AccessLevel.TOTAL)
				HomeController.goToDepartments(collab);
			else
				System.out.println("Você não tem permissão para abrir essa aba");
			break;
		case 8:
			if (access == AccessLevel.TOTAL)
				HomeController.goToProviders(collab);
			else
				System.out.println("Você não tem permissão para abrir essa aba");
		case 0:
			LoginController.logout();
			break;

		default:
			System.out.println("Opção Inválida!");
			break;
		}
	}
}
