package view.workHours;

import java.util.Scanner;

import view.Menu;

public class WorkHoursListDayMenu implements Menu{
	
	Scanner scan = new Scanner(System.in);
	
	@Override
	public void print() {
		System.out.println("Informe o CPF: ");
		String cpf = scan.nextLine();

		System.out.println("Informe o Dia: ");
		int day = scan.nextInt();

		System.out.println("Informe o Mês: ");
		int month = scan.nextInt();

		System.out.println("Informe o Ano: ");
		int year = scan.nextInt();

		new WorkHoursListDay(cpf,day,month,year).print();
	}

}
