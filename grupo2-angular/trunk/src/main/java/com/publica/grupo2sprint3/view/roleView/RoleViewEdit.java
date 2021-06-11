package com.publica.grupo2sprint3.view.roleView;

import java.util.ArrayList;

import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.dao.DepartmentDAO;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;
import com.publica.grupo2sprint3.model.person.physicalperson.Role;

/**
 * See the class related to editing functions.<br>
 * 
 * Returns lists related to functions and sends the desired option to the com.publica.grupo2sprint3.controller, the com.publica.grupo2sprint3.controller is in charge of editing.
 * 
 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
 */
public class RoleViewEdit extends RoleView {

	private static RoleViewEdit instance;
	private ArrayList<Role> roles;

	private RoleViewEdit(Collaborator collab, ArrayList<Role> roles) {
		super(collab);
		this.roles = roles;
	}

	public static RoleViewEdit getInstance(Collaborator collab, ArrayList<Role> roles) {
		if (instance == null) {
			instance = new RoleViewEdit(collab, roles);
		}
		return instance;
	}

	public static void destroyInstance() {
		instance = null;
	}

	@Override
	public void display() {
		edit();
	}

	/**
	 * Returns the list of roles and sends the desired option for the com.publica.grupo2sprint3.controller to edit.
	 * 
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 */
	private void edit() {
		System.out.println("\n--------- Edi��o de Cargo ---------");

		while(true) {
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

				System.out.print("Nome do Cargo: ");
				Main.scan.nextLine();
				String role = Main.scan.nextLine();
				
				ArrayList<Department> departments = DepartmentDAO.getInstance().getAll();
				
				System.out.printf("%-5s %-25s %-25s\n", "Id", "Gerente", "Departamento");
				for (int i = 0; i < departments.size(); i++) {
					try {
					} catch (Exception e) {
						System.out.printf("%-5d %-25s %-25s\n", i+1, "null", ((Department) departments.get(i)).getName());
					}

				}
				
				System.out.print("Id do Departamento: ");
				int departIndex = Main.scan.nextInt()-1;
				Department department = (Department) DepartmentDAO.getInstance().findById(departIndex);
				
				System.out.print("\nSal�rio: ");
				double salary = Main.scan.nextDouble();

				boolean added = roleController.updateRole(option, new Role(role, department, salary));
				

				
				if (added) {
					System.out.println("");
					System.out.println("=======================");
					System.out.println("Editado com Sucesso!");
					System.out.println("=======================");
				} else {
					System.out.println("");
					System.out.println("=======================");
					System.out.println("Edi��o n�o realizada!");
					System.out.println("=======================");
				}
				break;
			}else {
				System.out.println();
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
