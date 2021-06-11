package com.publica.grupo2sprint3.view.pointView;

import com.publica.grupo2sprint3.controller.PointController;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;

/**
 * <br> Point Creation View With Singleton. <br>
 * <br>
 * 
 * It overrides the display method to call the create since Output and Input are mixed.
 * 
 * @version 1.1.0
 * 
 * @author Jessé Amaro <jesse.amaro7@gmail.com>
 */
public class PointViewCreate extends PointView {
	
	public PointViewCreate(Collaborator collab) {
		super(collab);
	}
	
	/**
	 * Displays the Create Point form and sends the input data to the Store
	 * method in the Controller.
	 * 
	 * The dateFormatter method returns a formatted date and time for the 
	 * finalFormatedDate variable.
	 * 
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 */
	@Override
	public void display() {
		String finalFormatedDate = PointController.dateFormatter();
		
		System.out.println("\n--------- Registro de Pontos ---------\n");
		System.out.println("");
		
		System.out.println("> Realizando Novo Registro...");
		System.out.println("O ponto será registrado as: " + finalFormatedDate);
		System.out.println("Confirmar registro?" + " 1. SIM ou 2. NÃO");
		
		//int input2 = Main.scan.nextInt();

//		if (input2 == 1) {
//			com.publica.grupo2sprint3.controller.add();
//
//			System.out.println("-----------------------------");
//			System.out.println("Ponto Registrado com sucesso!");
//			System.out.println("-----------------------------");
//		} else {
//			System.out.println("---------------------");
//			System.out.println("Ponto NÃO registrado!");
//			System.out.println("---------------------");
//		}

		controller.goToInitial();
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