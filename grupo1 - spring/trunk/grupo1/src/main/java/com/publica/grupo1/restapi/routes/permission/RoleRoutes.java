package com.publica.grupo1.restapi.routes.permission;

import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.model.dao.permissions.RoleDAO;
import com.publica.grupo1.model.entities.permissions.role.Role;
import com.publica.grupo1.restapi.routes.GenericRouter;
import com.publica.grupo1.restapi.routes.message.MessageGenerator;
import com.publica.grupo1.restapi.routes.security.SecurityAPI;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/role")
public class RoleRoutes {

	Session session = DBConnection.getSession();
	SecurityAPI securityApi = new SecurityAPI();
	MessageGenerator messageGenerator = new MessageGenerator();
	GenericRouter<Role> router = new GenericRouter<Role>();

	/**
	 * This Route returns a JSON containing all objects from database
	 * 
	 * @return JSON containing all object registers in database
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> getAll(@RequestHeader("Authorization") String token) {
		return router.getAll(token, RoleDAO.getInstance(session));
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
		return router.selectById(id, token, RoleDAO.getInstance(session));
	}
	
	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public ResponseEntity<Object> selectById(@PathVariable("name") String name, @RequestHeader("Authorization") String token) {
		Role responseObj = RoleDAO.getInstance(DBConnection.getSession()).selectByName(name);
		return new ResponseEntity<Object>(responseObj, HttpStatus.OK);
	}

	/**
	 * This method
	 * 
	 * @param role A new Role parameters. As this method contains the annotation
	 *             <code>@Consumes</code>, this new object could be a JSON,
	 *             containing the same attributes as a Role object. The example
	 *             below shows how the JSON must be: <br>
	 *             <code>
	 * <pre>
	 * {
	 *    "workLoad": 480,
	 *    "name": "Cargo Editado"
	 * }
	 * </pre>
	 * </code>
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody Role role, @RequestHeader("Authorization") String token) {
		return router.create(role, token, RoleDAO.getInstance(session));
	}

	/***
	 * Replaces object on database with <code>id</code> in the URL request with a
	 * object in the request body
	 * 
	 * @param Role
	 * @param id
	 * @return The new Role
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@RequestBody Role role, @PathVariable("id") int id,
			@RequestHeader("Authorization") String token) {
		return router.update(role, id, token, RoleDAO.getInstance(session));
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
		return router.delete(id, token, RoleDAO.getInstance(session));
	}

}
