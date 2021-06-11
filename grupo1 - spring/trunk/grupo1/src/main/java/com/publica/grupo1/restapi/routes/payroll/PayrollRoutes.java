package com.publica.grupo1.restapi.routes.payroll;

import java.time.LocalDate;

import org.hibernate.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.publica.grupo1.controller.payroll.PayrollGenerator;
import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.model.dao.collaborator.CollaboratorDAO;
import com.publica.grupo1.model.dao.payroll.PayrollDAO;
import com.publica.grupo1.model.dao.point.PointDAO;
import com.publica.grupo1.model.entities.collaborator.Collaborator;
import com.publica.grupo1.model.entities.payroll.Payroll;
import com.publica.grupo1.restapi.routes.GenericRouter;
import com.publica.grupo1.restapi.routes.message.MessageGenerator;
import com.publica.grupo1.restapi.routes.security.SecurityAPI;

@RestController
@RequestMapping("/payroll")
public class PayrollRoutes {

	Session session = DBConnection.getSession();
	SecurityAPI securityApi = new SecurityAPI();
	MessageGenerator messageGenerator = new MessageGenerator();
	GenericRouter<Payroll> router = new GenericRouter<Payroll>();
	CollaboratorDAO collabDao = CollaboratorDAO.getInstance(session);

	/**
	 * This Route returns a JSON containing all objects from database
	 * 
	 * @return JSON containing all object registers in database
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> getAll(@RequestHeader("Authorization") String token) {
		return router.getAll(token, PayrollDAO.getInstance(session));
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
		return router.selectById(id, token, PayrollDAO.getInstance(session));
	}

	/**
	 * This method creates a new object.
	 * 
	 * The example below shows how the URL request must be: <br>
	 * <code>
	 * <pre>
	http://127.0.0.1:8080/grupo1/payroll/1/10/2021
	 * </pre>
	 * </code>
	 * 
	 * @param int idCollab
	 * @param int month
	 * @param int year
	 * @return Object's ID on database
	 */
	@RequestMapping(value = "/{idCollab}/{month}/{year}", method = RequestMethod.POST)
	public ResponseEntity<Object> create(@PathVariable("idCollab") int idCollab, @PathVariable("month") int month,
			@PathVariable("year") int year, @RequestHeader("Authorization") String token) {
		Collaborator collab = collabDao.selectById(idCollab);

		if (securityApi.hasCpfAccess(token, collab.getCpf())) {
			PayrollGenerator generator = new PayrollGenerator(collab, PointDAO.getInstance(session));
			generator.calculateDatePayroll(LocalDate.of(year, month, 1));
			int id = PayrollDAO.getInstance(session).insert(generator.getPayroll());

			return messageGenerator.generateInsertedObjectMessage(id);
		} else {
			return messageGenerator.generateUnauthorizedError();
		}
	}

	/***
	 * Replaces object on database with <code>id</code> in the URL request with a
	 * object in the request body
	 * 
	 * @param payroll
	 * @param id
	 * @return The new Payroll
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@RequestBody Payroll payroll, @PathVariable("id") int id,
			@RequestHeader("Authorization") String token) {
		return router.update(payroll, id, token, PayrollDAO.getInstance(session));
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
		return router.delete(id, token, PayrollDAO.getInstance(session));
	}
}
