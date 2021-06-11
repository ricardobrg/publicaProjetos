package com.publica.grupo2sprint3.view.vacationView;

import java.util.ArrayList;

import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;

/**
 * View to List Collaborators with their vacation info.
 * 
 * @version 1.0.0
 * 
 * @author Diego Borba
 * @author Caio Shimada
 *
 */
public class VacationViewList extends VacationView {

	private static VacationViewList instance;
	private ArrayList<Collaborator> collabs;

	private VacationViewList(Collaborator collab, ArrayList<Collaborator> collabs) {
		super(collab);
		this.collabs = collabs;
	}

	public static VacationViewList getInstance(Collaborator collab, ArrayList<Collaborator> collabs) {
		if (instance == null) {
			instance = new VacationViewList(collab, collabs);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		instance = null;
	}

	/***
	 * Method to generate an output from the list of collaborators
	 * 
	 * @author Diego Borba
	 * @author Caio Shimada
	 * 
	 * @return output(String)
	 */
	@Override
	public String getOutput() {
		String output = "";

		if (collabs.size() > 0)
			output += String.format("%-5s %-25s %-25s %-25s %-25s \n", "Id", "Nome", "Pode tirar férias?",
					"Está de férias?", "Tempo de Férias");
		else
			return "Nenhum Colaborador Encontrado!";

		for (int i = 0; i < collabs.size(); i++) {
			String vacation = collabs.get(i).getCanVacation() ? "Pode" : "Não pode";
			String inVacation = collabs.get(i).getInVacation() ? "Está" : "Não está";
			output += String.format("%-5d %-25s %-25s %-25s  %-25s \n", i + 1, collabs.get(i).getName(), vacation,
					inVacation, collabs.get(i).getVacationSize());
		}

		return output;
	}

	/**
	 * Prompts user to select a Collaborator if he has enough access level to modify
	 * its vacation info.
	 * 
	 * @author Caio Shimada
	 */
	@Override
	protected void readInput() {

		if (access != AccessLevel.TOTAL) {
			controller.goToInitial();
		}

		System.out.print("Selecione um Colaborador: ");
		int input = Main.scan.nextInt();

		System.out.println("1. Editar / 0. Voltar");
		int option = Main.scan.nextInt();

		switch (option) {
		case 1:
			controller.edit(input-1);
			break;
		case 0:
			controller.goToInitial();
			break;
		default:
			System.out.println("Opção Inválida");
			controller.goToInitial();
			break;
		}
	}

}
