package com.publica.grupo2sprint3.view.providerView;

import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;

/**
 * This is the Main Menu View for the Providers.
 * 
 * @version 1.0.0
 * 
 * @author Caio Shimada
 *
 */
public class ProviderViewMenu extends ProviderView{

	private static ProviderViewMenu instance;
	
	private ProviderViewMenu(Collaborator collab) {
		super(collab);
	}
	
	public static ProviderViewMenu getInstance(Collaborator collab) {
		if (instance == null) {
			instance = new ProviderViewMenu(collab);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		instance = null;
	}
	
	/**
	 * It sets the output with the options to be printed on the com.publica.grupo2sprint3.view.
	 * 
	 * @author Caio Shimada
	 * 
	 * @return the string set up ready to be printed
	 */
	@Override
	public String getOutput() {
		String output = "\n--------- Prestadores de Serviços ---------\n";

		if (access.id >= AccessLevel.ADVANCED.id) {
			output += "Você não deveria estar aqui. Redirecionando\n";
			controller.goToHome();
		}

		if (access == AccessLevel.TOTAL)
		output += "0. Voltar\n"
				+ "1. Lista de Prestador\n"
				+ "2. Cadastrar Novo Prestador\n";
		
		return output;
	}
	
	/**
	 * Reads the option selected by the user and calls the
	 * correct method in the Controller.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
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
		case 2:
			controller.create();
			break;
		default:
			System.out.println("Opção Inválida");
		}
	}
}
