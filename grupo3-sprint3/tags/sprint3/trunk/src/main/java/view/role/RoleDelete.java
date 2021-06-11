package view.role;

import java.util.Scanner;

import controller.RoleController;
import model.entities.security.Role;

/**
 * RoleDelete. 
 * Has a method that asks for an ID.
 * Searches for the item on the list of Roles.
 * Then, deletes it.
 * 
 * @version 1.0.0
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 */
public class RoleDelete {
	RoleController controller = new RoleController();
	Scanner scan = new Scanner(System.in);
	
	public void print() {
		System.out.println();
		System.out.println("=======================");
		int i = 0;
		for (Role item : controller.listRoles()) {
			System.out.print(i+" - ");
			System.out.println(item.getName());
			i++;
		}
		System.out.println("=======================");
		
		System.out.print("Informe o ID do objeto que será deletado: ");
		int deletedObject = scan.nextInt();
		controller.deleteRole(deletedObject);
	}
}
