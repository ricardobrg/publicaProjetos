package view.serviceprovider;

import java.util.Scanner;

import controller.ServiceProviderController;
import model.entities.person.ServiceProvider;


/**
 * ServiceProviderAdd. This class has a method that adds 
 * a new Service Provider in "newServiceProvider",
 * packs everything up and sends for the controller.  
 * 
 * @version 1.0.0
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 */
public class ServiceProviderAdd {
	public void print() {
		ServiceProvider newServiceProvider = new ServiceProvider();
		Scanner scan = new Scanner(System.in);
		ServiceProviderController controller = new ServiceProviderController();

		System.out.println();
		System.out.print("Informe o Nome do Provedor de Serviços: ");
		String newServiceProviderName = scan.nextLine();
		newServiceProvider.setName(newServiceProviderName);


		System.out.println();
		System.out.print("Informe o email do Provedor: ");
		String newServiceProviderEmail = scan.nextLine();
		newServiceProvider.setEmail(newServiceProviderEmail);


		System.out.println();
		System.out.print("Informe o telefone do Provedor: ");
		String newServiceProviderPhone = scan.nextLine();
		newServiceProvider.setPhone(newServiceProviderPhone);


		System.out.println();
		System.out.print("Informe a razão social do Provedor: ");
		String newServiceProviderSocialReason = scan.nextLine();
		newServiceProvider.setSocialReason(newServiceProviderSocialReason);


		System.out.println();
		System.out.print("Informe o CNPJ do Provedor: ");
		String newServiceProviderCnpj = scan.nextLine();
		newServiceProvider.setCnpj(newServiceProviderCnpj);

		controller.addServiceProvider(newServiceProvider);
	}
}
