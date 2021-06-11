package com.publica.grupo1.restapi.routes.collaborator;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.publica.grupo1.controller.collaborator.PostController;
import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.model.dao.collaborator.CollaboratorDAO;
import com.publica.grupo1.model.entities.collaborator.Collaborator;
import com.publica.grupo1.restapi.routes.GenericRouter;
import com.publica.grupo1.restapi.routes.message.MessageGenerator;
import com.publica.grupo1.restapi.routes.security.JwtTokenUtil;
import com.publica.grupo1.restapi.routes.security.SecurityAPI;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/collaborator")
public class CollaboratorRoutes {

	Session session = DBConnection.getSession();
	SecurityAPI securityApi = new SecurityAPI();
	MessageGenerator messageGenerator = new MessageGenerator();
	GenericRouter<Collaborator> router = new GenericRouter<Collaborator>();

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/**
	 * This Route returns a JSON containing all objects from database
	 * 
	 * @return JSON containing all object registers in database
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> getAll(@RequestHeader("Authorization") String token) {
		return router.getAll(token, CollaboratorDAO.getInstance(session));
	}

	/***
	 * This Route returns the collaborator based on the token sent in the request
	 * header
	 * 
	 * @param token
	 * @return Collaborator
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public Collaborator getProfile(@RequestHeader("Authorization") String token) {
		CollaboratorDAO dao = CollaboratorDAO.getInstance(session);
		String cpf = jwtTokenUtil.getUsernameFromToken(token.replace("Bearer ", ""));

		return dao.selectByCPF(cpf);
	}

	/**
	 * This method finds an object by its ID and then returns it.
	 * 
	 * @param id    String: Number indicating the ID of the object
	 * @param token String: logged user token that will be validated
	 * @return A JSON of the found object
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> selectById(@PathVariable("id") int id, @RequestHeader("Authorization") String token) {
		return router.selectById(id, token, CollaboratorDAO.getInstance(session));
	}

	/***
	 * This method finds an Collaborator object by its cpf and then returns it.
	 * 
	 * @param cpf   String: cpf value
	 * @param token String: logged user token that will be validated
	 * @return A JSON of the found object
	 */
	@RequestMapping(value = "/cpf/{cpf}", method = RequestMethod.GET)
	public ResponseEntity<Object> selectByCPF(@PathVariable("cpf") String cpf,
			@RequestHeader("Authorization") String token) {
		if (securityApi.hasAdminAccess(token)) {
			CollaboratorDAO dao = CollaboratorDAO.getInstance(session);
			Collaborator collab = dao.selectByCPF(cpf);

			if (collab == null) {
				return messageGenerator.generateNotFoundError("CPF not found");
			} else {
				return new ResponseEntity<>(collab, HttpStatus.OK);
			}
		} else {
			return messageGenerator.generateUnauthorizedError();
		}
	}

	/**
	 * This method insert a new collaborator object in database
	 * 
	 * @param collaborator The example below shows how the JSON must be: <br>
	 *                     <code>
	 * <pre>
	{
	"idCollaborator": 1,
	"permission": {
	"id": 1,
	"accessLevel": {
	  "id": 1,
	  "name": "Teste",
	  "capabilities": []
	},
	"role": {
	  "id": 1,
	  "workLoad": 8,
	  "name": "Cozinheiro"
	},
	"department": {
	  "id": 1,
	  "name": "Teste"
	}
	},
	"endereco": {
	"id": 1,
	"cep": {
	  "id": 1,
	  "cep": "12345000",
	  "localidade": "Localidade",
	  "bairro": "Bairro",
	  "logradouro": "Ribeirinho",
	  "complemento": "Do lado do bar"
	},
	"localidade": "Blumenau",
	"bairro": "Vila Nova",
	"logradouro": "Casa do zé",
	"complemento": "Limoeiro",
	"numero": 0
	},
	"contact": {
	"id": 1,
	"email": "josue@joestar.com",
	"phoneList": []
	},
	"admissionDate": "2020-03-01",
	"birthDate": "2002-07-17",
	"lastVacationDate": "2020-03-01",
	"salary": 2340.0,
	"nocturnalWork": true,
	"rg": "4321284",
	"cpf": "11232343212",
	"motherName": "Joana",
	"fatherName": "João",
	"maritalStatus": "solteiro",
	"naturalness": "pomerode",
	"nacionality": "Brasil",
	"password": "123",
	"name": "Josué",
	"surname": "Joestar",
	"gender": {
	"idGenre": 1,
	"nameGenre": "Boi"
	},
	"birthDateByAmericanPattern": "2002-07-17",
	"admissionDateByAmericanPattern": "2020-03-01"
	}
	 * </pre>
	 * </code>
	 * @return Object's ID on database
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody Collaborator collaborator,
			@RequestHeader("Authorization") String token) {
		if (securityApi.hasAdminAccess(token)) {
			PostController.fillingCollaboratorFields(collaborator);
			CollaboratorDAO.getInstance(session).insert(collaborator);
			return messageGenerator.generateInsertedObjectMessage(collaborator);
		} else {
			return messageGenerator.generateUnauthorizedError();
		}
	}

	/***
	 * Replaces object on database with <code>id</code> in the URL request with a
	 * object in the request body
	 * 
	 * @param collaborator
	 * @param id
	 * @return The new collaborator
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@RequestBody Collaborator collaborator, @PathVariable("id") int id,
			@RequestHeader("Authorization") String token) {
		return router.update(collaborator, id, token, CollaboratorDAO.getInstance(session));
	}

	/***
	 * Deletes a object from the database with the <code>id</code> in the URL
	 * request.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") int id, @RequestHeader("Authorization") String token) {
		return router.delete(id, token, CollaboratorDAO.getInstance(session));
	}

}
