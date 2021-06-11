package view.serviceprovider;

import java.util.ArrayList;

import com.google.gson.JsonObject;

import controller.ServiceProviderController;
import view.console.Console;

/**
 * ServiceProviderDelete. Has a method that asks for an ID.
 * Searches for the item on the list of Service Providers.
 * Then, deletes it.
 * 
 * @version 2.0.0
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 */
public class Delete {

	public void print(ArrayList<JsonObject> sps) {
		
		System.out.println();
		System.out.println("=======================");
		int i = 0;

		for (JsonObject item : sps) {
			if (item != null) {
				System.out.print(i+" - ");
				System.out.print(item.get("name").getAsString()+" - ");
				System.out.println(item.get("cnpj"));
				i++;	
			}else {
				System.out.println("Nenhum cargo Cadastrado!");
			}
		}

		JsonObject serviceProvider = new JsonObject();
		serviceProvider.addProperty("id", Console.getInputNumber(
				"Informe o ID do objeto que sera deletado: ", 0, i, "ID invalido!"));

		new ServiceProviderController().delete(serviceProvider);
	}
	
	

}
