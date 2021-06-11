package view.role;

import java.util.Scanner;

import controller.AccessLevelController;
import model.entities.security.AccessLevel;
import model.entities.security.Role;
import controller.RoleController;


/**
 * RoleAdd. 
 * This class has a method that adds a new Role 
 * in "newRole" and saves the Access Level.
 * (Valor: stands for reference tags. E.g.: 1 = Basic)
 * Finally, packs everything up and sends for the controller.  
 * 
 * @version 1.0.0
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 */
public class RoleAdd {
	public void print() {
		Role newRole = new Role();
		Scanner scan = new Scanner(System.in);
		RoleController controller = new RoleController();
		
		System.out.println();
		System.out.print("Informe o Nome do Cargo: ");
		String newRoleName = scan.nextLine();
		newRole.setName(newRoleName);

		System.out.println("Informe o nível de acesso: ");
		AccessLevelController.listAll();

		System.out.print("Valor: ");
		int newRoleAccessLevel = scan.nextInt();
		for (AccessLevel item : AccessLevel.values()) {
			if (item.getValue() == newRoleAccessLevel) {
				newRole.setAccessLevel(item);
			}
		}
		controller.addNewRole(newRole);
	}
}
