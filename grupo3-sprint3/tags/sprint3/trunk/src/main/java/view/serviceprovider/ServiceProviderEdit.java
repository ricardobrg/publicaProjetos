package view.serviceprovider;

import java.util.Scanner;

import controller.ServiceProviderController;
import model.entities.person.ServiceProvider;

/**
 * ServiceProviderEdit. This class implements an edit method. 
 * It receives attributes from ServiceProvider and asks the user new inputs.
 * Then, sends the information to the controller.
 * 
 * @version 1.0.0
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 */
public class ServiceProviderEdit {
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
		System.out.println();

		System.out.print("ID do provedor que será editado - ");
		int editedId = scan.nextInt();
		
		ServiceProvider oldServiceProvider, editedServiceProvider = new ServiceProvider();
		oldServiceProvider = controller.findServiceProvider(editedId);		

		
		System.out.println("Nome Fantasia antigo - "+oldServiceProvider.getName());
		System.out.print("Novo nome fantasia- ");
		scan.nextLine();
		String newName = scan.nextLine();
		editedServiceProvider.setName(newName);


		System.out.println("Email antigo - "+oldServiceProvider.getEmail());
		System.out.print("Novo email - ");
		String newEmail = scan.nextLine();
		editedServiceProvider.setName(newEmail);


		System.out.println("Telefone antigo - "+oldServiceProvider.getPhone());
		System.out.print("Novo telefone - ");
		String newPhone = scan.nextLine();
		editedServiceProvider.setName(newPhone);


		System.out.println("Razão social antiga - "+oldServiceProvider.getSocialReason());
		System.out.print("Nova razão social - ");
		String newSocialReason = scan.nextLine();
		editedServiceProvider.setName(newSocialReason);


		System.out.println("CNPJ antigo - "+oldServiceProvider.getCnpj());
		System.out.print("Novo CNPJ - ");
		String newCnpj= scan.nextLine();
		editedServiceProvider.setCnpj(newCnpj);
		
		controller.editServiceProvider(editedServiceProvider, editedId);
	}
}