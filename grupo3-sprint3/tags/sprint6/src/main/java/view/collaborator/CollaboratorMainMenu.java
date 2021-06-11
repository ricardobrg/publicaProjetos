package view.collaborator;

import java.util.ArrayList;

import com.google.gson.JsonObject;

import controller.CollaboratorController;
import model.entities.security.AccessLevel;
import view.AbstractMenu;
import view.console.Console;

/**
 * This class provides a menu containing various 
 * options for managing Collaborator objects.
 * Just like any other page class in the system, this one
 * contains a print method, that shows the options in
 * console.
 * 
 * @version 1.5.0
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 * @author Caio Shimada
 *
 */
public class CollaboratorMainMenu extends AbstractMenu {

	public CollaboratorMainMenu(JsonObject dataLogin) {
		super(dataLogin);
	}

	CollaboratorController collabController = new CollaboratorController();

	public void print() {

		Console.printHeader("MENU - COLABORADOR");

		ArrayList<String> options = new ArrayList<String>();

		System.out.println(this.getAccessLevel().getValue());

		if(this.getAccessLevel().getValue() > 0) {
			options.add("Voltar");
			options.add("Buscar por CPF");
		}
		if(this.getAccessLevel().getValue() > 1){
			options.add("Prestador de Serviços");
			options.add("Adicionar");
			options.add("Listar");
			options.add("Editar por CPF");
			options.add("Excluir por CPF");
		}
		if(this.getAccessLevel().getValue() > 2) {
			options.add("Cargo");
		}

		Console.printOptions(options);
		
		int optionIndex = Console.getInputNumber("Selecione uma opção - ", 0, options.size(), "Opção inválida.");

		switch (optionIndex) {
		case 0: 
			System.exit(0);
		case 1: 
			break;
		case 2:
			if(super.getAccessLevel().equals(AccessLevel.BASIC)) {
				System.out.println("Opção Inválida!");
			}
			else
				collabController.create();
			this.print();
			break;
		case 3:
			if(!super.getAccessLevel().equals(AccessLevel.BASIC))
				collabController.list();
			this.print();
			break;
		case 4:
			if(!super.getAccessLevel().equals(AccessLevel.BASIC)) {
				String cpf = Console.getCPF("CPF: ");
				JsonObject cpfJson = new JsonObject();
				cpfJson.addProperty("cpf", cpf);	
				collabController.show(cpfJson);
			}
			else {
				JsonObject cpfJson = new JsonObject();
				cpfJson.addProperty("cpf", super.getCpf());
				collabController.show(cpfJson);
			}
			this.print();
			break;

		case 5:
			if(!super.getAccessLevel().equals(AccessLevel.BASIC)){
				String cpf = Console.getCPF("CPF: ");
				JsonObject cpfJson = new JsonObject();
				cpfJson.addProperty("cpf", cpf);	
				collabController.edit(cpfJson);
			}
			else {
				JsonObject cpfJson = new JsonObject();
				cpfJson.addProperty("cpf", super.getCpf());
				collabController.edit(cpfJson);
			}
			this.print();
			break;

		case 6:
			if(!super.getAccessLevel().equals(AccessLevel.BASIC))
				collabController.remove();
			else
				System.out.println("\n\nSeu nível de acesso não permite deleções.");
			this.print();
			break;
		}
	}
}
