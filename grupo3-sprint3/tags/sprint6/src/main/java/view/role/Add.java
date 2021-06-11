package view.role;

import java.util.ArrayList;

import com.google.gson.JsonObject;

import controller.RoleController;
import view.console.Console;

/**
 * RoleAdd. 
 * This class has a method that adds a new Role 
 * in "newRole" and saves the Access Level.
 * (Valor: stands for reference tags. E.g.: 1 = Basic)
 * Finally, packs everything up and sends for the controller.  
 * 
 * @version 2.0.0
 *
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 */
public class Add {

	public void print() {
		
		String name = Console.getName("Nome: ");
		
		System.out.println("Informe o nivel de acesso: ");
		
		ArrayList<String> options = new ArrayList<String>();
		
		options.add("BASIC");
		options.add("MEDIUM");
		options.add("TOTAL");
		
		Console.printOptions(options);
		int accessLevel = (int) Console.getInputNumber("Selecione uma opcao: ", 0, options.size(), "Opcao Invalida!");
		
		JsonObject role = new JsonObject();
		
		role.addProperty("name", name);
		role.addProperty("accessLevel", accessLevel);
		
		new RoleController().add(role);
		
	}
}
