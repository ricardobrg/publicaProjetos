package com.publica.grupo2sprint3.view.departmentView;

import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;

/**
 * Department Creation View.
 * 
 * It overrides the display method since output and input are mixed.
 * 
 * @version 1.0.0
 * 
 * @author Caio Shimada
 *
 */
public class DepartmentViewCreate extends DepartmentView {

	private static DepartmentViewCreate instance;

	private DepartmentViewCreate(Collaborator collab) {
		super(collab);
	}

	public static DepartmentViewCreate getInstance(Collaborator collab) {
		if (instance == null) {
			instance = new DepartmentViewCreate(collab);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		instance = null;
	}

	/**
	 * Overrides display method to call the create method with the form.
	 * 
	 * @author Caio Shimada
	 */
	@Override
	public void display() {
		create();
	}

	/**
	 * Method to display department creation form and receive user input. It
	 * redirects user to initial department com.publica.grupo2sprint3.view after a successful department
	 * creation but traps the user eternally while the input is invalid.
	 * 
	 * @author Caio Shimada
	 */
	public void create() {
		System.out.print("Nome do Departamento: ");
		String name = Main.scan.nextLine();
		if (name == "")
			name = Main.scan.nextLine();
		
		if (controller.add(name)) {
			System.out.println("Departamento Criado");
			controller.goToInitial();
			
		} else {
			System.out.println("Departamento já existente!");
			System.out.println("1. Tentar Novamente / 0. Voltar");
			
			int input = Main.scan.nextInt();
			switch (input) {
			case 1:
				controller.create();
				break;
			case 0:
				controller.goToInitial();
				break;
			default:
				System.out.println("Opção Inválida. Retornando a Área Inicial");
				controller.goToInitial();
				break;
			}
		}
	}

	@Override
	public String getOutput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void readInput() {
		// TODO Auto-generated method stub

	}

}
