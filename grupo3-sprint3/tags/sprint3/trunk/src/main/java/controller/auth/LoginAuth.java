package controller.auth;

import model.dao.AccessLevelDAO;
import model.dao.CPFDAO;
import model.dao.CollaboratorDAO;
import model.entities.person.Collaborator;
import utils.authentication.ToHash;

/**
 * This class provides methods for user authentication in the system.
 * 
 * @version 1.0.0
 * @author Pedro Vinicius Hostert<pedrohostertt@gmail.com>
 */
public class LoginAuth {
	
	/**
	 * This method gets by parameters the values sent by the login view and
	 * verifies if the hash generated with these data is equals to user hash.
	 * 
	 * @param cpf String: CPF of ther user that wants to be logged in
	 * @param password String: password of ther user that wants to be logged in
	 * @return <code>boolean</code>: True if the user is registered. False if is no
	 *         registered.
	 * 
	 * @version 1.0.0
	 * @author Pedro Vinicius Hostert<pedrohostertt@gmail.com>
	 */
	public static boolean isLoggedIn(String cpf, String password) {
		var collDAO = new CollaboratorDAO();		
		boolean hasCPF = collDAO.getAll().stream()
										 .map(o -> o.getCpf())
										 .anyMatch(str -> str.equals(cpf));
		if(!hasCPF)
			return false;
		
		Collaborator coll = (Collaborator) collDAO.findObject(cpf);		
		password = ToHash.hashGeneratorSha(password);
		
		if(coll.getPwdHash().equals(password)) {
			new AccessLevelDAO().updateObject(coll.getRole().getAccessLevel());
			new CPFDAO().updateCPF(cpf);
			return true;
		}
		
		return false;
	}
}
