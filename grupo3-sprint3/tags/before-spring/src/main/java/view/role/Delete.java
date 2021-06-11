package view.role;

import java.util.ArrayList;

import com.google.gson.JsonObject;

import controller.RoleController;
import view.console.Console;

/**
 * RoleDelete. 
 * Has a method that asks for an ID.
 * Searches for the item on the list of Roles.
 * Then, deletes it.
 * 
 * @version 2.0.0
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 */
public class Delete {

	public void print(ArrayList<JsonObject> roles) {

		System.out.println();
		System.out.println("=======================");

	    int lastId = 0;
		for (JsonObject item : roles) {
			if (item != null) {
				System.out.print(item.get("id") + " - ");
				System.out.print(item.get("name").getAsString()+" - ");
				System.out.println(item.get("accessLevel"));
	            lastId = item.get("id").getAsInt();
			}else {
				System.out.println("Nenhum cargo Cadastrado!");
			}
		}

		JsonObject role = new JsonObject();
		role.addProperty("id", Console.getInputNumber(
				"Informe o ID do objeto que será deletado: ", 0, lastId, "ID inválido!"));
		
		new RoleController().delete(role);
	}
}
