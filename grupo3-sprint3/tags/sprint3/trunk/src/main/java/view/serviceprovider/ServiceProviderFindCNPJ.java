package view.serviceprovider;

import java.util.Scanner;

import controller.ServiceProviderController;
import model.entities.person.ServiceProvider;
import utils.validations.number.CNPJValidation;


/***
 * ServiceProviderFindCNPJ. Similar to ServiceProviderFindId.
 * The print method searches for a Service Provider via its CNPJ,
 * received in the Add part. If found, returns it. 
 * Otherwise throws an error message. 
 * 
 * @version 1.1.0
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 */
public class ServiceProviderFindCNPJ {
	public void print() {
		ServiceProviderController controller = new ServiceProviderController();
		Scanner scan = new Scanner(System.in);

		System.out.println();

		String cnpj = "";
		while(!CNPJValidation.isCnpjValid(cnpj)) {
			System.out.print("Informe o CNPJ do Prestador de Serviço - ");
			cnpj = scan.nextLine();
			if (CNPJValidation.isCnpjValid(cnpj)) {
				break;
			}else {
				System.out.println("CNPJ Inválido");
			}
		}

		System.out.println("=======================");
		ServiceProvider object = (ServiceProvider)controller.findByCNPJ(cnpj);

		if (object != null) {
			System.out.println(object.getName());					
		}else {
			System.out.println("Prestador de Serviço não encontrado");
		}
		System.out.println("=======================");
	}
}
