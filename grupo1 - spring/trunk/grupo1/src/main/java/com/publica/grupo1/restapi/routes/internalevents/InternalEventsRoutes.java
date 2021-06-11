package com.publica.grupo1.restapi.routes.internalevents;

import org.hibernate.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.model.dao.internalevents.InternalEventsDAO;
import com.publica.grupo1.model.entities.internalevents.InternalEvents;
import com.publica.grupo1.restapi.routes.GenericRouter;
import com.publica.grupo1.restapi.routes.message.MessageGenerator;
import com.publica.grupo1.restapi.routes.security.SecurityAPI;

@RestController
@RequestMapping("/internalevents")
public class InternalEventsRoutes {

	Session session = DBConnection.getSession();
	SecurityAPI securityApi = new SecurityAPI();
	MessageGenerator messageGenerator = new MessageGenerator();
	GenericRouter<InternalEvents> router = new GenericRouter<InternalEvents>();

	/**
	 * This Route returns a JSON containing all objects from database
	 * 
	 * @return JSON containing all object registers in database
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> getAll(@RequestHeader("Authorization") String token) {
		return router.getAll(token, InternalEventsDAO.getInstance(session));
	}

	/**
	 * This method finds an InternalEvents object by its ID and then returns it.
	 * 
	 * @param id    String: Number indicating the ID of the object
	 * @param token String: logged user token that will be validated
	 * @return A JSON object of the found InternalEvents Object that was searched
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> selectById(@PathVariable("id") int id, @RequestHeader("Authorization") String token) {
		return router.selectById(id, token, InternalEventsDAO.getInstance(session));
	}

	/**
	 * This method Gets the JSON on the body of the request, converts it into a
	 * InternalEvents Object and inserts it in database.
	 * 
	 * @param accessLevel. <br>
	 *                     <code>
	 * <pre>
	{
	"name": "BASIC",
	"capabilities": [
	{
	  "id": 44,
	  "name": "A"
	}
	]
	}
	 * </pre>
	 * </code>
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody InternalEvents event,
			@RequestHeader("Authorization") String token) {
		return router.create(event, token, InternalEventsDAO.getInstance(session));
	}

	/***
	 * Replaces object on database with <code>id</code> in the URL request with a
	 * object in the request body
	 * 
	 * @param internalEvent (internalEvent)
	 * @param id
	 * @return The new Internal Event
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@RequestBody InternalEvents event, @PathVariable("id") int id,
			@RequestHeader("Authorization") String token) {
		return router.update(event, id, token, InternalEventsDAO.getInstance(session));
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
		return router.delete(id, token, InternalEventsDAO.getInstance(session));
	}

}
