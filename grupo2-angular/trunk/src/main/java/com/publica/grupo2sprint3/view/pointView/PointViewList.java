package com.publica.grupo2sprint3.view.pointView;

import java.util.ArrayList;

import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Point;

/**
 * <br> Point List View Class <br> 
 * <br> 
 * 
 * This class calls the controllers related to list the points registry.
 * 
 * First, a date in the format "dd/mm/yyyy" must be entered for searching. 
 * All records of the informed day for the connected employee are displayed, 
 * identified by an ID. The ID is assigned by the list index.
 * 
 * @version 1.1.0
 * 
 * @author Jessé Amaro <jesse.amaro7@gmail.com>
 */
public class PointViewList extends PointView {
	
	private ArrayList<Point> points; 
	
	public PointViewList(Collaborator collab, ArrayList<Point> points) {
		super(collab);
		this.points = points;
	}

	@Override
	public String getOutput() {
		String output = "";
		
		if (points.isEmpty()) {
			System.out.println("");
			System.out.println("========================");
			System.out.println("Registro não encontrado!");
			System.out.println("========================");
			return output;
		}
		
		System.out.println("Registros Encontrados: ");
		
		for (int i = 0; i < points.size(); i++) {
			System.out.println("");
			System.out.println("Registro Nº" + (i + 1));
			System.out.println("Dia: " + points.get(i).getDate());

			System.out.println("Horário: ");
			String registry = points.get(i).convertDayMinute();

			output += registry + "\n";
			System.out.println("---------------");
		}

		System.out.println("Consulta Realizada!");
		System.out.println("-------------------");

		return output;
	}

	@Override
	protected void readInput() {
		controller.goToInitial();	
	}
}