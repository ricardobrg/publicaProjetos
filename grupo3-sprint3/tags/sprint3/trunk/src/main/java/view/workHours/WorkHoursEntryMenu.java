package view.workHours;

import java.util.Scanner;

import model.entities.security.AccessLevel;
import view.Menu;

public class WorkHoursEntryMenu implements Menu {
	private Scanner scan = new Scanner(System.in);

	@Override
	public void print() {
		if(acc.equals(AccessLevel.BASIC)) {
			new WorkHoursWorkEntry(loggedCpf);
			System.out.println("Ponto registrado com sucesso!!!");
			return;
		}

		System.out.println("Insira um CPF: ");
		String input = scan.nextLine();
		
		new WorkHoursWorkEntry(input);
		System.out.println("Ponto de colaborador registrado com sucesso!!!");
	}	
}
