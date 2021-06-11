package com.publica.grupo2sprint3.view.collaboratorView;

import java.util.ArrayList;

import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Cep;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Role;

/**
 * Collaborator Creation View With Singleton. 
 * 
 * It overrides the display method to call the create since Output and Input are mixed.
 * 
 * @version 1.0.1
 * 
 * @author Caio Shimada
 *
 */
public class CollaboratorViewCreate extends CollaboratorView{
	
	private static CollaboratorViewCreate instance;
	private static Contact contact;
	private static Address address;

	private CollaboratorViewCreate(Collaborator collab) {
		super(collab);
	}
	
	public static CollaboratorViewCreate getInstance(Collaborator collab) {
		if (instance == null) {
			instance = new CollaboratorViewCreate(collab);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		instance = null;
	}
	
	/**
	 * Overrides the default display method. Calls the create method.
	 * 
	 * @author Caio Shimada
	 */
	@Override
	public void display() {
		create();
	}
	
	/**
	 * Displays the Create Collaborator form and sends the input data to the Store
	 * method in the Controller.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * @author Augusto Kalahary <AuuKalaharyKW@gmail.com>
	 */
	private void create() {
		System.out.println("Formulário de Cadastro");

		System.out.print("Nome do Colaborador: ");
		Main.scan.nextLine();
		String name = Main.scan.nextLine();

		System.out.print("Email do Colaborador: ");
		String email = Main.scan.nextLine();

		System.out.print("CPF: ");
		String cpf = Main.scan.nextLine();

		System.out.print("Telefone: ");
		String phone = Main.scan.nextLine();

		System.out.print("CEP: ");
		String cep = Main.scan.nextLine();

		System.out.print("PIS: ");
		String pis = Main.scan.nextLine();
		
		System.out.printf("Selecionar cargo: \n%-5s %-20s %-20s\n", "Id", "Cargo", "Departamento");
		ArrayList<Role> roles = controller.getRoles();
		for (int i = 0; i < roles.size(); i++) {
			System.out.printf("%-5d %-20s %-20s\n", i, roles.get(i).getName(), roles.get(i).getDepartment().getName());
		}
		int roleId = Main.scan.nextInt();
		Role role = roles.get(roleId);
		
		System.out.print("Salário Extra: ");
		double salary = Main.scan.nextDouble();
		
		System.out.print("Carga Horária Diária: ");
		int workload = Main.scan.nextInt();

		contact = new Contact(phone, email);
		address = new Address(Cep.getInstance(cep));
		boolean added = controller.add(name, contact, address, cpf, pis, role, salary, workload);
		
		if (added) {
			System.out.println("Colaborador Cadastrado");
			controller.goToInitial();
			
		}else {
			System.out.println("\nNão foi possível cadastrar o Colaborador. Verifique as entradas e tente novamente.\n");
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
