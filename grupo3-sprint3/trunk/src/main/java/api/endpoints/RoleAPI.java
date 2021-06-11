package api.endpoints;

import java.util.ArrayList;
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

import controllers.RoleController;
import models.entities.security.AccessLevel;
import models.entities.security.Role;

/***
 * Class RoleAPI<br>

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
@RequestMapping("/roles")
@CrossOrigin
public class RoleAPI extends Api {

	RoleController roleController = new RoleController();
	ResponseEntity<Object> responseEntity;

	/***
	 * Find<br>
	 * This method finds a role via its ID.<br>
	 * It handles null entries.<br>
	 * Otherwise, throws a success message.
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * @author Nicole Taufenbach
	 * 
	 * @param id String - this way it can be converted to JSON (and will be parsed
	 *           in the try)
	 * @return builds the "find a role"
	 */
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> find(
			@PathVariable("id") String id,
			@RequestHeader("Authorization") String token) {
		
		Map<String, String> responseObj = new HashMap<>();
		try {
			
			if (!hasAccess(AccessLevel.MEDIUM, token)) {
				throw new SecurityException("Access Denied");
			}

			Role role = roleController.find(Integer.parseInt(id));

			if (role == null) {
				responseObj.put("error", "ID Not Found");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			} else {
				responseEntity = new ResponseEntity<>(role, HttpStatus.OK);
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
	 * Insert<br>
	 * This method inserts a role.<br>
	 * It handles null entries.<br>
	 * Otherwise, throws a success message.
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * @author Nicole Taufenbach
	 * 
	 * @param role : Role
	 * @return builds the "insert a role"
	 */
	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> insert(
			@RequestBody Role role,
			@RequestHeader("Authorization") String token) {
		
		Map<String, String> responseObj = new HashMap<>();

		try {
			if (!hasAccess(AccessLevel.TOTAL, token))
				throw new SecurityException("Access Denied");
			
			if (role.getName() == null || role.getAccessLevel() == null)
				throw new Exception("Null entry!");

			int index = roleController.add(role);

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
	 * Delete<br>
	 * This method deletes a role.<br>
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
	 * @return builds the "delete a role"
	 */
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> delete(
			@PathVariable("id") String id,
			@RequestHeader("Authorization") String token) {
		
		Map<String, String> responseObj = new HashMap<>();
		
		try {
			if (!hasAccess(AccessLevel.TOTAL, token))
				throw new SecurityException("Access Denied");
			
			int index = roleController.delete(Integer.parseInt(id));
			
			responseObj.put("success", "Deleted from database!");
			responseObj.put("id", String.valueOf(index));
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);

		} catch (IllegalArgumentException e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
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
	 * This method updates a role.<br>
	 * Can change only some values (patch).<br>
	 * It handles null entries and invalid values.<br>
	 * Otherwise, throws a success message.
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi
	 * @author Jonathas Rocha
	 * @author Nicole Taufenbach
	 * 
	 * @param String id - this way it can be converted to JSON (and will be parsed
	 *               in the try)
	 * @param role Role
	 * 
	 * @return builds the "update a role"
	 */
	@PatchMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> update(
			@PathVariable("id") String id,
			@RequestBody Role role,
			@RequestHeader("Authorization") String token) {
		
		Map<String, String> responseObj = new HashMap<>();
		
		try {
			if (!hasAccess(AccessLevel.TOTAL, token))
				throw new SecurityException("Access Denied");
			
			Role roleFound = roleController.find(Integer.parseInt(id));
			if (roleFound == null) {
				responseObj.put("error", "ID not found!");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);

			} else if (roleFound.getAccessLevel() == role.getAccessLevel()
					&& roleFound.getName().equals(role.getName())) {

				responseObj.put("success", "Nothing to alter!");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NO_CONTENT);

			} else {
				role.setId(Integer.parseInt(id));
				int index = roleController.edit(role);
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
	 * This method replaces a role.<br>
	 * Basically changes entirely a role.<br>
	 * It handles null entries and invalid values. Otherwise, throws a success
	 * message.
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi
	 * @author Jonathas Rocha
	 * @author Nicole Taufenbach
	 * 
	 * @param String id - this way it can be converted to JSON (and will be parsed
	 * @param role Role 
	 * @return builds the "replace a role"
	 */
	@PutMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> replace(
			@PathVariable("id") String id,
			@RequestBody Role role,
			@RequestHeader("Authorization") String token) {
		
		Map<String, String> responseObj = new HashMap<>();
		
		try {
			if (!hasAccess(AccessLevel.MEDIUM, token))
				throw new SecurityException("Access Denied");

			Role roleFound = roleController.find(Integer.parseInt(id));
			if (roleFound == null) {
				responseObj.put("error", "ID not found!");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);

			} else {
				role.setId(Integer.parseInt(id));
				int index = roleController.edit(role);
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
	 * This method shows all roles.<br>
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * @author Nicole Taufenbach
	 * 
	 * @return builds the "getAll roles"
	 */
	@GetMapping(produces = "application/json")
	public ResponseEntity<Object> getAll(
			@RequestHeader("Authorization") String token) {

		Map<String, String> responseObj = new HashMap<>();

		try {
			if (!hasAccess(AccessLevel.MEDIUM, token))
				throw new SecurityException("Access Denied");

			ArrayList<Role> roles = roleController.getAll();
			responseEntity = new ResponseEntity<>(roles, HttpStatus.OK);

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
