package view.payroll;

import java.util.ArrayList;

import com.google.gson.JsonObject;

public class ShowHistory {

	public void print(ArrayList<JsonObject> payrollHistory) {

		System.out.println("______________________________________________________________");
		System.out.printf("| %-20s %-15s %-12s %-8s |\n", "Nome", "CPF", "Data", "Salario");

		for (JsonObject item : payrollHistory) {
			String name = item.get("name").getAsString();
			String cpf = item.get("cpf").getAsString();
			String date = item.get("date").getAsString();
			String salary = item.get("netSalary").getAsString();
			System.out.printf("| %-20s %-15s %-12s %-8s |", name, cpf, date, salary);
		}

		System.out.println("______________________________________________________________");
	}

}
