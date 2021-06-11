package com.publica.grupo2sprint3.model.dto.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.PersistenceContext;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.publica.grupo2sprint3.model.dao.ContactDAO;
import com.publica.grupo2sprint3.model.person.Contact;

/***
 * "API" for take contact data
 * 
 * @author Diego Borba <diegoborba25@gmail.com>
 * @version 1.0.0
 */

@CrossOrigin
@RestController
@RequestMapping("/contact")
public class ContactResource {

	ResponseEntity<Object> responseEntity;
	ContactDAO dao = ContactDAO.getInstance();

	/**
	 * Adds a new Contact. <br>
	 * 
	 * If the contact is null, an exception will be thrown. Else, an OK message will
	 * be returned. <br>
	 * 
	 * @param contact : Object
	 * 
	 * @author Diego
	 * 
	 * @return responseEntity
	 */
	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> create(@RequestBody Contact contact) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			dao.add(contact);
			responseObj.put("Success", "Inserted in database!");
			responseObj.put("id", String.valueOf(contact.getId()));
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

	/**
	 * Search for a contact by its id.<br>
	 * 
	 * If the contact is null, an exception will be thrown. Else, the contact object
	 * found will be returned.
	 * 
	 * @author Diego
	 * 
	 * @param id int : contact's id.
	 * @return
	 */
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> read(@PathVariable("id") int id) {
		Map<String, String> responseObj = new HashMap<>();
		try {
			Contact contact = (Contact) dao.findById(id);

			if (contact == null) {
				responseObj.put("error", "ID Not Found");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			} else {
				responseEntity = new ResponseEntity<>(contact, HttpStatus.OK);
			}

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

	// Delete is not used in contact, because if collaborator has deleted, the
	// contact automatically is deleted.
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			dao.removeById(Integer.parseInt(id));

			responseObj.put("success", "Deleted from database!");
			responseObj.put("id", String.valueOf(id));
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);

		} catch (IllegalArgumentException e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

	@PersistenceContext
	@PutMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> update(@PathVariable("id") int id, @RequestBody Contact contact) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			boolean bool = dao.updateById(id, contact);
			if (bool) {
				responseObj.put("success", "Updated from database!");
				responseObj.put("id", String.valueOf(id));
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);

			} else {
				throw new Exception("Error updating from database!");
			}
		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}

	/**
	 * This method should return all existing Contacts <br>
	 * 
	 * @author Diego
	 * 
	 * @return an ArrayList of Contacts.
	 */
	@GetMapping(produces = "application/json")
	public ResponseEntity<Object> getAll() {
		Map<String, String> responseObj = new HashMap<>();

		try {
			ArrayList<Contact> contacts = dao.getAll();
			responseEntity = new ResponseEntity<>(contacts, HttpStatus.OK);

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}
}
