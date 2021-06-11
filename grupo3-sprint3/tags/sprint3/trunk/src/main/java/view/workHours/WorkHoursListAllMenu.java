package view.workHours;

import java.util.Scanner;

import model.entities.security.AccessLevel;
import utils.validations.number.CPFValidation;
import view.Menu;

public class WorkHoursListAllMenu implements Menu{
	Scanner scan = new Scanner(System.in);
	
	@Override
	public void print() {
		System.out.println("Informe o CPF: ");
		String input = scan.nextLine();
		
		while(!CPFValidation.isCpfValid(input)) {
			System.out.print("\nCPF Inválido!\n Tente Novamente: ");
			input = scan.nextLine();
		}
		
		if(acc.equals(AccessLevel.BASIC) && !input.equals(loggedCpf))
			return;
		
		new WorkHoursListAll(input).print();
	}
}
