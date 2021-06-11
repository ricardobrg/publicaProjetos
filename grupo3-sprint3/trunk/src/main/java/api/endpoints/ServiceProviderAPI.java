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

import controllers.ServiceProviderController;
import models.entities.person.ServiceProvider;
import models.entities.security.AccessLevel;

/***
 * Class ServiceProviderAPI<br>
 * This class is the DTO for the application.<br>
 * It calls the controller in the methods and does the needed operations.<br>
 * It also handles errors.
 * 
 * @author Caio Shimada
 * @author Giovanni Buzzi
 * @author Jonathas Rocha
 * @author Nicole Taufenbach
 * 
 * @version 1.0.0
 */
@RestController
@RequestMapping("/service-providers")
@CrossOrigin
public class ServiceProviderAPI extends Api {

	ServiceProviderController providerController = new ServiceProviderController();
	ResponseEntity<Object> responseEntity;
	ServiceProvider provider;

	/***
	 * Insert<br>
	 * This method inserts a Service Provider.<br>
	 * It handles null entries and invalid values.<br>
	 * Otherwise, throws a success message.
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * @author Nicole Taufenbach
	 * 
	 * @param a service provider
	 * @return builds the "insert a service provider"
	 * 
	 */
	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> insert(@RequestBody ServiceProvider provider,
			@RequestHeader("Authorization") String token) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			if (!hasAccess(AccessLevel.MEDIUM, token))
				throw new SecurityException("Access Denied");

			if (provider.getCnpj() == null)
				throw new Exception("Null entry!");

			int index = providerController.insert(provider);
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
	 * This method finds a Service Provider via its ID.<br>
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
	 * @return builds the "find a service provider"
	 * 
	 * 
	 */
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> find(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			ServiceProvider provider = providerController.findById(Integer.parseInt(id));

			if (provider == null) {
				responseObj.put("error", "ID not found");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			} else {
				if (!hasCpfAccess(AccessLevel.MEDIUM, token, provider.getCnpj()))
					throw new SecurityException("Access Denied");

				responseEntity = new ResponseEntity<>(provider, HttpStatus.OK);
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
	 * @return builds the "update a service provider"
	 * 
	 */
	@PatchMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> update(@PathVariable("id") String id, ServiceProvider provider,
			@RequestHeader("Authorization") String token) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			if (!hasAccess(AccessLevel.MEDIUM, token))
				throw new SecurityException("Access Denied");
			
			ServiceProvider providerFound = providerController.findById(Integer.parseInt(id));
			if (providerFound == null) {
				responseObj.put("error", "ID not found!");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);

			} else {
				provider.setId(Integer.parseInt(id));
				int index = providerController.update(provider);
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
	 * Delete<br>
	 * This method deletes a Service Provider.<br>
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
	 * 
	 * @return builds the "delete a Service Provider"
	 * 
	 */
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> delete(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			if (!hasAccess(AccessLevel.MEDIUM, token))
				throw new SecurityException("Access Denied");

			int index = providerController.delete(Integer.parseInt(id));

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
	 * Replace<br>
	 * This method replaces a Service Provider.<br>
	 * Basically changes entirely a Service Provider.<br>
	 * It handles null entries. Otherwise, throws a success message.
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi
	 * @author Jonathas Rocha
	 * @author Nicole Taufenbach
	 * 
	 * @param String id - this way it can be converted to JSON (and will be parsed
	 *               in the try)
	 * 
	 * @return builds the "replace a Service Provider"
	 * 
	 */
	@PutMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> replace(@PathVariable("id") String id, 
										  @RequestBody ServiceProvider provider,
										  @RequestHeader("Authorization") String token) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			if (!hasAccess(AccessLevel.MEDIUM, token))
				throw new SecurityException("Access Denied");
			
			ServiceProvider providerFound = providerController.findById(Integer.parseInt(id));
			if (providerFound == null) {
				responseObj.put("error", "ID not found!");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);

			} else {
				provider.setId(Integer.parseInt(id));
				int index = providerController.update(provider);
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
	 * This method shows all Service Providers.<br>
	 * 
	 * @author Caio Shimada
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Jonathas Rocha <jonathasrochadesouza@gmail.com>
	 * @author Nicole Taufenbach
	 * 
	 * @return builds the "getAll Service Providers"
	 */
	@GetMapping(produces = "application/json")
	public ResponseEntity<Object> getAll(@RequestHeader("Authorization") String token) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			if (!hasAccess(AccessLevel.MEDIUM, token))
				throw new SecurityException("Access Denied");
			
			ArrayList<ServiceProvider> providers = providerController.getAll();
			responseEntity = new ResponseEntity<>(providers, HttpStatus.OK);

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
