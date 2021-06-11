package view.role;

import java.util.Scanner;

import controller.RoleController;
import model.dao.AccessLevelDAO;
import model.entities.security.AccessLevel;
import model.entities.security.Role;


/**
 * RoleEdit. This class implements an edit method. 
 * It receives attributes from Role and asks the user for new inputs.
 * Then, sends the information to the controller.
 * 
 * @version 1.0.0
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 */
public class RoleEdit {
	
	AccessLevel acc = new AccessLevelDAO().findObject();
	
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
		System.out.println();

		System.out.print("ID do colaborador que será editado - ");
		int editedId = scan.nextInt();
		
		Role oldRole, editedRole = new Role();
		
		oldRole = controller.findRole(editedId);
		
		System.out.println("Nome antigo - "+oldRole.getName());
		System.out.print("Novo nome - ");
		scan.nextLine();
		String newName = scan.nextLine();
		editedRole.setName(newName);
		System.out.println("Nível de Acesso Antigo - "+oldRole.getAccessLevel());
		System.out.println();
		for (AccessLevel item : AccessLevel.values()) {
			System.out.println(item+" - "+item.getValue());
		}
		System.out.print("Selecione um novo Nível de Acesso:");
		int newRoleAccessLevel = scan.nextInt();
		
		for (AccessLevel item : AccessLevel.values()) {
			if (item.getValue() == newRoleAccessLevel) {
				editedRole.setAccessLevel(item);				
			}
		}
		controller.editRole(editedRole, editedId);
	}
}
