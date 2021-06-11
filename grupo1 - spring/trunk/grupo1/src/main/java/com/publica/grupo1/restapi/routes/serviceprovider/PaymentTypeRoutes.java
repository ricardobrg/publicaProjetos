package com.publica.grupo1.restapi.routes.serviceprovider;

import org.hibernate.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.model.dao.serviceprovider.PaymentTypeDAO;
import com.publica.grupo1.model.entities.serviceprovider.PaymentType;
import com.publica.grupo1.restapi.routes.GenericRouter;
import com.publica.grupo1.restapi.routes.message.MessageGenerator;
import com.publica.grupo1.restapi.routes.security.SecurityAPI;

/***
 * Endpoint of PaymentType. Contains <code>GET</code>, <code>POST</code>,
 * <code>PUT</code> and <code>DELETE</code> methods.
 * 
 * @author Pedro Vinicius Hostert <pedrohostertt@gmail.com>
 *
 */
@RestController
@RequestMapping("/paymenttype")
public class PaymentTypeRoutes {

	Session session = DBConnection.getSession();
	SecurityAPI securityApi = new SecurityAPI();
	MessageGenerator messageGenerator = new MessageGenerator();
	GenericRouter<PaymentType> router = new GenericRouter<PaymentType>();

	/**
	 * This Route returns a JSON containing all objects from database
	 * 
	 * @return JSON containing all object registers in database
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> getAll(@RequestHeader("Authorization") String token) {
		return router.getAll(token, PaymentTypeDAO.getInstance(session));
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
		return router.selectById(id, token, PaymentTypeDAO.getInstance(session));
	}

	/**
	 * This method creates a new object.
	 * 
	 * The example below shows how the JSON must be: <br>
	 * <code>
	 * <pre>
	{
	"name": "Cheque"
	}
	 * </pre>
	 * </code>
	 * 
	 * @param paymentType
	 * @return Object's ID on database
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody PaymentType paymentType,
			@RequestHeader("Authorization") String token) {
		return router.create(paymentType, token, PaymentTypeDAO.getInstance(session));
	}

	/***
	 * Replaces object on database with <code>id</code> in the URL request with a
	 * object in the request body
	 * 
	 * @param paymentType
	 * @param id
	 * @return The new PaymentType
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@RequestBody PaymentType paymentType, @PathVariable("id") int id,
			@RequestHeader("Authorization") String token) {
		return router.update(paymentType, id, token, PaymentTypeDAO.getInstance(session));
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
		return router.delete(id, token, PaymentTypeDAO.getInstance(session));
	}
}
