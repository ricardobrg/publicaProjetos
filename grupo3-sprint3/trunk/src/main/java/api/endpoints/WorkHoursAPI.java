package api.endpoints;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.dto.WorkHoursDTO;
import controllers.WorkHoursController;
import models.entities.person.Collaborator;
import models.entities.security.AccessLevel;
import models.entities.workHours.WorkEntry;
import utils.convertions.DateConversions;

/**
 * <strong> Class WorkHoursAPI </strong> <br>
 * This class is the DTO for the WorkHours.<br>
 * It calls the <code> <a> WorkHoursController </a> </code> on the methods and
 * performs the necessary operations.<br>
 * It also handles errors related to the exception. <br>
 *
 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 *
 * @version 1.0.0
 */
@RestController
@RequestMapping("/work-hours")
@CrossOrigin
public class WorkHoursAPI extends Api {

	WorkHoursController workHoursController;
	ResponseEntity<Object> responseEntity;

	/**
	 * <strong> Insert </strong> <br>
	 * 
	 * Enter a new time in the database. <br>
	 * The time entered is given by the current time!<br>
	 * 
	 * Receives a Json that contains someone's 'CPF', the new point is directly
	 * linked to the 'CPF' registered in the database!
	 *
	 * @param collab - Receives a collaborator as a parameter
	 * @return build
	 *
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 */
	@PostMapping(value = "/clock", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> insert(@RequestBody Collaborator collab,
			@RequestHeader("Authorization") String token) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			if (!hasCpfEquals(token, collab.getCpf()))
				throw new SecurityException("Access Denied");

			if (collab.getCpf() == null) {
				throw new Exception("Null entry!"); // return a '400: Bad Request'
			}

			workHoursController = new WorkHoursController(collab.getCpf());

			int index = workHoursController.clockIn();
			if (index == -1)
				throw new Exception("Error inserting in database!");

			WorkEntry workEntry = workHoursController.find(index);

			responseObj.put("success", workEntry.getClock().toString());
			responseObj.put("collabName", workEntry.getCollaborator().getName());
			responseObj.put("id", String.valueOf(index));
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);

		} catch (SecurityException e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.UNAUTHORIZED);

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

	/**
	 * <strong> getAll </strong> <br>
	 * 
	 * Returns a list of points between the desired date! <br>
	 * Fetch point information based on employee's 'CPF' <br>
	 *
	 * @param cpf   String - Receives a 'CPF' as one of the parameters
	 * @param date1 String - Receives a start date as one of the parameters for the
	 *              date
	 * @param date2 String - Receives an end date as one of the parameters for the
	 *              date
	 * @return build
	 *
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 */
	@GetMapping(value = "/{cpf}/{date1}-{date2}", produces = "application/json")
	public ResponseEntity<Object> getAll(@PathVariable("cpf") String cpf, @PathVariable("date1") String date1,
			@PathVariable("date2") String date2, @RequestHeader("Authorization") String token) {

		Map<String, String> responseObj = new HashMap<>();
		try {
			if (!hasCpfAccess(AccessLevel.MEDIUM, token, cpf))
				throw new SecurityException("Access Denied");

			date1 = date1.replace(".", "-");
			date2 = date2.replace(".", "-");
			
			date1 = DateConversions.parseDate(date1);
			date2 = DateConversions.parseDate(date2);

			workHoursController = new WorkHoursController(cpf);

			ArrayList<LocalDateTime> localDate = workHoursController.showHours(date1, date2);
			int hours = workHoursController.calculateHours(date1, date2);

			WorkHoursDTO workHoursDTO = new WorkHoursDTO(localDate, hours);

			responseEntity = new ResponseEntity<>(workHoursDTO, HttpStatus.OK);

		} catch (SecurityException e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.UNAUTHORIZED);

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}

}
