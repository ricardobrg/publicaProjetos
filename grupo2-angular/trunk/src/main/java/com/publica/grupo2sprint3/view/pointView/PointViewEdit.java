package com.publica.grupo2sprint3.view.pointView;

import java.util.ArrayList;

import com.publica.grupo2sprint3.controller.CollaboratorController;
import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.controller.PointController;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Point;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;

/**
 * <br> Point Edit View Class. <br>
 * <br>
 * 
 * This class calls the controllers related to editing the points registry.
 * 
 * All registered employees are displayed, identified by an ID. 
 * The ID is assigned by the list index. A collaborator is selected using his ID.
 * 
 * All points of the selected employee are displayed, identified by an ID. The ID is 
 * assigned by the list index too. 
 * 
 * To edit the selected point, a new time must be informed, following the template "hh: mm".
 * 
 * @version 1.1.0
 * 
 * @author Jessé Amaro <jesse.amaro7@gmail.com>
 */
public class PointViewEdit extends PointView {

	public PointViewEdit(Collaborator collab) {
		super(collab);
	}

	public void display() {
		if (access == AccessLevel.TOTAL) {
			System.out.println("Editar Registros");

			ArrayList<Collaborator> allCollabs = CollaboratorController.getInstance(collab).getCollabs();

			System.out.println("Lista de todos os colaboradores: ");
			System.out.println("");

			for (int i = 0; i < allCollabs.size(); i++) {
				System.out.println("Colaborador - ID " + (i + 1));
				System.out.println("Nome: " + allCollabs.get(i).getName());
				System.out.println("------------------------------------");
			}

			System.out.println("Informe o ID do colaborador desejado: ");
			int inputID = Main.scan.nextInt();

			Collaborator colabFound = allCollabs.get(inputID - 1);

			System.out.println("Colaborador Selecionado: " + colabFound.getName());
			System.out.println("");
			System.out.println("Listando todos os pontos...");
			System.out.println("---------------------------");
			System.out.println("");

			ArrayList<Point> points = PointController.getInstance(colabFound).getAllCollabsPoint(colabFound);

			System.out.println("================================");
			String printPoints = "";
			for (int i = 0; i < points.size(); i++) {
				Point point = points.get(i);
				String id = String.valueOf("Registro 0" + (i + 1));
				printPoints = printPoints + id + " - " + point.getDate() + " - " + point.convertDayMinute() + "\n";
			}

			System.out.print(printPoints);
			System.out.println("================================");
			System.out.println("");

			System.out.println("Informe o registro que deseja alterar: ");
			int inputID2 = Main.scan.nextInt();

			Point returnedPoint;

			try {
				returnedPoint = points.get(inputID2 - 1);
			} catch (Exception e) {
				System.out.println("Registro não encontrado!");

				redirectToPointMenu();
				return;
			}

			System.out.println("Informe o novo horário, no formato hh:mm");
			String newTime = Main.scan.next();

			String[] splittedTime = newTime.split(":");

			int hour = Integer.parseInt(splittedTime[0]);
			int minute = Integer.parseInt(splittedTime[1]);

			int dayMinute = (hour * 60) + minute;

			PointController.getInstance(colabFound).editPointHour(returnedPoint, dayMinute);

			System.out.println("Alterado com sucesso!");
			
			redirectToPointMenu();
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
