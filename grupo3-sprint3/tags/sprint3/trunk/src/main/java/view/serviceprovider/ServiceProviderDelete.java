package view.serviceprovider;


import java.util.Scanner;

import controller.ServiceProviderController;
import model.entities.person.ServiceProvider;

/**
 * ServiceProviderDelete. Has a method that asks for an ID.
 * Searches for the item on the list of Service Providers.
 * Then, deletes it.
 * 
 * @version 1.0.0
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 */
public class ServiceProviderDelete {
	ServiceProviderController controller = new ServiceProviderController();
	Scanner scan = new Scanner(System.in);
	
	public void print() {
		System.out.println();
		System.out.println("=======================");
		int i = 0;
		for (ServiceProvider item : controller.listServicesProviders()) {
			System.out.print(i+" - ");
			System.out.println(item.getName());
			i++;
		}
		System.out.println("=======================");
		
		System.out.print("Informe o ID do objeto que será deletado: ");
		int deletedObject = scan.nextInt();
		controller.deleteServiceProvider(deletedObject);
	}
}
