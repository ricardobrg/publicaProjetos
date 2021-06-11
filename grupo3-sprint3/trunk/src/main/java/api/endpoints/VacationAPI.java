package api.endpoints;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.dto.VacationDTO;
import controllers.CollaboratorController;
import controllers.VacationController;
import models.entities.person.Collaborator;
import models.entities.security.AccessLevel;
import utils.convertions.DateConversions;

/***
 * Class VacationAPI<br>
 * This class is the DTO for the application.<br>
 * It calls the controller in the methods and does the needed operations.<br>
 * It also handle errors.
 * 
 * @version 1.0.0
 * 
 * @author Caio Shimada
 * @author Giovanni Buzzi
 * @author Jonathas Rocha
 * @author Nicole Taufenbach
 */
@RestController
@RequestMapping("/vacations")
@CrossOrigin
public class VacationAPI extends Api {
	VacationDTO vacDTO = new VacationDTO();

	VacationController vacationController = new VacationController();
	ResponseEntity<Object> responseEntity;

	/***
	 * Finds a vacation related to a collaborator. If the entry is valid, it returns
	 * a success message. Otherwise, throws an error.
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi
	 * @author Jonathas Rocha
	 * @author Nicole Taufenbach
	 * 
	 * @param cpf  the collaborator's CPF
	 * @param date the closing day of vacation
	 * @return the collaborator's vacation
	 */
	@GetMapping(value = "/{cpf}-{date}", produces = "application/json")
	public ResponseEntity<Object> find(@PathVariable("cpf") String cpf, @PathVariable("date") String date, @RequestHeader("Authorization") String token) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			date = date.replace(".", "-");
			date = DateConversions.parseDate(date);
			
			if (!hasCpfAccess(AccessLevel.MEDIUM, token, cpf)) 
				throw new SecurityException("Access Denied");
			
			vacDTO = vacationController.getVacationList(cpf, date);
			responseEntity = new ResponseEntity<>(vacDTO, HttpStatus.OK);

		} catch (SecurityException e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.UNAUTHORIZED);

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

	/***
	 * Returns vacation info about a collaborator Changes the lastVacationDate field
	 * to the sum of the number of vacation days the collaborator is allowed to have
	 * and the date they wish. If the entry is valid, it returns a success message.
	 * Otherwise, throws an error.
	 * 
	 * @author Giovanni Buzzi
	 * @author Nicole Taufenbach
	 * 
	 * @param cpf  the collaborator's CPF
	 * @param date the closing day of vacation
	 * @return the collaborator's vacation
	 */
	@PostMapping(value = "/allowed/{cpf}-{date}", produces = "application/json")
	public ResponseEntity<Object> allowedFind(@PathVariable("cpf") String cpf, @PathVariable("date") String date, @RequestHeader("Authorization") String token) {
		Map<String, String> responseObj = new HashMap<>();
		
		CollaboratorController collabController = new CollaboratorController();

		try {
			date = date.replace(".", "-");
			date = DateConversions.parseDate(date);
			
			if (!hasAccess(AccessLevel.MEDIUM, token)) 
				throw new SecurityException("Access Denied");
			
			Collaborator collab = collabController.findByCpf(cpf);
			vacDTO = vacationController.getVacationList(cpf, date);
			LocalDate localDate = DateConversions.convertDate(date);
			
			if (vacDTO.getCanVacation()) {collab.setLastVacationDate(
										DateConversions.parseDate(
										localDate.plusDays(vacDTO.getVacationDays()).toString()));
				
				collabController.update(collab);
				responseObj.put("returnDate", collab.getLastVacationDate());
				
			} else if (collab.getInVacation()) {
				throw new Exception("O Colaborador já está em férias.");			
			} else {
				throw new Exception("O Colaborador não pode tirar férias.");
			}

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

}
