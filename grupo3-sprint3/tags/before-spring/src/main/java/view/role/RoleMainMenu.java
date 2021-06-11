package view.role;

import java.util.ArrayList;

import com.google.gson.JsonObject;

import controller.RoleController;
import view.AbstractMenu;
import view.console.Console;

/**
 * This class provides a menu containing various 
 * options for managing Role objects
 * Just like any other page class in the system, this one
 * contains a print method, that show the options in
 * console.
 * 
 * @version 1.1.0
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 * @author Giovanni Bruno Buzzi <buzzi.giovannI@outlook.com>
 */
public class RoleMainMenu extends AbstractMenu {
	
	public RoleMainMenu(JsonObject dataLogin) {
		super(dataLogin);
	}

	public void print() {
		System.out.println();
		System.out.println();

		RoleController role = new RoleController();
		
		Console.printHeader("MENU - CARGO");
			
		ArrayList<String> options = new ArrayList<String>();

		System.out.println(this.getAccessLevel().getValue());

		
		if(this.getAccessLevel().getValue() > 1){
			options.add("Listar");
			options.add("Buscar");
			options.add("Adicionar");
			options.add("Editar");
		}
		if(this.getAccessLevel().getValue() > 2) {
			options.add("Deletar");
		}

		Console.printOptions(options);
		
		int optionIndex = Console.getInputNumber("Selecione uma opção - ", 0, options.size(), "Opção inválida.");

		switch (optionIndex) {

			case 0: 
				System.exit(0);
				
			case 1: 
				break;

			case 2: {
				role.getAllMenu();
				this.print();
				break;
			}

			case 3: {
				role.findMenu();
				this.print();
				break;
			}

			case 4: {
				role.addMenu();
				this.print();
				break;
			}
			
			case 5: {
				role.editMenu();
				this.print();
				break;
			}
			
			case 6:{
				role.deleteMenu();
				this.print();
				break;
			}

		}
	}
}
