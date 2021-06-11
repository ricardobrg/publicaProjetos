package view.role;

import java.util.ArrayList;

import com.google.gson.JsonObject;

import controller.RoleController;
import view.console.Console;


/**
 * RoleEdit. This class implements an edit method. 
 * It receives attributes from Role and asks the user for new inputs.
 * Then, sends the information to the controller.
 * 
 * @version 2.0.0
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 */

	public void print(ArrayList<JsonObject> roles) {
		
		System.out.println();
		System.out.println("=======================");
		int lastId = 0;
		for (JsonObject item : roles) {
			System.out.print(item.get("id") + " - ");
			System.out.print(item.get("name") + " - ");
			System.out.println(item.get("accessLevel"));
			lastId = item.get("id").getAsInt();
		}
		System.out.println("=======================");
		System.out.println();

		int id = Console.getInputNumber(
				"Informe o ID a ser editado: ", 0, lastId, "Informe um id valido!");
		
		String name = Console.getName("Nome: ");

		System.out.println("Informe o nivel de acesso: ");
		
		ArrayList<String> options = new ArrayList<String>();
		
		options.add("BASIC");
		options.add("MEDIUM");
		options.add("TOTAL");
		
		Console.printOptions(options);
		int accessLevel = (int) Console.getInputNumber("Selecione uma opcao: ", 0, options.size(), "Opcao Invalida!");
		

		JsonObject role = new JsonObject();

		role.addProperty("name",name);
		role.addProperty("accessLevel",accessLevel);
		role.addProperty("id", id);

		new RoleController().edit(role);
	}
}
