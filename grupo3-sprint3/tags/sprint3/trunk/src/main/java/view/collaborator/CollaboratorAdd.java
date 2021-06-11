package view.collaborator;

import java.util.ArrayList;
import java.util.Scanner;

import controller.RoleController;
import controller.collaborator.CollaboratorController;
import model.entities.person.Collaborator;
import model.entities.security.Role;
import utils.UtilsNumber;
import utils.validations.number.CEPValidation;
import utils.validations.number.CPFValidation;
import utils.validations.number.TelephoneValidation;
import utils.validations.string.EmailValidation;
import utils.validations.string.TextValidation;


/**
 * CollaboratorAdd. This class has a method that adds 
 * a new Collaborator in "newCollaborator" with its information.
 * If the ID is not valid, gives the user another chance to type it right.
 * 
 * @version 1.0.0
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 */
public class CollaboratorAdd {
	
	public void print() {
	
		CollaboratorController colabController = new CollaboratorController();
		
		Scanner scan = new Scanner(System.in);
		String input;
		
		Collaborator colab = new Collaborator();
		
		
		System.out.println("Cadastro de colaborador");
		System.out.println("=======================");
		System.out.println("Nome: ");
		input = scan.nextLine();
		colab.setName(input);
		
		while(!CEPValidation.isCepValid(input)) {
			System.out.println("Cep: ");
			input = scan.nextLine();
			colab.setCep(input);	
		}
		
		while(!EmailValidation.isEmailValid(input)) {
			System.out.println("Email: ");
			input = scan.nextLine();

			colab.setEmail(input);	
		}
		
		while(!TelephoneValidation.isPhoneValid(input)) {
			System.out.println("Telefone: ");
			input = scan.nextLine();

			colab.setPhone(input);
		}
		
		while(!CPFValidation.isCpfValid(input)) {
			System.out.println("CPF: ");
			input = UtilsNumber.onlyNumbers(scan.nextLine());

			colab.setCpf(input);	
		}
		System.out.println("Pis: ");
		input = scan.nextLine();
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
		
		System.out.println("Data de admissão: ");
		scan.nextLine();
		colab.setAdmissionDate(scan.nextLine());
		System.out.println("Carga horaria: ");
		colab.setWorkHours(scan.nextInt());
		System.out.println("Salario: ");
		colab.setSalary(scan.nextDouble());
		System.out.println("Senha: ");
		
		String senha = scan.next();
		while(!TextValidation.isCharactersValid(senha)) {
			System.out.println("Tente Novamente: ");
			senha = scan.next();
		}
		
		colab.setPwdHash(senha);	
		colab.setEndereco(null);
		
		colabController.collaboratorAdd(colab);
	}
}
