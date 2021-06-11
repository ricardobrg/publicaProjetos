package controller;

import com.google.gson.JsonObject;

import model.dao.CollaboratorDAO;
import model.entities.person.Collaborator;
import utils.authentication.ToHash;
import view.MainMenu;

/**
 * This class provides methods for user authentication in the system.
 * 
 * @version 1.0.0
 * @author Pedro Vinicius Hostert<pedrohostertt@gmail.com>
 */
public class LoginController{
	
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
	public JsonObject logIn(JsonObject dataLogin) {	
		
		CollaboratorDAO collDAO = new CollaboratorDAO();
		Collaborator coll = collDAO.findByCpf(dataLogin.get("cpf").getAsString());		
		
		String password = ToHash.hashGeneratorSha(dataLogin.get("password").getAsString());
		JsonObject dataLoginRet = new JsonObject();
		
		if(coll == null) {
			dataLoginRet.addProperty("message", "CPF nao cadastrado!");
			return dataLoginRet;
		}
		
		if(coll.getPwdHash().equals(password)) {
			dataLoginRet.addProperty("accessLevel", coll.getRole().getAccessLevel().name());
			dataLoginRet.addProperty("cpf", coll.getCpf());
			new MainMenu(dataLoginRet).print();
		}else {
			dataLoginRet.addProperty("message","Senha invalida!");
		}
		
		return dataLoginRet;
	}

}
