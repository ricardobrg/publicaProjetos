package view.workHours;

import view.MainMenu;
import view.Menu;
import view.console.Console;
import model.entities.security.AccessLevel;

import java.util.Scanner;

/**
 * This class provides a menu containing various 
 * options for managing WorkHours objects
 * Just like any other page class in the system, this one
 * contains a print method, that show the options in
 * console.
 * P.S.: WorkHours stands for 'Carga Horária'.
 * 
 * @version 1.0.0
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 */
public class WorkHoursMainMenu implements Menu {
	
	private int max = 2;

	private void checkAccessLevel() {
		if(acc.equals(AccessLevel.MEDIUM) || acc.equals(AccessLevel.TOTAL))
			max = 7;
	}

	public void print() {
		checkAccessLevel();
		
		System.out.println("\n");

		Console.printHeader("MENU - PONTO");
		Console.printOptions(new String[] {
				"Bater Ponto",
				"Listar Todos os Pontos",
				"Listar Pontos por Mês (por CPF)",
				"Listar Pontos por Ano (por CPF)",
				"Listar Pontos por Dia (por CPF)",
				"Voltar",
		});

		Scanner scan = new Scanner(System.in);
		int valor = (int) Console.getInputNumber("Selecione uma opção - ", 0, max, "Acesso Negado!");
		//WorkHoursController workHoursController = new WorkHoursController();

		switch (valor) {
		
		case 1: {
			new WorkHoursEntryMenu().print();
			this.print();
			break;
		}

		case 2: {
			new WorkHoursListAllMenu().print();
			this.print();
			break;
		}

		case 3: {				
			new WorkHoursListMonthMenu().print();
			this.print();
			break;
		}

		case 4: {
			new WorkHoursListYearMenu().print();
			this.print();
			break;
		}

		case 5: {
			new WorkHoursListDayMenu().print();
			this.print();
			break;
		}

		case 6: {
			new MainMenu().print();
		}

		default: {
			System.out.println("Opção Inválida!");
		}
		}
	}
}
