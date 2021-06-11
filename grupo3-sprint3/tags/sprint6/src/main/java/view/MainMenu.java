package view;

import java.util.ArrayList;

import com.google.gson.JsonObject;

import controller.MainMenuController;
import view.console.Console;

/**
 * Used to display the main menu of the aplication. In its
 * print method will be displayed various options for
 * managing data of the entities.
 * <br>
 * 
 * @version 1.5.0 
 * @author Vinicius Roosevelt Santos Dias
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@Outlook.com>
 */
public class MainMenu extends AbstractMenu{
	
	public MainMenu(JsonObject dataLogin) {
		super(dataLogin);
	}
	
	public void print() {
		
		MainMenuController menuController = new MainMenuController(super.getDataLogin());
		
		System.out.println();
		Console.printHeader("MENU");
		
		ArrayList<String> options = new ArrayList<String>();
				
		if(this.getAccessLevel().getValue() > 0) {
			options.add("Controle de Ponto");
			options.add("Colaborador");
			options.add("Folha de Pagamento");
		}
		if(this.getAccessLevel().getValue() > 1){
			options.add("Prestador de Serviços");
		}
		if(this.getAccessLevel().getValue() > 2) {
			options.add("Cargo");
		}
		
		Console.printOptions(options);
		
		int optionIndex = Console.getInputNumber("Selecione uma opção - ", 0, options.size(), "Opção inválida.");
		
		switch (optionIndex) {

		case 0 : 
			System.out.println("\nSistema finalizado!");
			break;
		case 1 : 
			menuController.workHours();
			this.print();
			break;
		case 2 : 
			menuController.collaborator();
			this.print();
			break;
		case 3 : 
			menuController.payroll();
			this.print();
			break;
		case 4 : 
			menuController.serviceProvider();
			this.print();
			break;
		case 5 : 
			menuController.role();
			this.print();
			break;
		default : 
			System.out.println("Acesso Negado!");
			this.print();
			break;
		}
	}
}





