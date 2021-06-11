package view.serviceprovider;

import java.util.ArrayList;

import com.google.gson.JsonObject;

import controller.ServiceProviderController;
import view.console.Console;

/**
 * ServiceProviderEdit. This class implements an edit method. 
 * It receives attributes from ServiceProvider and asks the user new inputs.
 * Then, sends the information to the controller.
 * 
 * @version 1.5.0
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 */
public class Edit {
	
	public void print(ArrayList<JsonObject> sps) {
		System.out.println();
		System.out.println("=======================");
		int i = 0;
		for (JsonObject item : sps) {
			System.out.print("ID: " + i + " - ");
			System.out.print("Nome:" + item.get("name") + " - ");
			System.out.println("CNPJ: " + item.get("cnpj"));
			i++;
		}
		System.out.println("=======================");
		System.out.println();

		int id = Console.getInputNumber(
				"Informe o ID a ser editado: ", 0, i, "Informe um id valido!");

		String name = Console.getName("Nome fantasia: ");
		String email = Console.getEmail("Email: ");
		String phone = Console.getPhone("Telefone: ");
		String socialReason = Console.getName("Razao Social: ");
		String cnpj = Console.getCNPJ("CNPJ: ");
		
		JsonObject serviceProvider = new JsonObject();

		serviceProvider.addProperty("id", id);
		serviceProvider.addProperty("name",name);
		serviceProvider.addProperty("email",email);
		serviceProvider.addProperty("phone",phone);
		serviceProvider.addProperty("socialReason",socialReason);
		serviceProvider.addProperty("cnpj",cnpj);
		
		new ServiceProviderController().update(serviceProvider);
	}
}