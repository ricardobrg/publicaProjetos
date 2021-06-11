package view.serviceprovider;

import com.google.gson.JsonObject;

import controller.ServiceProviderController;
import view.console.Console;

/**
 * This class is responsible for searching a object by its id (index in array).
 * <br>
 * Just like any other page class in the system, this one
 * contains a print method, that show the options in
 * console. 
 * 
 * @version 2.0.0
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 *
 */
public class Find {
	public void print() {
		String cnpj = Console.getCNPJ("Cnpj: ");
		JsonObject serviceProvider = new JsonObject();
				
		serviceProvider.addProperty("cnpj", cnpj);

		ServiceProviderController serviceProviderController = new ServiceProviderController();
		
		serviceProvider = serviceProviderController.findByCnpj(serviceProvider);
		
		System.out.println("---- Prestadores de Servico ----");
		System.out.println("Nome: " + serviceProvider.get("name"));
		System.out.println("Cnpj: " + serviceProvider.get("cnpj"));
	}
}
