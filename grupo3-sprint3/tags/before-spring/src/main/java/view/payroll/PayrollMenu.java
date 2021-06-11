package view.payroll;

import java.util.ArrayList;

import com.google.gson.JsonObject;

import controller.PayrollController;
import view.console.Console;

/**
 * PayrollMenu Class<br>
 * Makes a conversation with the user, that types information that will be sent
 * to the controller, via JsonObject.
 * 
 * @version 1.5.0
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 */
public class PayrollMenu {

	public void print() {

		JsonObject payrollData = new JsonObject();

		JsonObject discountData = new JsonObject();

		ArrayList<JsonObject> discounts = new ArrayList<JsonObject>();

		String cpf = Console.getCPF("Informe o CPF do colaborador: ");
		payrollData.addProperty("cpf", cpf);

		String date1 = Console.getDate("Informe a data inicial: ");
		payrollData.addProperty("date1", date1);

		String date2 = Console.getDate("Informe a data final: ");
		payrollData.addProperty("date2", date2);

		double extra = Console.getInputNumber("Informe o valor da hora extra: ", 0, 999999,
				"Informe um numero valido!");
		payrollData.addProperty("extra", extra);

		while (true) {

			String name = Console.getName("Informe o nome do desconto");
			discountData.addProperty("name", name);

			int type = Console.getInputNumber(
					"--- Informe o ID do desconto ---\n" + "1 - Porcentagem\n 2 - Valor Fixo\n --> ID: ", 1, 2,
					"\nInforme um ID válido");
			discountData.addProperty("type", type == 1 ? true : false);

			double value = Console.getInputNumber("Informe o valor: ", 0.0, 9999999.0, "\nInforme um numero valido!");
			discountData.addProperty("value", value);

			int cont = Console.getInputNumber("1 - Adicionar outro desconto\n" + "2 - Sair", 1, 2,
					"\nInforme um ID valido!");
			
			discounts.add(discountData);
			
			if (cont == 2)
				break;
				
		}

		double salary = new PayrollController().generatePayroll(payrollData, discounts);

		System.out.printf("Pagamento: %.2f\n", salary);

	}

}
