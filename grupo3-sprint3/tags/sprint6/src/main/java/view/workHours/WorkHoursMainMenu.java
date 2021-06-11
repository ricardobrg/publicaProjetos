package view.workHours;

import java.util.ArrayList;

import com.google.gson.JsonObject;

import controller.WorkHoursController;
import view.AbstractMenu;
import view.console.Console;

/**
 * This class provides a menu containing various 
 * options for managing WorkHours objects
 * Just like any other page class in the system, this one
 * contains a print method, that show the options in
 * console.
 * P.S.: WorkHours stands for 'Carga Horï¿½ria'.
 * 
 * @version 1.0.0
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 */
public class WorkHoursMainMenu extends AbstractMenu{

	public WorkHoursMainMenu(JsonObject dataLogin) {
		super(dataLogin);
	}

	public void print() {	

		WorkHoursController wkController = new WorkHoursController(super.getDataLogin());

		Console.printHeader("MENU - PONTO");

		ArrayList<String> options = new ArrayList<String>();

		if(this.getAccessLevel().getValue() > 0) {
			options.add("Voltar");
			options.add("Bater Ponto");
			options.add("Listar Todos");
			options.add("Listar Entre Duas Datas");
		}
	
		Console.printOptions(options);

		int optionIndex = Console.getInputNumber("Selecione uma opção - ", 0, options.size(), "Opção inválida.");

		switch (optionIndex) {

			case 0: 
				System.exit(0);
	
			case 1: 
				break;
	
			case 2: {
				wkController.clockIn();
				this.print();
				break;
			}
	
			case 3: {
				wkController.listAllMenu();
				this.print();
				break;
			}
	
			case 4: {
				wkController.showHours(Console.getDate("Informe a data inicial: "), 
						Console.getDate("Informe a data final: "));
				this.print();
				break;
			}
	
			default: {
				System.out.println("Opção inválida!");
				this.print();
			}
		}
	}
}



