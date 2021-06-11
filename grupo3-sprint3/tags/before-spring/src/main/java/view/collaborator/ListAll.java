package view.collaborator;

import java.util.ArrayList;

import com.google.gson.JsonObject;

import controller.CollaboratorController;

/**
 * Lists all Collaborators with their IDs, names, cpfs and roles.
 * 
 * @version 1.0.0
 * 
 * @author Caio Shimada
 */
public class ListAll {
	
	CollaboratorController collabController = new CollaboratorController();

	public void print(ArrayList<JsonObject> collabs) {
		
		System.out.println();
		System.out.println("=======================");
		
		System.out.printf("%-8s %-25s %-25s %-25s\n", "Id", "Nome", "CPF", "Cargo");
		for (JsonObject item : collabs) {
			if (item != null) {
				System.out.printf("%-8d %-25s %-25s %-25s\n", 
						item.get("id").getAsInt(), 
						item.get("name").getAsString(), 
						item.get("cpf").getAsString(), 
						item.get("role").getAsString());
			}else {
				System.out.println("Nenhum colaborador Cadastrado!");
			}
		}
		System.out.println("=======================");
		
	}
	
}
