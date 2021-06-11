package view.collaborator;

import java.util.ArrayList;
import java.util.Scanner;

import controller.RoleController;
import controller.collaborator.CollaboratorController;
import model.entities.person.Collaborator;
import model.entities.security.Role;


/**
 * CollaboratorUpdate. 
 * This class implements an edit (update) method. 
 * It asks the user for a modification.
 * After that, asks for an valid ID.
 * Then, sends the information to the controller.
 * 
 * @version 1.0.0
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 */
public class CollaboratorUpdate {

	private String cpf;

	public CollaboratorUpdate(String cpf) {
		this.cpf = cpf;
	}

	public void print() {

		CollaboratorController colabController = new CollaboratorController();

		Collaborator colab = colabController.collaboratorFind(cpf);

		Scanner scan = new Scanner(System.in);

		System.out.println("==========================");
		System.out.println("--- Editar Colaborador ---");
		System.out.println("--- Anterior ---");
		System.out.println("\nNome: " + colab.getName());
		System.out.println("--- Novo ---");
		System.out.print("Nome: ");
		colab.setName(scan.nextLine());

		System.out.println("--- Anterior ---");
		System.out.println("\nCep: " + colab.getCep());
		System.out.println("--- Novo ---");
		System.out.print("Cep: ");
		colab.setCep(scan.nextLine());

		System.out.println("--- Anterior ---");
		System.out.println("\nEmail: " + colab.getEmail());
		System.out.println("--- Novo ---");
		System.out.print("Email: ");
		colab.setEmail(scan.nextLine());

		System.out.println("--- Anterior ---");
		System.out.println("\nTelefone: " + colab.getPhone());
		System.out.println("--- Novo ---");
		System.out.print("Telefone: ");
		colab.setPhone(scan.nextLine());

		colab.setCpf(cpf);

		System.out.println("--- Anterior ---");
		System.out.println("\nPis: " + colab.getPis());
		System.out.println("--- Novo ---");
		System.out.print("Pis: ");
		colab.setPis(scan.nextLine());

		System.out.println("--- Anterior ---");
		System.out.println("\nCargo: " + colab.getRole().getName());
		System.out.println("--- Novo ---");
		System.out.println("==== Cargo ====");
		System.out.println("ID === Cargo: ");
		
		RoleController rController = new RoleController();
		ArrayList<Role> roles = rController.listRoles();

		int i = 0;

		for(i = 0; i < roles.size(); i++) {
			System.out.println(i + "      " + roles.get(i).getName());
		}

		System.out.println("Informe o ID da role: ");

		Role role = new Role();

		int id = scan.nextInt();

		while(!(id>=0 && id < i)) {
			System.out.println("Informe um ID válido: ");
			id = scan.nextInt();
		}
		role = roles.get(id);
		
		colab.setRole(role);
		
		System.out.println("--- Anterior ---");
		System.out.println("\nAdmission Date: " + colab.getAdmissionDate());
		System.out.println("--- Novo ---");
		System.out.print("Nome: ");
		colab.setAdmissionDate(scan.nextLine());

		System.out.println("--- Anterior ---");
		System.out.println("\nHoras de Trabalho: " + colab.getWorkHours());
		System.out.println("--- Novo ---");
		System.out.print("Horas de Trabalho: ");
		colab.setWorkHours(scan.nextInt());

		System.out.println("--- Anterior ---");
		System.out.println("\nSalario: " + colab.getSalary());
		System.out.println("--- Novo ---");
		System.out.print("Salario: ");
		colab.setSalary(scan.nextDouble());
		
		colab.setEndereco(null);
		
		System.out.println(colabController.collaboratorUpdate(cpf, colab));

	}
}
