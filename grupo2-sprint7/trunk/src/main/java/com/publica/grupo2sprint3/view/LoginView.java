package com.publica.grupo2sprint3.view;

import com.publica.grupo2sprint3.controller.LoginController;
import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.util.ClearConsole;

/***
 * Class responsible for inputs and outputs from the login com.publica.grupo2sprint3.view
 * 
 * @version 2.0.0
 * 
 * @author Diego Borba <diegoborba25@gmail.com>
 * @author Caio Shimada
 * 
 */
public class LoginView extends View {

	private static LoginView instance;

	private LoginView() {
		super(null);
	}

	public static LoginView getInstance() {
		if (instance == null) {
			instance = new LoginView();
		}
		return instance;
	}

	public static void destroyInstance() {
		instance = null;
	}

	/***
	 * Method responsible for the initialize display login
	 * 
	 * @author Diego Borba <diegoborba25@gmail.com>
	 */
	@Override
	public void display() {
		ClearConsole.clearConsole();
		System.out.println(getOutput());
		readInput();
	}

	/**
	 * Method responsible for the initialize display login at the first time
	 * 
	 * @author Diego Borba <diegoborba25@gmail.com>
	 */
	public void firstDisplay() {
		System.out.println(getOutput());
		readInput();
	}

	/**
	 * Returns the Login Message
	 * 
	 * @author Diego Borba
	 * @author Caio Shimada
	 * 
	 * @return the login string message
	 */
	@Override
	public String getOutput() {
		return "--------- Login ---------";
	}

	/**
	 * Method responsabile for the user's inputs
	 * 
	 * @author Diego Borba <diegoborba25@gmail.com>
	 */
	@Override
	protected void readInput() {
		String user, password = "";

		System.out.print("Usuário: ");
		user = Main.scan.nextLine();
		if (user == "")
			user = Main.scan.nextLine();

		System.out.print("Senha: ");
		password = Main.scan.nextLine();

		if (LoginController.login(user, password))
			LoginController.goToHome(LoginController.getCollab());
		else {
			System.out.println("Usuário e/ou senha incorreto(s)");
			readInput();
			ClearConsole.clearConsole();
		}

	}
}
