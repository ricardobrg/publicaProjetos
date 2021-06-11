package com.publica.grupo1.restapi.routes.vacations;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.publica.grupo1.controller.vacation.VacationController;
import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.model.dao.collaborator.CollaboratorDAO;
import com.publica.grupo1.model.dao.vacation.TimeOffTrackerDAO;
import com.publica.grupo1.model.entities.collaborator.Collaborator;
import com.publica.grupo1.model.entities.vacations.TimeOffTracker;
import com.publica.grupo1.restapi.routes.GenericRouter;
import com.publica.grupo1.restapi.routes.message.MessageGenerator;
import com.publica.grupo1.restapi.routes.security.SecurityAPI;

@RestController
@RequestMapping("/timeofftracker")
public class TimeOffTrackerRoutes {

	Session session = DBConnection.getSession();
	SecurityAPI securityApi = new SecurityAPI();
	MessageGenerator messageGenerator = new MessageGenerator();
	GenericRouter<TimeOffTracker> router = new GenericRouter<TimeOffTracker>();

	/**
	 * This Route verifies if collaborator with ID in URL request can take off at
	 * the moment.
	 * 
	 * @return JSON containing all object registers in database
	 */
	@RequestMapping(value = "/{collabId}", method = RequestMethod.GET)
	public ResponseEntity<Object> verifyTakeOff(@PathVariable("collabId") int collabId,
			@RequestHeader("Authorization") String token) {

		Map<String, String> responseObj = new HashMap<>();

		Collaborator collab = CollaboratorDAO.getInstance(session).selectById(collabId);
		VacationController vacController = new VacationController(collab, session);

		if (securityApi.hasCpfAccess(token, collab.getCpf())) {

			responseObj.put("isVacationAvailable", String.valueOf(vacController.isVacationAvailable()));
			if (vacController.isVacationAvailable()) {
				responseObj.put("daysAmount", String.valueOf(vacController.vacationAllowedDays()));
			}
			if (vacController.daysToVacation() >= 0)
				responseObj.put("daysToNextVacation", String.valueOf(vacController.daysToVacation()));

			return messageGenerator.generateOkMessage(responseObj);

		} else {
			return messageGenerator.generateUnauthorizedError();
		}
	}

	/***
	 * Checks if collaborator with ID in URL request can take off, if true registers
	 * a new vacation.
	 * 
	 * @param collabId
	 * @return
	 */
	@RequestMapping(value = "/{collabId}", method = RequestMethod.POST)
	public ResponseEntity<Object> registerTakeOff(@PathVariable("collabId") int collabId,
			@RequestHeader("Authorization") String token) {
		Collaborator collab = CollaboratorDAO.getInstance(session).selectById(collabId);
		VacationController vacController = new VacationController(collab, session);

		Map<String, String> responseObj = new HashMap<>();
		responseObj.put("collaborator identifier", String.valueOf(collab.getIdCollaborator()));
		
		if (securityApi.hasCpfAccess(token, collab.getCpf())) {

			if (!vacController.isVacationAvailable()) {
				responseObj.put("message", "collaborator cant take off");
				return messageGenerator.generate(responseObj, HttpStatus.OK);
			}

			TimeOffTracker vacation = new TimeOffTracker();
			vacation.setCollab(collab);
			vacation.setDate(LocalDate.now());

			if (vacController.vacationAllowedDays() <= 0) {
				responseObj.put("message", "insufficient time worked");
				return messageGenerator.generate(responseObj, HttpStatus.OK);
			}

			int id = TimeOffTrackerDAO.getInstance().insert(vacation);
			vacation.setIdTimeOffTracker(id);

			collab.setLastVacationDate(LocalDate.now());
			responseObj.put("message", "this collaborator can takeoff");
			return messageGenerator.generate(responseObj, HttpStatus.OK);
			
		} else {
			return messageGenerator.generateUnauthorizedError();
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") int id, @RequestHeader("Authorization") String token) {
		return router.delete(id, token, TimeOffTrackerDAO.getInstance());
	}
}
