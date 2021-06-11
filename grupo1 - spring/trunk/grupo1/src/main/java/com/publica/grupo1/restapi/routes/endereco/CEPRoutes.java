package com.publica.grupo1.restapi.routes.endereco;

import org.hibernate.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.model.dao.endereco.CEPDAO;
import com.publica.grupo1.model.entities.endereco.CEP;
import com.publica.grupo1.restapi.routes.GenericRouter;
import com.publica.grupo1.restapi.routes.message.MessageGenerator;
import com.publica.grupo1.restapi.routes.security.SecurityAPI;

@RestController
@RequestMapping("/cep")
public class CEPRoutes {
	
	Session session = DBConnection.getSession();
	SecurityAPI securityApi = new SecurityAPI();
	MessageGenerator messageGenerator = new MessageGenerator();
	GenericRouter<CEP> router = new GenericRouter<CEP>();

	/**
	 * This Route returns a JSON containing all objects from database
	 * 
	 * @return JSON containing all object registers in database
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> getAll(@RequestHeader("Authorization") String token) {
		return router.getAll(token, CEPDAO.getInstance(session));
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
		return router.selectById(id, token, CEPDAO.getInstance(session));
	}
	
	/***
	 * This method finds an CEP object by its cep and then returns it.
	 * 
	 * @param cep   String: cepvalue
	 * @param token String: logged user token that will be validated
	 * @return A JSON of the found object
	 */	
	@RequestMapping(value = "/cep/{cep}", method = RequestMethod.GET)
	public ResponseEntity<Object> create(@RequestBody CEP cep, @RequestHeader("Authorization") String token) {
		return router.create(cep, token, CEPDAO.getInstance(session));
	}
	
	/***
	 * Replaces object on database with <code>id</code> in the URL request
	 * with a object in the request body
	 * 
	 * @param cep
	 * @param id
	 * @return The new CEP
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@RequestBody CEP cep, @PathVariable("id") int id,
			@RequestHeader("Authorization") String token) {
		return router.update(cep, id, token, CEPDAO.getInstance(session));
	}
	
	/***
	 * Deletes a object from the database with the <code>id</code> in the 
	 * URL request.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") int id, 
			@RequestHeader("Authorization") String token) {
		return router.delete(id, token, CEPDAO.getInstance(session));
	}
}












