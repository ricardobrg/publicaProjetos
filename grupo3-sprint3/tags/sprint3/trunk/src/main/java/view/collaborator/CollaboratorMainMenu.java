package view.collaborator;
import view.MainMenu;
import view.Menu;
import view.console.Console;

import java.util.Scanner;


/**
 * This class provides a menu containing various 
 * options for managing Collaborator objects.
 * Just like any other page class in the system, this one
 * contains a print method, that show the options in
 * console.
 * 
 * @version 1.0.0
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 *
 */
public class CollaboratorMainMenu implements Menu {

	public void print() {
		System.out.println();
		System.out.println();

		Console.printHeader("MENU - COLABORADOR");
		Console.printOptions(new String[] {
				"Adicionar colaborador",
				"Buscar colaborador por CPF", 
				"Editar colaborador por CPF", 
				"Excluir colaborador",
				"Voltar",
		});

		Scanner scan = new Scanner(System.in);
		int valor = (int) Console.getInputNumber("Selecione uma opção - ", 0, 5, "Opção inválida");

		switch (valor) {

			case 0: {
				break;
			}
			
			case 1: {
				new CollaboratorAdd().print();
				this.print();
				break;
			}
		
			case 2: {
				System.out.println("Informe o CPF: ");
				new CollaboratorFind(scan.nextLine()).print();
				this.print();
				break;
			}

			case 3: {
				System.out.println("Informe o CPF: ");
				new CollaboratorUpdate(scan.nextLine()).print();
				this.print();
				break;
			}

			case 4: {
				System.out.println("Informe o CPF: ");
				new CollaboratorDelete(scan.nextLine()).print();
				this.print();
				break;
			}
			case 5: {
				new MainMenu().print();
			}

		}
	}
}
