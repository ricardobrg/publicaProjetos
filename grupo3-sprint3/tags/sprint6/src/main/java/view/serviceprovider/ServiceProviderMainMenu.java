package view.serviceprovider;

import java.util.ArrayList;

import com.google.gson.JsonObject;

import controller.ServiceProviderController;
import view.AbstractMenu;
import view.console.Console;

/**
 * This class provides a menu containing various 
 * options for managing ServiceProvider objects<br>
 * Just like any other page class in the system, this one
 * contains a print method, that show the options in
 * console.
 * 
 * @version 1.5.0
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 *
 */
public class ServiceProviderMainMenu extends AbstractMenu {

	public ServiceProviderMainMenu(JsonObject dataLogin) {
		super(dataLogin);
	}

	public void print() {
		System.out.println();
		System.out.println();

		ServiceProviderController spController = new ServiceProviderController();

		Console.printHeader("MENU - PRESTADOR DE SERVIÇOS");

		ArrayList<String> options = new ArrayList<String>();

		System.out.println(this.getAccessLevel().getValue());

		if(this.getAccessLevel().getValue() > 1){
			options.add("Listar");
			options.add("Buscar");
			options.add("Adicionar");
			options.add("Editar");
		}

		Console.printOptions(options);

		int optionIndex = Console.getInputNumber("Selecione uma opção - ", 0, options.size(), "Opção inválida.");

		switch (optionIndex) {

			case 0: 
				System.exit(0);
	
			case 1: 
				break;
	
			case 2: 
				spController.listAllMenu();
				this.print();
				break;
	
			case 3: 
				spController.findMenu();
				this.print();
				break;
	
			case 4: 
				spController.addMenu();
				this.print();
				break;
	
			case 5: 
				spController.editMenu();
				this.print();
				break;
	
			case 6:
				spController.deleteMenu();
				this.print();
				break;
	
			}
	}
}
