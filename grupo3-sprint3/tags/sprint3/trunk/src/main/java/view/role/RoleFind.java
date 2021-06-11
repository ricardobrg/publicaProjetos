package view.role;

import java.util.Scanner;

import controller.RoleController;

/***
 * RoleFind.
 * The print method searches for Role information
 * via its ID (automatically inserted in Add part).
 * If found, returns it. 
 * Otherwise throws an error message. 
 * 
 * @version 1.0.0
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
*/

public class RoleFind {
	public void print() {
		RoleController controller = new RoleController();
		Scanner scan = new Scanner(System.in);
		
		System.out.println();
		System.out.print("Informe o ID do cargo - ");
		int id = scan.nextInt();

		System.out.println("=======================");
		if (controller.findRole(id)!= null) {
			System.out.println(controller.findRole(id).getName());					
		}else {
			System.out.println("Cargo não encontrado");
		}
		System.out.println("=======================");
	}
}
