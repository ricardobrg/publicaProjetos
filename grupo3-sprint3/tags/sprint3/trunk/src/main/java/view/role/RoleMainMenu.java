package view.role;

import view.MainMenu;
import view.Menu;
import view.console.Console;

import java.util.Scanner;

/**
 * This class provides a menu containing various 
 * options for managing Role objects
 * Just like any other page class in the system, this one
 * contains a print method, that show the options in
 * console.
 * 
 * @version 1.0.0
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 */
public class RoleMainMenu implements Menu {
	
	public void print() {
		System.out.println();
		System.out.println();

		Console.printHeader("MENU - CARGO");
		Console.printOptions(new String[] {
				"Listar todos Cargos", 
				"Buscar Cargo pelo ID", 
				"Adicionar Cargo",
				"Editar Cargo",
				"Deletar Cargo",
				"Voltar",
		});

		Scanner scan = new Scanner(System.in);
		int valor = (int) Console.getInputNumber("Selecione uma opção - ", 0, 6, "Opção inválida");

		switch (valor) {

			case 0: {
				break;
			}

			case 1: {
				new RoleListAll().print();
				this.print();
				break;
			}

			case 2: {
				new RoleFind().print();
				this.print();
				break;
			}

			case 3: {
				new RoleAdd().print();
				this.print();
				break;
			}
			
			case 4: {
				new RoleEdit().print();
				this.print();
				break;
			}
			
			case 5:{
				new RoleDelete().print();
				this.print();
				break;
			}
			
			case 6: {
				new MainMenu().print();
				break;
			}

		}
	}
}
