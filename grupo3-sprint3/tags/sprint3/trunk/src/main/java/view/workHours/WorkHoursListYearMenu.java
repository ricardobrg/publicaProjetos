package view.workHours;

import java.util.Scanner;

import view.Menu;

public class WorkHoursListYearMenu implements Menu{
	
	Scanner scan = new Scanner(System.in);
	
	@Override
	public void print() {
		System.out.println("Informe o CPF: ");
		String cpf = scan.nextLine();

		System.out.println("Informe o Ano: ");
		int year = scan.nextInt();

		new WorkHoursListYear(cpf,year).print();
	}

}
