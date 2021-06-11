package view.collaborator;

import java.util.ArrayList;

import com.google.gson.JsonObject;

import controller.CollaboratorController;
import controller.RoleController;
import view.console.Console;


/**
 * CollaboratorUpdate. 
 * This class implements an edit (update) method. 
 * It asks the user for a modification.
 * After that, asks for an valid ID.
 * Then, sends the information to the controller.
 * 
 * @version 1.5.0
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 * @author Caio Shimada
 */
public class Update {
	
	CollaboratorController collabController = new CollaboratorController();
	
	public void print(JsonObject collabJson) {

		System.out.println("==========================");
		System.out.println("--- Editar Colaborador ---");
		System.out.println("--- Anterior ---");
		System.out.println("Nome: " + collabJson.get("name"));
		System.out.println("--- Novo ---");
		String newName = Console.getName("Nome: ");

		System.out.println("--- Anterior ---");
		System.out.println("Cep: " + collabJson.get("cep"));
		System.out.println("--- Novo ---");
		String newCep = Console.getCEP("CEP: ");

		System.out.println("--- Anterior ---");
		System.out.println("Email: " + collabJson.get("email"));
		System.out.println("--- Novo ---");
		String newEmail = Console.getEmail("Email: ");

		System.out.println("--- Anterior ---");
		System.out.println("Telefone: " + collabJson.get("phone"));
		System.out.println("--- Novo ---");
		String newPhone = Console.getPhone("Telefone: ");

		System.out.println("--- Anterior ---");
		System.out.println("Pis: " + collabJson.get("pis"));
		System.out.println("--- Novo ---");
		String newPis = Console.getPIS("PIS: ");

		System.out.println("--- Anterior ---");
		System.out.println("\nCargo: " + collabJson.get("role").getAsJsonObject().get("name"));
		System.out.println("--- Novo ---");
		System.out.println("==== Cargo ====");
		System.out.println("ID ------ Cargo");

		ArrayList<JsonObject> roles = new RoleController().getAll();
		for(int i = 0; i < roles.size(); i++) {
			System.out.printf("%-8d %-30s %-10s\n", 
					roles.get(i).get("id").getAsInt(), 
					roles.get(i).get("name").getAsString(), 
					roles.get(i).get("accessLevel").getAsString());
		}
		int newRoleId = (int) Console.getInputNumber("Selecione o ID da role: ", 0, roles.size()-1, "Tente Novamente!");		
		
		System.out.println("--- Anterior ---");
		System.out.println("Admission Date: " + collabJson.get("admissionDate"));
		System.out.println("--- Novo ---");
		String newAdmissionDate = Console.getDate("Data de admissão: ");

		System.out.println("--- Anterior ---");
		System.out.println("Horas de Trabalho: " + collabJson.get("workHours"));
		System.out.println("--- Novo ---");
		int newWorkHours = (int) Console.getInputNumber("Horas de Trabalho: ", 0, 1440, "Tente Novamente!");

		System.out.println("--- Anterior ---");
		System.out.println("Salario: " + collabJson.get("salary"));
		System.out.println("--- Novo ---");
		double newSalary = (int) Console.getInputNumber("Salário: ", 0, 9999999999d, "Tente Novamente!");
		
		JsonObject newCollab = new JsonObject();
		
		newCollab.addProperty("id", collabJson.get("id").getAsInt());
		newCollab.addProperty("name", newName);
		newCollab.addProperty("cpf", collabJson.get("cpf").getAsString());
		newCollab.addProperty("email", newEmail);
		newCollab.addProperty("phone", newPhone);
		newCollab.addProperty("cep", newCep);
		newCollab.addProperty("pis", newPis);
		newCollab.addProperty("roleId", newRoleId);
		newCollab.addProperty("admissionDate", newAdmissionDate);
		newCollab.addProperty("workHours", newWorkHours);
		newCollab.addProperty("salary", newSalary);
		
		collabController.update(newCollab);
		
		System.out.println("Alterado com sucesso!");
	}
}
