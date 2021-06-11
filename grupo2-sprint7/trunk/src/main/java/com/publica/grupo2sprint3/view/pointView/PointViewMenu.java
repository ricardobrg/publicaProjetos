package com.publica.grupo2sprint3.view.pointView;

import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;

/**
 * <br> Point View Menu Class <br>
 * <br>
 * 
 * This class is responsible to showing options for point control and point
 * querys.
 * 
 * The class receives a collaborator with all his properties and also the access
 * level that collaborator has.
 * 
 * The display only shows the options that the collaborator can see according to
 * their permissions.
 * 
 * Option 1 and 2 (Basic Access) - Available to everyone. Option 3 (Advanced
 * Access) - Available to managers and HR. Option 4 and 5 (Total Access) - Available
 * only for HR
 * 
 * @version 1.1.0
 * @author Jessé Amaro <jesse.amaro7@gmail.com>
 */
public class PointViewMenu extends PointView {

	public PointViewMenu(Collaborator collab) {
		super(collab);
	}

	/**
	 * It sets the output with the options to be printed on the com.publica.grupo2sprint3.view.
	 * 
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 * @return the string set up ready to be printed
	 */
	public String getOutput() {
		
		String output = "\n--------- Registro de Pontos ---------\n";
		output += "\n";

		output += "# Informe o número da opção desejada: \n";
		output += "\n";

		if (access.id <= AccessLevel.BASIC.id) {
			output += "0. Voltar\n";
			output += "1. Novo Registro de Ponto\n";
			output += "2. Consultar Registros de Ponto\n";
		}

		if (access.id <= AccessLevel.ADVANCED.id) {
			output += "3. Consultar Registros por Setor\n";
		}

		if (access == AccessLevel.TOTAL) {
			output += "4. Editar Registros de Ponto\n";
			output += "5. Deletar Registros de Ponto\n";
		}
		return output;
	}
	
	/**
	 * Reads the option selected by the user and calls the correct method in the
	 * Controller.
	 * 
	 * It verifies if the user has access to the option selected.
	 * 
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 */
	protected void readInput() {
	
		int input = Main.scan.nextInt();
		switch (input) {
		case 0:
			controller.goToHome();
			break;
			
		case 1:
			if (access != AccessLevel.ADMIN)
				controller.create();
			else
				System.out.println("Ação Não Permitida");
			break;
			
		case 2:
			if (access != AccessLevel.ADMIN) {
				System.out.println("Informe a data, no formato dd/mm/aaaa,  para pesquisar o registro.");
				System.out.println("");
				String inputDate = Main.scan.next();
				
				controller.list(inputDate);
			}
			else
				System.out.println("Ação Não Permitida");
			break;
			
		case 3:
			if (access != AccessLevel.BASIC)
				controller.listByDepartment();
			else
				System.out.println("Ação Não Permitida");
			break;
			
		case 4:
			if (access == AccessLevel.TOTAL || access == AccessLevel.ADMIN)
				controller.editPointHourView();
			else
				System.out.println("Ação Não Permitida");
			break;
			
		case 5:
			if (access == AccessLevel.TOTAL || access == AccessLevel.ADMIN)
				controller.removeView();
			else
				System.out.println("Ação Não Permitida");
			break;
			
		default:
			System.out.println("Opção Inválida");
			break;
		}
	}
}