package com.publica.grupo2sprint3.view.roleView;

import java.util.ArrayList;

import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Role;

/**
 * View class related to deleting roles.<br>
 * 
 * Returns lists related to roles and sends the desired option to the com.publica.grupo2sprint3.controller to delete.
 * 
 * @version 1.0.0
 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
 */
public class RoleViewDelete extends RoleView {

	private static RoleViewDelete instance;
	private ArrayList<Role> roles;

	public RoleViewDelete(Collaborator collab, ArrayList<Role> roles) {
		super(collab);
		this.roles = roles;
	}

	public static RoleViewDelete getInstance(Collaborator collab, ArrayList<Role> roles) {
		if (instance == null) {
			instance = new RoleViewDelete(collab, roles);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		instance = null;
	}

	public void display() {
		delete();
	}

	/**
	 * Returns lists related to roles and sends the desired option to the com.publica.grupo2sprint3.controller to delete.
	 * 
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 */
	public void delete() {
		System.out.println("\n--------- Edição de Cargo ---------");

		for (int i = 0; i < roles.size(); i++) {
			System.out.printf("%n%s%3d%s", "Id ", i + 1, ": ");
			System.out.printf("%-26s", roles.get(i).getName());
			System.out.printf("%-26s", roles.get(i).getDepartment().getName());
			System.out.printf("%-26s", roles.get(i).getSal());
		}

		System.out.print("\nDigite o Id do cargo que deseja editar: ");
		int option = Main.scan.nextInt();

		System.out.println();

		if (option > 0 && option <= roles.size()) {

			boolean added = roleController.remove(option);

			if (added) {
				System.out.println("");
				System.out.println("=======================");
				System.out.println("Deletado com Sucesso!");
				System.out.println("=======================");
			} else {
				System.out.println("");
				System.out.println("=======================");
				System.out.println("Deleção não realizada!");
				System.out.println("=======================");
			}
		}
		roleController.goToInitial();
	}

	@Override
	public String getOutput() {
		return null;
	}

	@Override
	protected void readInput() {
	}

}
