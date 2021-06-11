package com.publica.grupo2sprint3.model.dto.resource;

import java.time.LocalDate;
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

import com.publica.grupo2sprint3.model.dao.CollaboratorDAO;
import com.publica.grupo2sprint3.model.dao.PayrollDAO;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Payroll;

/***
 * "API" for take Payroll data
 * 
 * @author Diego Borba <diegoborba25@gmail.com>
 * @version 1.0.0
 */
@CrossOrigin
@RestController
@RequestMapping("/payroll")
public class PayrollResource {

	ResponseEntity<Object> responseEntity;
	CollaboratorDAO daoCollab = CollaboratorDAO.getInstance();
	PayrollDAO dao = PayrollDAO.getInstance();

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> find(@PathVariable("id") String id) {
		Map<String, String> responseObj = new HashMap<>();
		try {
			Payroll payroll = (Payroll) dao.findById(Integer.parseInt(id));

			if (payroll == null) {
				responseObj.put("error", "ID Not Found");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			} else {
				responseEntity = new ResponseEntity<>(payroll, HttpStatus.OK);
			}

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}
	
	@PostMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> insert(@PathVariable("id") String str_id) {
		Map<String, String> responseObj = new HashMap<>();
		try {
			int id = Integer.parseInt(str_id);
			Collaborator collab = CollaboratorDAO.getInstance().findById(id);
			Payroll payroll = new Payroll(LocalDate.now(), collab);

			PayrollDAO dao = PayrollDAO.getInstance();
			dao.add(payroll);
			responseObj.put("Success", "Inserted in database!");
			responseObj.put("id", String.valueOf(payroll.getId()));
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

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
	public ResponseEntity<Object> replace(@PathVariable("id") String id, @RequestBody Payroll payroll) {
		Map<String, String> responseObj = new HashMap<>();
		
		try {
			Payroll payrollFound = (Payroll) dao.findById(Integer.parseInt(id));
			if (payrollFound == null) {
				responseObj.put("error", "ID not found!");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);

			} else {
				payroll.setId(Integer.parseInt(id));
				boolean bool = dao.updateById(Integer.parseInt(id), payroll);
				if (bool) {
					responseObj.put("success", "Updated from database!");
					responseObj.put("id", id);
					responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);
				} else {
					throw new Exception("Error updating from database!");
				}
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
			ArrayList<Payroll> payrolls = dao.getAll();
			responseEntity = new ResponseEntity<>(payrolls, HttpStatus.OK);

		}catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}
}