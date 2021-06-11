package view.serviceprovider;

import controller.ServiceProviderController;
import model.entities.person.ServiceProvider;

/***
 * ServiceProviderListAll. 
 * Lists all ServiceProviders in listServicesProviders. 
 * Which is an ArrayList of Service Providers, in Controllers.
 * Otherwise throws an error message. 
 * 
 * @version 1.0.0
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 */
public class ServiceProviderListAll {
	public void print() {
		ServiceProviderController controller = new ServiceProviderController();

		System.out.println();
		System.out.println("=======================");
		int i = 0;

		for (ServiceProvider item : controller.listServicesProviders()) {
			if (item != null) {
				System.out.print(i + " - ");
				System.out.println(item.getName());
				i++;
			} else {
				System.out.println("Nenhum Prestador de Serviço Cadastrado!");
			}
		}
		System.out.println("=======================");
	}
}
