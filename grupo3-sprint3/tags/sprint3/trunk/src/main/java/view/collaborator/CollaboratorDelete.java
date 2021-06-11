package view.collaborator;

import controller.collaborator.CollaboratorController;

/***
 * CollaboratorDelete. 
 * Has a method that asks for a CPF.
 * Searches for the item on the list which the CPF matches.
 * Then, deletes it.
 * 
 * @version 1.0.0
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 */

public class CollaboratorDelete {

	private String cpf;
	
	public CollaboratorDelete(String cpf) {
		this.cpf = cpf;
	}
	
	public void print() {
		
		CollaboratorController colabController = new CollaboratorController();

		System.out.println(colabController.collaboratorDelete(cpf));
		
	}
}
