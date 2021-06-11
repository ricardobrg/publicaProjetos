package api.endpoints;

import org.springframework.beans.factory.annotation.Autowired;

import api.endpoints.auth.config.JwtTokenUtil;
import controllers.CollaboratorController;
import models.entities.person.Collaborator;
import models.entities.security.AccessLevel;

public class Api {

	@Autowired
	protected JwtTokenUtil jwtToken = new JwtTokenUtil();
	
	/***
	 * Verifies if the user has the required access level.
	 * 
	 * @param endPointLevel : the access required by the endpoint
	 * @param token : the user's token (has the access level)
	 * @return <code>true</code> if has access; <code>false</code>
	 */
	public boolean hasAccess(AccessLevel endPointLevel, String token) {
		Collaborator collab = getUser(token);
		
		return collab.getRole().getAccessLevel().getValue() >= endPointLevel.getValue() ? true : false;
	}
	
	public boolean hasCpfAccess(AccessLevel endPointLevel, String token, String cpf) {
		Collaborator collab = getUser(token);
		
		return collab.getCpf().equals(cpf) ? 
				true : collab.getRole().getAccessLevel().getValue() >= endPointLevel.getValue() ? true : false;
	}
	
	public boolean hasCpfEquals(String token, String cpf) {
		Collaborator collab = getUser(token);
		
		return collab.getCpf().equals(cpf) ? true : false;
	}
	
	protected Collaborator getUser(String token) {
		token = token.replace("Bearer ","");
		
		return new CollaboratorController().findByCpf(jwtToken.getUsernameFromToken(token));
	}

}
