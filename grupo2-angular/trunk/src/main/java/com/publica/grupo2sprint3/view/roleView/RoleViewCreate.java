package com.publica.grupo2sprint3.view.roleView;

import java.util.ArrayList;

import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.dao.DepartmentDAO;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;

/**
 * This View Class has the methods to show the Create New Role Menu.
 * 
 * @version 1.0.0
 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
 * @author Jessé Amaro <jesse.amaro7@gmail.com>
 * @author Caio Shimada <xcaiosr@gmail.com>
 */
public class RoleViewCreate extends RoleView {

	private static RoleViewCreate instance;

	private RoleViewCreate(Collaborator collab) {
		super(collab);
	}

	public static RoleViewCreate getInstance(Collaborator collab) {
		if (instance == null) {
			instance = new RoleViewCreate(collab);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		instance = null;
	}

	@Override
	public void display() {
		create();
	}

	/**
	 * Displays the Create Collaborator form and sends the input data to the Store
	 * method in the Controller.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * @author Jessé Amaro
	 * @author Jonathas Rocha
	 */
	private void create() {
		System.out.println("\n--------- Registro de Cargo ---------");

		System.out.print("Nome do Cargo: ");
		Main.scan.nextLine();
		String role = Main.scan.nextLine();
		
		
		System.out.printf("%-5s %-20s \n", "Id", "Departamento");
		ArrayList<Department> departments = DepartmentDAO.getInstance().getAll();
		for (int i = 0; i < departments.size(); i++) {
			System.out.printf("%-5d %-20s \n", i+1, departments.get(i).getName());
		}
		System.out.print("Selecionar departamento: ");
		int departmentId = Main.scan.nextInt()-1;
		Department department = departments.get(departmentId);

		System.out.print("Salário: ");
		double salary = Main.scan.nextDouble();

		boolean added = roleController.inputRole(role, department, salary);

		if (added) {
			System.out.println("");
			System.out.println("=======================");
			System.out.println("Registrado com Sucesso!");
			System.out.println("=======================");
		} else {
			System.out.println("");
			System.out.println("=======================");
			System.out.println("Registro não realizado!");
			System.out.println("=======================");
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
