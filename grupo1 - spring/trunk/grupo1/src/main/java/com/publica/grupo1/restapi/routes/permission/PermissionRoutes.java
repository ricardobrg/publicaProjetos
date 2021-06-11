package com.publica.grupo1.restapi.routes.permission;

import org.hibernate.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.model.dao.permissions.PermissionDAO;
import com.publica.grupo1.model.entities.permissions.Permission;
import com.publica.grupo1.restapi.routes.GenericRouter;
import com.publica.grupo1.restapi.routes.message.MessageGenerator;
import com.publica.grupo1.restapi.routes.security.SecurityAPI;

@RestController
@RequestMapping("/permission")
public class PermissionRoutes {

	Session session = DBConnection.getSession();
	SecurityAPI securityApi = new SecurityAPI();
	MessageGenerator messageGenerator = new MessageGenerator();
	GenericRouter<Permission> router = new GenericRouter<Permission>();

	/**
	 * This Route returns a JSON containing all objects from database
	 * 
	 * @return JSON containing all object registers in database
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> getAll(@RequestHeader("Authorization") String token) {
		return router.getAll(token, PermissionDAO.getInstance(session));
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
		return router.selectById(id, token, PermissionDAO.getInstance(session));
	}

	/**
	 * This method insert in database a new Permission object, passed by parameter
	 * 
	 * @param permission: A new Permission object. As this method contains the
	 *                    annotation <code>@Consumes</code>, this new object could
	 *                    be a JSON, containing the same attributes as a Permission
	 *                    object. The example below shows how the JSON must be: <br>
	 *                    <code>
	 * <pre>
	{
	"accessLevel": {
	"capabilities": [],
	"id": 3,
	"name": "ALTERADO"
	},
	"department": {
	  "id": 1,
	"name": "TI"
	},
	"role": {
	"id": 8,
	"name": "novo",
	"workLoad": 480
	}
	}
	 * </pre>
	 * </code>
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody Permission permission,
			@RequestHeader("Authorization") String token) {
		return router.create(permission, token, PermissionDAO.getInstance(session));
	}

	/***
	 * Replaces object on database with <code>id</code> in the URL request with a
	 * object in the request body
	 * 
	 * @param permission
	 * @param id
	 * @return The new Permission
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@RequestBody Permission permission, @PathVariable("id") int id,
			@RequestHeader("Authorization") String token) {
		return router.update(permission, id, token, PermissionDAO.getInstance(session));
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
		return router.delete(id, token, PermissionDAO.getInstance(session));
	}
}
