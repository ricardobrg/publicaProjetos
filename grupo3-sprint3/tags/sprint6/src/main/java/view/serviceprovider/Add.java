package view.serviceprovider;

import com.google.gson.JsonObject;

import controller.ServiceProviderController;
import view.console.Console;

/**
 * ServiceProviderAdd. This class has a method that adds 
 * a new Service Provider in "newServiceProvider",
 * packs everything up and sends for the controller.  
 * 
 * @version 1.5.0
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 */
public class Add {
	public void print() {
		
		JsonObject serviceProvider = new JsonObject();
		
		serviceProvider.addProperty("name", Console.getName("\nInforme o Nome do Provedor de Serviços: "));
		serviceProvider.addProperty("email", Console.getEmail("Informe o email do Provedor: "));
		serviceProvider.addProperty("phone", Console.getPhone("Informe o telefone do Provedor: "));
		serviceProvider.addProperty("socialReason", Console.getName("Informe a razao social do Provedor: "));
		serviceProvider.addProperty("cnpj", Console.getCNPJ("Informe o CPNJ do provedor: "));
		serviceProvider.addProperty("cep", Console.getCEP("Informe o CEP do provedor: "));
		
		new ServiceProviderController().insert(serviceProvider);
	}
}
