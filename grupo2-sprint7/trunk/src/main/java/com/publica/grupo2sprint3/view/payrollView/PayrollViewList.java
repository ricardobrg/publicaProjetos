package com.publica.grupo2sprint3.view.payrollView;


import java.util.ArrayList;

import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;

public class PayrollViewList extends PayrollView{
	
	//Singleton {
	private static PayrollViewList instance;

	PayrollViewList(Collaborator collab, AccessLevel accessLevel) {
		super(collab);
	}

	public static PayrollViewList getInstance(Collaborator collab, AccessLevel accessLevel) {
		if (instance == null) {
			instance = new PayrollViewList(collab, accessLevel);
		}
		return instance;
		
	}
	
	public static void destroyInstance() {
		instance = null;
	}
	// }
	
	/***
	 * Method to get formated output
	 * for the PayrollViewList
	 */
	@Override
	public String getOutput() {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		String output = "--------- Minha Folha --------- \n";

//		output += String.format("Nome: %-25s Cargo: %-25s Departamento: %-25s Salário: %.2f \n", 
//				collab.getName() , collab.getRole().getName(), collab.getRole().getDepartment().getName(),
//				com.publica.grupo2sprint3.controller.getSalary());
//
//		if(access == AccessLevel.ADVANCED) {
//			list = com.publica.grupo2sprint3.controller.getAdvancedList();
//			output += "\n--------- Departamento --------- \n";
//		}
//		else if(access == AccessLevel.TOTAL) {
//			list = com.publica.grupo2sprint3.controller.getTotalList();
//			output += "\n--------- Empresa --------- \n";
//		}

		for (ArrayList<String> element : list) { 
			output += String.format("Nome: %-25s Cargo: %-25s Departamento: %-25s Salário: %-25.2f \n", 
					element.get(0), element.get(1), element.get(2), Float.parseFloat(element.get(3))); 
		}
		return output;

	}
	
	/***
	 * Simple method for stand by
	 * for user.
	 * 
	 */
	@Override
	protected void readInput() {
		System.out.print("Pressione enter para continar");
		Main.scan.nextLine();
		Main.scan.nextLine();
		controller.goToHome();
	}

}
