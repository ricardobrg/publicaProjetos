package com.publica.grupo1.restapi.routes.security;

import org.springframework.beans.factory.annotation.Autowired;

import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.model.dao.collaborator.CollaboratorDAO;
import com.publica.grupo1.model.entities.collaborator.Collaborator;
import com.publica.grupo1.model.entities.permissions.AccessLevel;

public class SecurityAPI {

	@Autowired
	protected JwtTokenUtil jwtToken = new JwtTokenUtil();

	/***
	 * Verifies if the user has admin access level.
	 * 
	 * @param token : the user's token (has the access level)
	 * @return <code>true</code> if has access; <code>false</code>
	 */
	public boolean hasAdminAccess(String token) {
		Collaborator collab = getUser(token);

		return collab.getPermission().getAccessLevel().equals(AccessLevel.ADMIN);
	}

	/***
	 * Verifies if the user has the required access level.
	 * 
	 * @param token : the user's token (has the access level)
	 * @param cpf   : cpf of the user
	 * @return <code>true</code> if has access; <code>false</code>
	 */
	public boolean hasCpfAccess(String token, String cpf) {
		Collaborator collab = getUser(token);

		return collab.getCpf().equals(cpf) ? true : collab.getPermission().getAccessLevel().equals(AccessLevel.ADMIN);
	}

	public boolean hasCpfEquals(String token, String cpf) {
		Collaborator collab = getUser(token);

		return collab.getCpf().equals(cpf) ? true : false;
	}

	public Collaborator getUser(String token) {
		token = token.replace("Bearer ", "");
		return CollaboratorDAO.getInstance(DBConnection.getSession()).selectByCPF(jwtToken.getUsernameFromToken(token));
	}
}
