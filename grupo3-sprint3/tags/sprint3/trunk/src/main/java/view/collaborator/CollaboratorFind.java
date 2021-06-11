package view.collaborator;

import controller.collaborator.CollaboratorController;
import model.entities.person.Collaborator;
import utils.UtilsNumber;

/***
 * CollaboratorFind.
 * The print method searches for Collaborator information
 * via its CPF, received in the Add part. If found, returns it. 
 * Otherwise throws an error message. 
 * 
 * @version 1.0.0
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 */
public class CollaboratorFind {

	private String cpf;

	public CollaboratorFind(String cpf) {
		this.cpf = UtilsNumber.onlyNumbers(cpf);
	}

	public void print() {

		CollaboratorController colabController = new CollaboratorController();

		Collaborator colab = colabController.collaboratorFind(this.cpf);

		if(colab != null) {

			System.out.println("===================================");

			System.out.println("Nome:       "+colab.getName());
			if(colab.getCep() != null) {
				System.out.println("CEP:        "+colab.getCep());
			}
			System.out.println("Email:      "+colab.getEmail());
			System.out.println("Telefone:   "+colab.getPhone());
			System.out.println("CPF:        "+colab.getCpf());
			System.out.println("PIS:        "+colab.getPis());
			System.out.println("Cargo:      "+colab.getRole().getName());
			System.out.println("D.admissao: "+colab.getAdmissionDate());
			System.out.println("C. Horaria: "+colab.getWorkHours());
			System.out.println("Salario:    "+colab.getSalary());
		}else {
			System.out.println("CPF não cadastrado!!!");
		}
	}
}
