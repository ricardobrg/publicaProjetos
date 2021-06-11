package view.collaborator;

import java.util.ArrayList;

import com.google.gson.JsonObject;

import controller.CollaboratorController;
import controller.RoleController;
import view.console.Console;


/**
 * CollaboratorAdd. This class has a method that adds 
 * a new Collaborator in "newCollaborator" with its information.
 * If the ID is not valid, gives the user another chance to type it right.
 * 
 * @version 1.5.0
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 * @author Caio Shimada
 */
public class Add {

	public void print() {	
		CollaboratorController collabController = new CollaboratorController();
		
		System.out.println("Cadastro de colaborador");
		System.out.println("=======================");

		JsonObject collab = new JsonObject();
		
		String name = Console.getName("Nome: ");
		String cpf = Console.getCPF("CPF: ");		
		String email = Console.getEmail("Email: ");
		String phone = Console.getPhone("Telefone: ");	
		String cep = Console.getCEP("CEP: ");
		String pis = Console.getPIS("PIS: ");
		
		System.out.println("==== Cargo ====");
		System.out.println("ID === Cargo: ");
		
		ArrayList<JsonObject> roles = new RoleController().getAll();
		for(int i = 0; i < roles.size(); i++) {
			System.out.printf("%-8d %-30s %-10s\n", 
					roles.get(i).get("id").getAsInt(), 
					roles.get(i).get("name").getAsString(), 
					roles.get(i).get("accessLevel").getAsString());
		}
		int roleId = (int) Console.getInputNumber("Selecione o ID da role: ", 0, roles.size()-1, "Tente Novamente!");		
		
		String admissionDate = Console.getAdmissionDate("Data de admissão [ENTER para usar a data atual]: ");		
		int workHours = (int) Console.getInputNumber("Carga horária diária em minutos: ", 0, 1440, "Tente Novamente!");		
		Double salary = Double.valueOf(Console.getOnlyNumbers("Salário: "));
		String pwd = Console.getText("Senha: ");
		
		collab.addProperty("name", name);
		collab.addProperty("cpf", cpf);
		collab.addProperty("email", email);
		collab.addProperty("phone", phone);
		collab.addProperty("cep", cep);
		collab.addProperty("pis", pis);
		collab.addProperty("roleId", roleId);
		collab.addProperty("admissionDate", admissionDate);
		collab.addProperty("workHours", workHours);
		collab.addProperty("salary", salary);
		collab.addProperty("pwd", pwd);
		
		collabController.insert(collab);
	}
}
