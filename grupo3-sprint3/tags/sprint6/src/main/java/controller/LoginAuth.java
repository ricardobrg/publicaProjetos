package controller;

import model.dao.CollaboratorDAO;
import model.entities.person.Collaborator;
import utils.authentication.ToHash;
import view.Menu;

/**
 * This class provides methods for user authentication in the system.
 * 
 * @version 1.0.0
 * @author Pedro Vinicius Hostert<pedrohostertt@gmail.com>
 */
public class LoginAuth extends Menu{
	
	/**
	 * This method gets by parameters the values sent by the login view and
	 * verifies if the hash generated with these data is equals to user hash.
	 * 
	 * @param cpf String: CPF of ther user that wants to be logged in
	 * @param password String: password of ther user that wants to be logged in
	 * @return <code>boolean</code>: True if the user is registered. False if is no
	 *         registered.
	 * 
	 * @version 1.5.0
	 * @author Pedro Vinicius Hostert<pedrohostertt@gmail.com>
	 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
	 * 
	 */
	public boolean isLoggedIn(String cpf, String password) {
		
		CollaboratorDAO collDAO = new CollaboratorDAO();
		Collaborator coll = collDAO.findByCpf(cpf);		
		
		if (coll == null) 
			return false;
		
		password = ToHash.hashGeneratorSha(password);
		
		if(coll.getPwdHash().equals(password)) {
			acc = coll.getRole().getAccessLevel();
			currentCPF = coll.getCpf();
			return true;
		}
		
		return false;
	}

}
