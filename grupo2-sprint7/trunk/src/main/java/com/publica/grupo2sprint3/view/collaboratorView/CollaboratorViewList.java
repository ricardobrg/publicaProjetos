package com.publica.grupo2sprint3.view.collaboratorView;

import java.util.ArrayList;

import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;

/**
 * Collaborator List View with Singleton.
 * 
 * It displays the Collaborators list by Access Level. <br>
 * For ADVANCED Access Level users, it displays the logged in user's Department.
 * <br>
 * For TOTAL Access Level users, it displays all the existent users.
 * 
 * @version 1.0.0
 * 
 * @author Caio Shimada
 *
 */
public class CollaboratorViewList extends CollaboratorView {

	private static CollaboratorViewList instance;
	private ArrayList<Collaborator> collabs;

	private CollaboratorViewList(Collaborator collab, ArrayList<Collaborator> collabs) {
		super(collab);
		this.collabs = collabs;
	}

	public static CollaboratorViewList getInstance(Collaborator collab, ArrayList<Collaborator> collabs) {
		if (instance == null) {
			instance = new CollaboratorViewList(collab, collabs);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		instance = null;
	}

	/**
	 * Constructs the output list with the users to be displayed.
	 * 
	 * @author Caio Shimada
	 * 
	 * @return output the string ready to be printed
	 */
	@Override
	public String getOutput() {
		String output = "";

		if (collabs.size() > 0)
			output += String.format("%-5s %-25s %-25s %-25s\n", "Id", "Nome", "CPF", "Cargo");
		else
			return "Nenhum Colaborador Encontrado!";

		for (int i = 0; i < collabs.size(); i++) {
			output += String.format("%-5d %-25s %-25s %-25s\n", i, collabs.get(i).getName(), collabs.get(i).getCpf(),
					collabs.get(i).getRole().getName());
		}

		return output;
	}

	/**
	 * Reads the selected user in the list as well as what option the user would
	 * like.
	 * 
	 * It has options for Visualize Collaborator data, Edit Collaborator data,
	 * Delete Collaborator and return to initial Collaborator View.
	 * 
	 * @author Caio Shimada
	 */
	@Override
	protected void readInput() {
		System.out.print("Selecione um Colaborador: ");
		int input = Main.scan.nextInt();

		System.out.println("1. Visualizar / 2. Editar / 3. Deletar / 0. Voltar");
		int option = Main.scan.nextInt();

		switch (option) {
		case 1:
			controller.show(input);
			break;
		case 2:
			if (access == AccessLevel.TOTAL)
				controller.edit(collabs.get(input).getCpf());
			else
				System.out.println("Você Não Tem Permissão");
			break;
		case 3:
			if (access == AccessLevel.TOTAL) {
				if (controller.remove(collabs.get(input).getCpf())) {
					System.out.println("Collaborador deletado");
					controller.goToInitial();
				} else {
					System.out.println("Não foi possível deletar");
					controller.goToInitial();
				}
			}
		case 4:
			controller.goToInitial();
		default:
			System.out.println("Opção Inválida");
			controller.goToInitial();
			break;
		}

	}

}
