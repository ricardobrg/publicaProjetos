package view;

import controller.auth.LoginAuth;
import utils.UtilsNumber;
import view.console.Console;

public class Login implements Menu {
	
	@Override
	public void print() {
		
		Console.printHeader("Log In");	
		String cpf = Console.getInput("CPF: ");
		String senha = Console.getInput("Senha: ");

		while(!LoginAuth.isLoggedIn(UtilsNumber.onlyNumbers(cpf), senha)) {
			cpf = UtilsNumber.onlyNumbers(Console.getInput("CPF: "));
			senha = Console.getInput("Senha: ");
		}
		
		new MainMenu().print();
	}
}
