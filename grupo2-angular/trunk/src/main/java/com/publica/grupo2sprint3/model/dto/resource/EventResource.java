package com.publica.grupo2sprint3.model.dto.resource;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.publica.grupo2sprint3.model.dao.EventDAO;
import com.publica.grupo2sprint3.model.person.physicalperson.Event;

@CrossOrigin
@RestController
@RequestMapping("/events")
public class EventResource {
	ResponseEntity<Object> responseEntity;
	Map<String, String> responseObj = new HashMap<>();
	
	
	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> insert(@RequestBody Event event) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			if (event.getName() == null)
				throw new Exception("Evento sem nome.");

			EventDAO.getInstance().add(event);

			responseObj.put("OK", "Evento cadastrado!");
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> find(@PathVariable("id") String id) {
		Map<String, String> responseObj = new HashMap<>();
		try {
			Event event = (Event) EventDAO.getInstance().findById(Integer.parseInt(id));

			if (event == null) {
				responseObj.put("NOT OK", "O ID informado nÃ£o foi encontrado");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			} else {
				responseEntity = new ResponseEntity<>(event, HttpStatus.OK);
			}

		} catch (Exception e) {
			responseObj.put("Erro: ", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}
	
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			boolean bool = EventDAO.getInstance().removeById(Integer.parseInt(id));
			
			if(bool) {
				responseObj.put("OK", "Evento deletado!");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);
			}
			else {
				responseObj.put("ERRO", "NÃ£o foi possÃ­vel deletar.");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
			}
			
		

		} catch (IllegalArgumentException e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}
	
	
	@PatchMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody Event event) {
		Map<String, String> responseObj = new HashMap<>();
		
		try {
			Event eventFound = (Event) EventDAO.getInstance().findById(Integer.parseInt(id));
			if (eventFound == null) {
				responseObj.put("NOT OK", "O ID informado nÃ£o foi encontrado");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);

			} else if (eventFound.getName() == event.getName()
					&& eventFound.getDescription().equals(event.getDescription())) {

				responseObj.put("OK", "Sem alteraÃ§Ãµes necessÃ¡rias");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NO_CONTENT);

			} else {
				event.setId(Integer.parseInt(id));
				EventDAO.getInstance().updateByIdentifier(eventFound.getName(), event);
				
				
				responseObj.put("OK", "Evento Atualizado!");
				responseObj.put("ID", id);
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);

			}

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}
	
	
	@GetMapping(produces = "application/json")
	public ResponseEntity<Object> getAll() {
		Map<String, String> responseObj = new HashMap<>();
		
		try {
			ArrayList<Event> events = EventDAO.getInstance().getAll();
			responseEntity = new ResponseEntity<>(events, HttpStatus.OK);

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}
}
