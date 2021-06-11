package com.publica.grupo2sprint3.view.pointView;

import java.util.ArrayList;

import com.publica.grupo2sprint3.controller.CollaboratorController;
import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.controller.PointController;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Point;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;

/**
 * <br> Point List by Departament View Class <br> 
 * <br> 
 * 
 * This class calls the controllers related to list the points registry by department.
 * 
 * First, a list of all employees in the sector is displayed, identified by an ID. 
 * The ID is assigned by the list index and the collaborator is selected using his ID.
 * 
 * After selection, all points of the selected employee are displayed.
 * 
 * @version 1.1.0
 * 
 * @author Jess� Amaro <jesse.amaro7@gmail.com> 
 */
public class PointViewListDepartment extends PointView {
	
	public PointViewListDepartment(Collaborator collab) {
		super(collab);
	}

	public void display() {
		if (access.id <= AccessLevel.ADVANCED.id) {
			System.out.println("Consultando Registros por Setor...");
			System.out.println("Lista de Colaboradores do setor:  ");
			System.out.println("");
			ArrayList<Collaborator> allCollabsDepartment 
				= CollaboratorController.getInstance(collab).getCollabs();

			for (int i = 0; i < allCollabsDepartment.size(); i++) {
				System.out.println("Colab N�-" + (i + 1));
				System.out.println("Colaborador:   " + allCollabsDepartment.get(i).getName());
				System.out.println(
						"Departamento:  " + allCollabsDepartment.get(i).getRole().getDepartment().getName());
				System.out.println("");
			}

			System.out.println("Informe o ID do colaborador para consultar:");
			int inputID = Main.scan.nextInt();

			Collaborator colabFound = allCollabsDepartment.get(inputID - 1);
			System.out.println("Colaborador Selecionado: " + colabFound.getName());
			System.out.println("");

			System.out.println("Deseja consultar os pontos deste colaborador?" + " 1. SIM ou 2. N�O ");
			int inputC = Main.scan.nextInt();

			if (inputC == 1) {
				ArrayList<Point> points = PointController.getInstance(colabFound).getAllCollabsPoint(colabFound);

				System.out.println("================================");
				String printPoints = "";
				for (int i = 0; i < points.size(); i++) {
					Point point = points.get(i);
					String id = String.valueOf("Registro 0" + (i + 1));
					printPoints = printPoints + id + " - " + point.getDate() + " - " + point.convertDayMinute()
							+ "\n";
				}

				System.out.println(printPoints);
				System.out.println("================================");

			} else {
				System.out.println("Voce escolheu voltar!");
			}

			System.out.println("-------------------");
			System.out.println("Consulta Realizada!");
			System.out.println("-------------------");

		} else {
			System.out.println("Voc� n�o tem permiss�o!");
		}
		
		redirectToPointMenu();	
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