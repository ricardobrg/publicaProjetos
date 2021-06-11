package view.serviceprovider;

import java.util.Scanner;

import controller.RoleController;
import controller.ServiceProviderController;

/**
 * This class is responsible for searching a object by its id (index in array).
 * <br>
 * It uses a controller for comunicate with the database.
 * <br><br>
 * Just like any other page class in the system, this one
 * contains a print method, that show the options in
 * console. 
 * 
 * @version 1.0.0
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 *
 */
public class ServiceProviderFind {
	public void print() {
		ServiceProviderController controller = new ServiceProviderController();
		Scanner scan = new Scanner(System.in);
		
		System.out.println();
		System.out.print("Informe o ID do Provedor de Serviços - ");
		int id = scan.nextInt();

		System.out.println("=======================");
		if (controller.findServiceProvider(id)!= null) {
			System.out.println(controller.findServiceProvider(id).getName());					
		}else {
			System.out.println("Prestador de Serviços não encontrado");
		}
		System.out.println("=======================");
	}
}
