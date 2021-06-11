package view.payroll;

import java.util.ArrayList;

import com.google.gson.JsonObject;

import controller.PayrollController;
import view.AbstractMenu;
import view.console.Console;



/***
 * PayrollMainMenu<br>
 * Class to redirect user to choice menu.
 * 
 * @version 1.0.0	
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 */
public class PayrollMainMenu extends AbstractMenu{
	
	public PayrollMainMenu(JsonObject dataLogin) {
		super(dataLogin);
	}

	public void print() {

		System.out.println("\n");

		Console.printHeader("MENU - PONTO");
	
		ArrayList<String> options = new ArrayList<String>();


		System.out.println(this.getAccessLevel().getValue());

		if(this.getAccessLevel().getValue() > 0) {
			options.add("Voltar");
			options.add("Historico");
		}
		if(this.getAccessLevel().getValue() > 1){
			options.add("Gerar Folha de Pagamento");
		}

		Console.printOptions(options);
		
		int optionIndex = Console.getInputNumber("Selecione uma opção - ", 0, options.size(), "Opção inválida.");

		PayrollController payrollController = new PayrollController();
		
		switch (optionIndex) {

			case 0: 
				System.exit(0);
				
			case 1: 
				break;
		
			case 2: {
				payrollController.payrollMenu();
				this.print();
				break;
			}
			
			case 3: {
				payrollController.getPayrollMenu(super.getCpf());
				this.print();
				break;
			}
	
			default: {
				System.out.println("Opção Inválida!");
			}
		}
	}
}
