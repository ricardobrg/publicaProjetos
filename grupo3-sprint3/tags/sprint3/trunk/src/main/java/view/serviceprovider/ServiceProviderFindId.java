package view.serviceprovider;

import java.util.Scanner;

import controller.ServiceProviderController;
import model.entities.person.ServiceProvider;


/***
 * ServiceProviderFindID.Searches for a Service Provider via its ID,
 * automatically inserted in Add part. If found returns it. 
 * Otherwise throws an error message. 
 * 
 * @version 1.0.0
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 */
public class ServiceProviderFindId {
	public void print() {
		ServiceProviderController controller = new ServiceProviderController();
		Scanner scan = new Scanner(System.in);
		
		System.out.println();
		System.out.print("Informe o ID do Prestador de Serviço - ");
		int id = scan.nextInt();
		System.out.println("=======================");

		ServiceProvider object = (ServiceProvider)controller.findObject(id);
		
		if (object != null) {
			System.out.println(object.getName());
		}else {
			System.out.println("Prestador de Serviço não encontrado");
		}
		System.out.println("=======================");
	}
}
