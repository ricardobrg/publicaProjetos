package com.publica.grupo2sprint3.view.departmentView;

import java.util.ArrayList;

import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;

/**
 * View to show List of Departments.
 * 
 * @version 1.0.0
 * 
 * @author Caio Shimada
 *
 */
public class DepartmentViewList extends DepartmentView {

	private static DepartmentViewList instance;
	private ArrayList<Department> departments;

	private DepartmentViewList(Collaborator collab, ArrayList<Department> departments) {
		super(collab);
		this.departments = departments;
	}

	public static DepartmentViewList getInstance(Collaborator collab, ArrayList<Department> departments) {
		if (instance == null) {
			instance = new DepartmentViewList(collab, departments);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		instance = null;
	}

	/**
	 * Sets the string with the list to display and returns it.
	 * 
	 * The table shows the name of the manager and the name of the department.
	 * 
	 * @author Caio Shimada
	 * 
	 * @return the string of the list ready to be printed
	 */
	@Override
	public String getOutput() {
		String output = "";

		output += String.format("%-5s %-25s %-25s\n", "Id", "Gerente", "Departamento");
		for (int i = 0; i < departments.size(); i++) {
			try {
				output += String.format("%-5d %-25s %-25s\n", i+1, departments.get(i).getManager().getName(),
						departments.get(i).getName());
			} catch (Exception e) {
				output += String.format("%-5d %-25s %-25s\n", i+1, "null", departments.get(i).getName());
			}

		}
		return output;
	}

	/**
	 * Reads the selected department to take action on.
	 * 
	 * The user can select a department to edit it, which will redirect him to the
	 * edit page, or to delete it.
	 * 
	 * @author Caio Shimada
	 */
	@Override
	protected void readInput() {
		System.out.print("Selecione um Departamento: ");
		int input = Main.scan.nextInt();

		System.out.println("1. Editar / 2. Deletar / 0. Voltar");
		int option = Main.scan.nextInt();

		switch (option) {
		case 1:
			controller.edit(input);
			break;
		case 2:
			if (controller.remove(input)) {
				System.out.println("Departamento deletado");
				controller.goToInitial();
			} else {
				System.out.println("Não foi possível deletar esse departamento");
				controller.goToInitial();
			}
			break;
		case 0:
			controller.goToInitial();
			break;
		default:
			System.out.println("Opção Inválida");
			controller.goToInitial();
			break;
		}

	}

}
