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

import controllers.CollaboratorController;
import controllers.EventController;
import models.entities.events.Event;
import models.entities.person.Collaborator;
import models.entities.security.AccessLevel;

@RestController
@RequestMapping("/events")
@CrossOrigin
public class EventAPI extends Api {

	EventController eventController = new EventController();
	ResponseEntity<Object> responseEntity;

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> find(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {

		Map<String, String> responseObj = new HashMap<>();

		try {

			Event event = eventController.find(Integer.parseInt(id));

			if (event == null) {
				responseObj.put("error", "ID Not Found");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			} else {
				responseEntity = new ResponseEntity<>(event, HttpStatus.OK);
			}

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> insert(@RequestBody Event event, @RequestHeader("Authorization") String token) {

		Map<String, String> responseObj = new HashMap<>();

		try {

			if (event.getName() == null)
				throw new Exception("Null entry!");

			int index = eventController.add(event);

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

	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> delete(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {

		Map<String, String> responseObj = new HashMap<>();

		try {
			Collaborator creator = eventController.find(Integer.parseInt(id)).getCollaborator();
			if (!hasCpfEquals(token, creator.getCpf()))
				throw new SecurityException("Access Denied");

			int index = eventController.delete(Integer.parseInt(id));

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

	@PatchMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody Event event,
			@RequestHeader("Authorization") String token) {

		Map<String, String> responseObj = new HashMap<>();

		try {
			Collaborator creator = eventController.find(Integer.parseInt(id)).getCollaborator();
			if (!hasCpfEquals(token, creator.getCpf()))
				throw new SecurityException("Access Denied");

			Event eventFound = eventController.find(Integer.parseInt(id));
			if (eventFound == null) {
				responseObj.put("error", "ID not found!");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);

			} else if (eventFound.getAccessLevel() == event.getAccessLevel()
					&& eventFound.getName().equals(event.getName())) {

			} else {
				event.setId(Integer.parseInt(id));
				int index = eventController.edit(event);
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

	@PutMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> replace(@PathVariable("id") String id, @RequestBody Event event,
			@RequestHeader("Authorization") String token) {

		Map<String, String> responseObj = new HashMap<>();

		try {
			Collaborator creator = eventController.find(Integer.parseInt(id)).getCollaborator();
			if (!hasCpfEquals(token, creator.getCpf()))
				throw new SecurityException("Access Denied");

			Event eventFound = eventController.find(Integer.parseInt(id));
			if (eventFound == null) {
				responseObj.put("error", "ID not found!");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);

			} else {
				event.setId(Integer.parseInt(id));
				int index = eventController.edit(event);
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

	@GetMapping(produces = "application/json")
	public ResponseEntity<Object> getAll(@RequestHeader("Authorization") String token) {

		Map<String, String> responseObj = new HashMap<>();

		try {
			ArrayList<Event> event = eventController.getAll();
			responseEntity = new ResponseEntity<>(event, HttpStatus.OK);

		} catch (SecurityException e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

	@PostMapping(value = "/{id}/subscribe", produces = "application/json")
	public ResponseEntity<Object> subscribe(@PathVariable("id") String id,
			@RequestHeader("Authorization") String token) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			Event event = eventController.find(Integer.parseInt(id));

//			if (/*!hasAccess(event.getAccessLevel(), token)*/!hasAccess(AccessLevel.TOTAL, token))
//				throw new SecurityException("Access Denied");*/
			Collaborator collab = new CollaboratorController().findByCpf(getUser(token).getCpf());
			if (event.checkSubscribed(collab)) {
				throw new Exception("User Already Subscribred");

			}
			int index = eventController.insertCollab(collab, Integer.parseInt(id));

			responseObj.put("success", "Insert from database!");
			responseObj.put("id", String.valueOf(index));
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);

//		} catch (SecurityException e) {
//			responseObj.put("error", e.getMessage());
//			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

}
