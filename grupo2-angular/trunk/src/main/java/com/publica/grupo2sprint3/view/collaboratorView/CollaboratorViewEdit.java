package com.publica.grupo2sprint3.view.collaboratorView;

import java.util.ArrayList;

import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Cep;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Role;

/**
 * Collaborator Edit View with Singleton.
 * 
 * It overrides the default display method and calls the edit method instead
 * since Output and Input are mixed.
 * 
 * The current data is displayed as the form is filled.
 * 
 * @version 1.0.0
 * 
 * @author Caio Shimada
 *
 */
public class CollaboratorViewEdit extends CollaboratorView {

	private static CollaboratorViewEdit instance;
	private Collaborator collabFound;
	private Contact contact;
	private Address address;
	private Cep cep;

	private CollaboratorViewEdit(Collaborator collab, Collaborator collabFound) {
		super(collab);
		this.collabFound = collabFound;
	}

	public static CollaboratorViewEdit getInstance(Collaborator collab, Collaborator collabFound) {
		if (instance == null) {
			instance = new CollaboratorViewEdit(collab, collabFound);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		instance = null;
	}

	/**
	 * Overrides the default display method and calls the edit method.
	 * 
	 * @author Caio Shimada
	 */
	@Override
	public void display() {
		edit();
	}

	/**
	 * Displays the edit form and read input data.
	 * 
	 * The current data in each variable is displayed as the form is filled.
	 * 
	 * Blank data is understood as unchanged data.
	 * 
	 * @author Caio Shimada
	 */
	private void edit() {
		System.out.println("\nFormulário de Edição. Deixe em branco mara manter,");

		System.out.println("\nNome do Colaborador Atual: " + collabFound.getName());
		System.out.print("Novo Nome: ");
		Main.scan.nextLine();
		String name = Main.scan.nextLine();
		if (name == "")
			name = collabFound.getName();

		System.out.println("\nEmail para Contato Atual: " + collabFound.getContact().getEmail());
		System.out.print("Novo Email: ");
		String email = Main.scan.nextLine();
		if (email == "")
			email = collabFound.getContact().getEmail();

		System.out.println("\nCPF Atual: " + collabFound.getCpf());
		System.out.print("Novo CPF: ");
		String cpf = Main.scan.nextLine();
		if (cpf == "")
			cpf = collabFound.getCpf();

		System.out.println("\nTelefone Atual: " + collabFound.getContact().getPhone());
		System.out.print("Novo Telefone: ");
		String phone = Main.scan.nextLine();
		if (phone == "")
			phone = collabFound.getContact().getPhone();

		System.out.println("\nCEP Atual: " + collabFound.getAddress().getCep());
		System.out.print("Novo Cep: ");
		String cep = Main.scan.nextLine();
		this.cep = Cep.getInstance(cep);
		if (this.cep.getCep() == "")
			this.cep.getCep().toString().equals(collabFound.getAddress().getCep().toString());

		System.out.println("\nPIS Atual: " + collabFound.getPis());
		System.out.print("Novo Pis: ");
		String pis = Main.scan.nextLine();
		if (pis == "")
			pis = collabFound.getPis();

		System.out.println("\nCargo Atual: " + collabFound.getRole().getName());
		System.out.println("Selecione o Novo Cargo (-1 para manter): ");
		System.out.printf("%-5s %-20s %-20s\n", "Id", "Cargo", "Departamento");
		ArrayList<Role> roles = controller.getRoles();
		for (int i = 0; i < roles.size(); i++) {
			System.out.printf("%-5d %-20s %-20s\n", i, roles.get(i).getName(), roles.get(i).getDepartment().getName());
		}
		System.out.print("Id: ");
		int roleId = Main.scan.nextInt();
		Role role;
		if (roleId == -1) {
			role = collabFound.getRole();
		} else {
			role = roles.get(roleId);
		}

		System.out.println("\nSalário Extra Atual: " + collabFound.getExtraSalary());
		System.out.print("Novo Salário Extra (-1 para manter): ");
		double salary = Main.scan.nextDouble();
		if (salary == -1)
			salary = collabFound.getExtraSalary();

		System.out.println("\nCarga Horária Diária Atual: " + collabFound.getWorkHours());
		System.out.print("Nova Carga Horária Diária (-1 para manter): ");
		int workload = Main.scan.nextInt();
		if (workload == -1)
			workload = collabFound.getWorkHours();

		contact = new Contact(phone, email);
		address = new Address(this.cep);
		boolean added = controller.update(collabFound.getCpf(), name, contact, address, cpf, pis, role,
				collabFound.getAdmissionDate(), salary, workload);

		if (added) {
			System.out.println("\nColaborador Atualizado");
			controller.goToInitial();
			
		} else {
			System.out.println("\nNão foi possível atualizar o Colaborador. Verifique as entradas.");
			System.out.println("1. Tentar Novamente / 0. Voltar");
			
			int input = Main.scan.nextInt();
			switch (input) {
			case 1:
				controller.edit(collabFound.getCpf());
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
