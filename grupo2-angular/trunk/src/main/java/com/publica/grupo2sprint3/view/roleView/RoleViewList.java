package com.publica.grupo2sprint3.view.roleView;

import java.util.ArrayList;

import com.publica.grupo2sprint3.model.dao.RoleDAO;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Role;

/**
 * This View Class has the methods to show all the roles registered.
 * 
 * @version 1.1.0
 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
 * @author Jessé Amaro <jesse.amaro7@gmail.com>
 * @author Caio Shimada <xcaiosr@gmail.com>
 */
public class RoleViewList extends RoleView {

	private static RoleViewList instance;
	private ArrayList<Role> roles = RoleDAO.getInstance().getAll();
	
	

	private RoleViewList(Collaborator collab, ArrayList<Role> roles) {
		super(collab);
		this.roles = roles;
	}

	public static RoleViewList getInstance(Collaborator collab, ArrayList<Role> roles) {
		if (instance == null) {
			instance = new RoleViewList(collab, roles);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		instance = null;
	}

	/**
	 * This method show list of roles the output String
	 *
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 */
	@Override
	public String getOutput() {
		
		String output = "\n--------- Consulta de Cargos ---------\n" + //
				String.format("%-8s%-26s%-26s%-26s", "", "Cargo:", "Departamento:", "Salário:");
		
		
		for (Role role : roles ) {
			output += role.toString();
		}
		return output;
	}

	@Override
	protected void readInput() {
		roleController.goToInitial();
	}
}
