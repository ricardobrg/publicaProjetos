package view.role;

import controller.RoleController;
import model.entities.security.Role;

/***
 * RoleListAll. 
 * Lists all Roles, Access Levels and IDs. 
 * Which is an ArrayList of Service Providers, in Controllers.
 * Otherwise throws an error message. 
 * 
 * @version 1.0.0
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 */
public class RoleListAll {
	
	public void print() {
		RoleController controller = new RoleController();
		
		System.out.println();
		System.out.println("=======================");
		int i = 0;
		
		for (Role item : controller.listRoles()) {
			if (item != null) {
				System.out.print(i+" - ");
				System.out.print(item.getName()+" - ");
				System.out.println(item.getAccessLevel());
				i++;	
			}else {
				System.out.println("Nenhum cargo Cadastrado!");
			}
		}
		System.out.println("=======================");
	}
}
