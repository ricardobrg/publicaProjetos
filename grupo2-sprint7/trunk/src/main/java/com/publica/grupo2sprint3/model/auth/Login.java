package com.publica.grupo2sprint3.model.auth;

import com.publica.grupo2sprint3.model.dao.CollaboratorDAO;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.util.ToHash;

public class Login {
	private static Collaborator collab;
	
	public static boolean login(String user, String password){
		boolean bool = false;
		for (Collaborator nowCollab : CollaboratorDAO.getInstance().getAll()) {
			bool = nowCollab.getUser().equals(user) && nowCollab.getPassword().equals(ToHash.hashGeneratorPbk(user, password));
				
			if (bool) {
				collab = nowCollab;
				break;
			}
		}
		return bool;
	}

	public static Collaborator getCollab() {
		return collab;
	}
}
