package api.endpoints;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import controllers.CollaboratorController;
import models.entities.person.Collaborator;
import models.entities.security.AccessLevel;

/***
 * Class CollaboratorAPI<br>
 * This class is the DTO for the application.<br>
 * It calls the controller in the methods and does the needed operations.<br>
 * It also handle errors.
 * 
 * @author Caio Shimada
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
 * @author Nicole Taufenbach
 * 
 * @version 1.0.0
 * @param <T>
 */
@RestController
@RequestMapping("/collaborators")
@CrossOrigin
public class CollaboratorAPI extends Api {

	CollaboratorController collabController = new CollaboratorController();
	ResponseEntity<Object> responseEntity;
	Collaborator collab;

	/***
	 * Insert<br>
	 * This method inserts a collaborator.<br>
	 * It handles null entries and invalid values.<br>
	 * Otherwise, throws a success message.
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * @author Nicole Taufenbach
	 * 
	 * @param Collaborator : collab
	 * @return builds the "insert a collaborator"
	 */
	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> insert(@RequestBody Collaborator collab,
			@RequestHeader("Authorization") String token) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			if (!hasAccess(AccessLevel.MEDIUM, token))
				throw new SecurityException("Access Denied");

			if (collab.getCpf() == null || collab.getRole() == null)
				throw new Exception("Null entry!");
			
			//collab.setAdmissionDate(DateConversions.parseDate(collab.getAdmissionDate()));
			
			int index = collabController.insert(collab);
			if (index == -1)
				throw new Exception("Error inserting in database!");
			
			
			responseObj.put("success", "Inserted in database!");
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

	/***
	 * Find<br>
	 * This method finds a collaborator via its ID.<br>
	 * It handles null entries.<br>
	 * Otherwise, throws a success message.
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * @author Nicole Taufenbach
	 * 
	 * @param String id - this way it can be converted to JSON (and will be parsed
	 *               in the try)
	 * @return builds the "find a collaborator"
	 */
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> find(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			Collaborator collab = collabController.findById(Integer.parseInt(id));

			if (collab == null) {
				responseObj.put("error", "ID not found");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			} else {
				if (!hasCpfAccess(AccessLevel.MEDIUM, token, collab.getCpf()))
					throw new SecurityException("Access Denied");

				responseEntity = new ResponseEntity<>(collab, HttpStatus.OK);
			}

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
	 * Delete<br>
	 * This method deletes a collaborator.<br>
	 * It handles invalid entries.<br>
	 * Otherwise, throws a success message.
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi
	 * @author Jonathas Rocha
	 * @author Nicole Taufenbach
	 * 
	 * @param String id - this way it can be converted to JSON (and will be parsed
	 *               in the try)
	 * @return builds the "delete a collaborator"
	 */
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> delete(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			if (!hasAccess(AccessLevel.MEDIUM, token))
				throw new SecurityException("Access Denied");

			int index = collabController.delete(Integer.parseInt(id));

			if (index == -1)
				throw new Exception("Error deleting from database!");

			responseObj.put("success", "Deleted from database!");
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

	/***
	 * Update<br>
	 * This method updates a collaborator.<br>
	 * Can change only some values (patch)<br>
	 * It handles null entries and invalid values. Otherwise, throws a success
	 * message.
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi
	 * @author Jonathas Rocha
	 * @author Nicole Taufenbach
	 * 
	 * @param String id - this way it can be converted to JSON (and will be parsed
	 *               in the try)
	 * @param collab Collaborator
	 * @return builds the "update a collaborator"
	 * 
	 */
	@PatchMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody Collaborator collab,
			@RequestHeader("Authorization") String token) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			if (!hasAccess(AccessLevel.MEDIUM, token))
				throw new SecurityException("Access Denied");

			Collaborator collabFound = collabController.findById(Integer.parseInt(id));
			if (collabFound == null) {
				responseObj.put("error", "ID not found!");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);

			} else if (collabFound.getName().equals(collab.getName()) && collabFound.getCpf().equals(collab.getCpf())
					&& collabFound.getAdmissionDate().equals(collab.getAdmissionDate())
					&& collabFound.getContact().equals(collab.getContact())
					&& collabFound.getEndereco().equals(collab.getEndereco())
					&& collabFound.getLastVacationDate().equals(collab.getLastVacationDate())
					&& collabFound.getPis().equals(collab.getPis()) && collabFound.getRole().equals(collab.getRole())
					&& collabFound.getPwdHash().equals(collab.getPwdHash())
					&& collabFound.getSalary() == collab.getSalary()
					&& collabFound.getWorkHours() == collab.getWorkHours()) {

				responseObj.put("success", "Nothing to alter!");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NO_CONTENT);

			} else {
				collab.setId(Integer.parseInt(id));
				int index = collabController.update(collab);
				if (index == -1) {
					throw new Exception("Error updating from database!");

				} else {
					responseObj.put("success", "Updated from database!");
					responseObj.put("id", id);
					responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);
				}
			}

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
	 * Replace<br>
	 * This method replaces a collaborator.<br>
	 * Basically changes entirely a collaborator.<br>
	 * It handles null entries and invalid values. Otherwise, throws a success
	 * message.
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi
	 * @author Jonathas Rocha
	 * @author Nicole Taufenbach
	 * 
	 * @param id     String - this way it can be converted to JSON (and will be
	 *               parsed in the try)
	 * @param collab Collaborator
	 * @return builds the "replace a collaborator"
	 */
	@PutMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> replace(@PathVariable("id") String id, @RequestBody Collaborator collab,
			@RequestHeader("Authorization") String token) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			if (!hasAccess(AccessLevel.MEDIUM, token))
				throw new SecurityException("Access Denied");

			Collaborator collabFound = collabController.findById(Integer.parseInt(id));
			if (collabFound == null) {
				responseObj.put("error", "ID not found!");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);

			} else {
				collab.setId(Integer.parseInt(id));
				int index = collabController.update(collab);
				if (index == -1) {
					throw new Exception("Error updating from database!");

				} else {
					responseObj.put("success", "Updated from database!");
					responseObj.put("id", id);
					responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);
				}
			}

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
	 * GetAll<br>
	 * This method shows all collaborators.<br>
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * @author Nicole Taufenbach
	 * 
	 * @return builds the "getAll collaborators"
	 */
	@GetMapping(produces = "application/json")
	public ResponseEntity<Object> getAll(@RequestHeader("Authorization") String token) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			if (!hasAccess(AccessLevel.MEDIUM, token))
				throw new SecurityException("Access Denied");

			responseEntity = new ResponseEntity<>(collabController.getAll(), HttpStatus.OK);

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
