package com.publica.grupo2sprint3.view.departmentView;

import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;

/**
 * Department Edit View.
 * 
 * It overrides the display method since output and input are mixed.
 * 
 * @version 1.0.0
 * 
 * @author Caio Shimada
 *
 */
public class DepartmentViewEdit extends DepartmentView{

	private static DepartmentViewEdit instance;
	private Department department;
	
	private DepartmentViewEdit(Collaborator collab, Department department) {
		super(collab);
		this.department = department;
	}
	
	public static DepartmentViewEdit getInstance(Collaborator collab, Department department) {
		if (instance == null) {
			instance = new DepartmentViewEdit(collab, department);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		instance = null;
	}
	
	/**
	 * Overrides method to call the edit method.
	 * 
	 * @author Caio Shimada
	 */
	@Override
	public void display() {
		edit();
	}
	
	/**
	 * Displays the form to edit the department and receives user input to update it.
	 * 
	 * Blank input keeps the current data.
	 * 
	 * @author Caio Shimada
	 */
	private void edit() {
		System.out.println("\nEditando Departamento. Deixe em branco para manter o dado.");
		System.out.println("\nNome do Departamento Atual: " + department.getName());
		System.out.print("Novo nome: ");
		Main.scan.nextLine();
		String name = Main.scan.nextLine();
		if (name == "")
			name = department.getName();
		
		try {
		} catch(Exception e) {
			System.out.println("\nN�o h� Gerente Associado!");
		}
		
		System.out.print("\nCPF do Novo Gestor: ");
		String manager = Main.scan.nextLine();
		if (manager == "")
			try {
			} catch (Exception e) {
				manager = "";
			}
		
		if (controller.update(department.getName(), name, manager)) {
			System.out.println("\nDepartamento Atualizado");
			controller.goToInitial();
			
		} else {
			System.out.println("\nN�o foi poss�vel atualizar o Departamento. Verifique as entradas.\n");
			System.out.println("1. Tentar Novamente / 0. Voltar");
			
			int input = Main.scan.nextInt();
			switch (input) {
			case 1:
				controller.edit(department.getName());
				break;
			case 0:
				controller.goToInitial();
				break;
			default:
				System.out.println("Op��o Inv�lida. Retornando a �rea Inicial");
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
