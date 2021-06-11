package view.collaborator;

import com.google.gson.JsonObject;

import controller.CollaboratorController;

/***
 * CollaboratorFind.
 * The print method searches for Collaborator information
 * via its CPF, received in the Add part. If found, returns it. 
 * Otherwise throws an error message. 
 * 
 * @version 1.5.0
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 * @author Caio Shimada
 */
public class Find {

	CollaboratorController collabController = new CollaboratorController();
	
	public void print(JsonObject collabJson) {
		
		if(collabJson != null) {
			
			System.out.println("===================================");

			System.out.println("Nome:       "+collabJson.get("name"));
			System.out.println("CEP:        "+collabJson.get("cep"));
			System.out.println("Email:      "+collabJson.get("email"));
			System.out.println("Telefone:   "+collabJson.get("phone"));
			System.out.println("CPF:        "+collabJson.get("cpf"));
			System.out.println("PIS:        "+collabJson.get("pis"));
			System.out.println("Cargo:      "+collabJson.get("role").getAsJsonObject().get("name"));
			System.out.println("D.admissao: "+collabJson.get("admissionDate"));
			System.out.println("C. Horaria: "+collabJson.get("workHours"));
			System.out.println("Salario:    "+collabJson.get("salary"));
		}
		else 
			System.out.println("CPF não cadastrado!!!");
	}
}
