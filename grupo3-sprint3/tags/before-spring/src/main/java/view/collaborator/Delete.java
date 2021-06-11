package view.collaborator;

import com.google.gson.JsonObject;

import controller.CollaboratorController;
import view.console.Console;

/***
 * CollaboratorDelete. Has a method that asks for a CPF. Finds the Collaborator
 * with the given CPF and Deletes him using his ID
 * 
 * @version 1.5.0
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 * @author Caio Shimada
 */

public class Delete {
	
	CollaboratorController collabController = new CollaboratorController();

	public void print() {
		
		String cpf = Console.getCPF("CPF: ");
		
		JsonObject cpfJson = new JsonObject();
		cpfJson.addProperty("cpf", cpf);

		JsonObject collabJson = collabController.findByCpf(cpfJson);
		JsonObject id = new JsonObject();
		id.addProperty("id", collabJson.get("id").getAsInt());
		
		collabController.delete(id);
		
		System.out.println(collabJson.get("name").toString() + " deletado com sucesso!");
	}
}
