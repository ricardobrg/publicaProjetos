package com.publica.grupo2sprint3.view.vacationView;

import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;

/**
 * View to edit the Vacation info of a selected Collaborator.
 * 
 * @version 1.0.0
 * 
 * @author Diego Borba
 * @author Caio Shimada
 *
 */
public class VacationViewEdit extends VacationView{

	private static VacationViewEdit instance;
	private Collaborator collabFound;
	
	private VacationViewEdit(Collaborator collab, Collaborator collabFound) {
		super(collab);
		this.collabFound = collabFound;
	}
	
	public static VacationViewEdit getInstance(Collaborator collab, Collaborator collabFound) {
		if (instance == null) {
			instance = new VacationViewEdit(collab, collabFound);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		instance = null;
	}
	
	/**
	 * Overwrites diplay method since output and input are mixed.
	 * 
	 * @author Caio Shimada
	 */
	@Override
	public void display() {
		edit();
	}
	
	/**
	 * Edit method that displays form and reads user input while validating each time.
	 * 
	 * Calls the update method in the com.publica.grupo2sprint3.controller to update the vacation info.
	 * 
	 * @author Diego Borba
	 * @author Caio Shimada
	 */
	private void edit() {
		String[] returned = new String[3];

		while(true) {
			System.out.print("O colaborador pode tirar férias?(s/n):");
			Main.scan.nextLine();
			returned[0] = (Main.scan.nextLine());
			if (returned[0].equals("s") || returned[0].equals("n"))	break;
			System.out.println("Opção inválida");
		}

		while(true) {
			System.out.print("O colaborador está de férias?(s/n):");
			returned[1] = (Main.scan.nextLine());
			if (returned[1].equals("s") || returned[1].equals("n"))	break;
			System.out.println("Opção inválida");
		}

		while(true) {
			System.out.print("Quanto tempo o calaborador tem/terá de férias?(30 ou 24 ou 18 dias):");
			returned[2] = (Main.scan.nextLine());
			if (returned[2].equals("30") || returned[2].equals("24") || returned[2].equals("18"))	break;
			System.out.println("Opção inválida");
		}

		boolean updated = controller.update(collabFound.getCpf(), returned);
		
		if (updated) {
			System.out.println("\nFérias Atualizadas");
			controller.goToInitial();
		} else {
			System.out.println("\nNão Foi Possível Atualizar as Férias");
			controller.goToInitial();
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
