package view;

import com.google.gson.JsonObject;

import controller.LoginController;
import view.console.Console;

/**
 * Used to display the first part of the aplication. 
 * In its printing method will be displayed a input
 * method asking for a CPF and password.
 * While the CPF is incorrect or the password, the 
 * console will keep asking for the right key.
 * 
 * @version 1.0.0 
 * @author Vinicius Roosevelt Santos Dias
 */
public class Login {
	
	private JsonObject doLogin() {
		String cpf = Console.getCPF("CPF: ");
		String password = Console.getText("Senha: ");

		JsonObject login = new JsonObject();
		login.addProperty("cpf", cpf);
		login.addProperty("password", password);
		
		return new LoginController().logIn(login);
	}
	
	
	public void print() {
		Console.printHeader("Log In");	
		
		JsonObject ret = doLogin();
		
		while(!ret.get("message").getAsString().equals("success")) {
			System.out.println("Erro - " + ret.get("message").getAsString());
			doLogin();
		}
		
	}
}
