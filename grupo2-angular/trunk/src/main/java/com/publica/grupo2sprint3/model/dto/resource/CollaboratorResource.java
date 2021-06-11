package com.publica.grupo2sprint3.model.dto.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.publica.grupo2sprint3.model.dao.AddressDAO;
import com.publica.grupo2sprint3.model.dao.CollaboratorDAO;
import com.publica.grupo2sprint3.model.dao.ContactDAO;
import com.publica.grupo2sprint3.model.dto.CollaboratorDTO;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Vacation;

@CrossOrigin
@RestController
@RequestMapping("/collaborator")
public class CollaboratorResource {

	ContactResource contactResource;
	AddressResource addressResource;
	Collaborator collab;
	Contact contact;
	CollaboratorDTO dtoCollab;
	CollaboratorDAO daoCollab = CollaboratorDAO.getInstance();
	AddressDAO daoAddress = AddressDAO.getInstance();
	ContactDAO daoContact = ContactDAO.getInstance();
	ResponseEntity<Object> responseEntity;
	Map<String, String> responseObj = new HashMap<>();

	/**
	 * This method is used to create a new Collaborator
	 * 
	 * @param collab : an collaborator's object
	 * @return a response with true if everything happened good
	 * 			or an error if something went wrong
	 */
	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> create(@RequestBody Collaborator collab) {
		try {
			if ((collab.getName().isEmpty() || collab.getName().equals(null)) 
					|| (collab.getCpf().isEmpty() || collab.getCpf().equals(null))
					|| (collab.getAdmissionDate().isEmpty() || collab.getAdmissionDate().equals(null))
					|| (collab.getPis().isEmpty() || collab.getPis().equals(null))) {
				throw new Exception("Os campos: nome, cpf, pis e data de admissão precisam ser preenchidos!");
			}
			daoCollab.add(collab);
			responseObj.put("id", String.valueOf(collab.getId()));
			responseObj.put("Processamento realizado", "Inserido no banco de dados");
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);
		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}

	/**
	 * This method is used to get a Collaborator
	 * 
	 * @param id
	 * @return a collaborator's object that corresponds to the referred id
	 * 			if the id exists
	 */
	@GetMapping(value = "/{id}", produces = "application/json")
	@ResponseBody
	public  ResponseEntity<Object> read(@PathVariable("id") int id) {
		try {
			dtoCollab = new CollaboratorDTO(daoCollab.findById(id), false);
			if (dtoCollab.equals(null)) {
				responseObj.put("Erro" , "ID não foi encontrado");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			} else {
				
				responseEntity = new ResponseEntity<>(daoCollab.findById(id), HttpStatus.OK);
//				responseEntity = new ResponseEntity<>(dtoCollab, HttpStatus.OK);
			}
		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	
	/**
	 * This method is used to get a Collaborator 
	 * Vacations var's
	 * 
	 * @param id
	 * @return collab vacation var's
	 */
	@GetMapping(value = "/{id}/vacationVars", produces = "application/json")
	@ResponseBody
	public  ResponseEntity<Object> readVacation(@PathVariable("id") int id) {
		try {
			Collaborator collab = ((Collaborator) (daoCollab.findById(id)));
			Double result = Vacation.vacationPayment(collab.getGrossSalary(), Integer.parseInt(collab.getVacationSize()), false);
			responseObj.put("id", String.valueOf(collab.getId()));
			responseObj.put("name", collab.getName());
			responseObj.put("inVacation", String.valueOf(collab.getInVacation()));
			responseObj.put("vacationSize", String.valueOf(collab.getVacationSize()));
			responseObj.put("vacationSalary", result.toString());
			if (result.equals(null)) {
				responseObj.put("Erro" , "ID não foi encontrado");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			} else {
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);
			}
		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	
	/**
	 * This method is used to get a Collaborator 
	 * Vacations var's
	 * 
	 * @param id
	 * @return collab vacation var's
	 */
	@GetMapping(value = "/vacationVars", produces = "application/json")
	@ResponseBody
	public  ResponseEntity<Object> readVacations() {
		try {
			ArrayList<Map<String, String>> output = new ArrayList<Map<String, String>>();
			for(Collaborator collab : daoCollab.getAll()) {
				Map<String, String> responseObj = new HashMap<>();
				Double result = Vacation.vacationPayment(collab.getGrossSalary(), Integer.parseInt(collab.getVacationSize()), false);
				responseObj.put("id", String.valueOf(collab.getId()));
				responseObj.put("name", collab.getName());
				responseObj.put("inVacation", String.valueOf(collab.getInVacation()));
				responseObj.put("vacationSize", String.valueOf(collab.getVacationSize()));
				responseObj.put("vacationSalary", result.toString());
				output.add(responseObj);
			}
			
			
			if (output.equals(null)) {
				responseObj.put("Erro" , "ID não foi encontrado");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			} else {
				responseEntity = new ResponseEntity<>(output, HttpStatus.OK);
			}
		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}

	/**
	 * This method is used to do a complete update in a Collaborator
	 * 
	 * @param id 
	 * @param collab : a collaborator's object
	 * @return a successful response if the informations were updated
	 * 			or nothing happens if some information is missing
	 */
	@PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> update(@PathVariable("id") int id, @RequestBody Collaborator collab) {
		if(collab.getContact().equals(null))			
			collab.setContact(daoCollab.findById(id).getContact());
		else 
			daoContact.updateById(daoCollab.findById(id).getContact().getId(), collab.getContact());
		
		if(collab.getAddress().equals(null)) 				
			collab.setAddress(daoCollab.findById(id).getAddress());
		else
			daoAddress.updateById(daoCollab.findById(id).getAddress().getId(), collab.getAddress());
		
		try {
			if ((collab.getName().equals(null) || collab.getName().isEmpty()) 
					|| (collab.getCpf().isEmpty() || collab.getCpf().equals(null))
					|| (collab.getAdmissionDate().isEmpty() || collab.getAdmissionDate().equals(null))) {
				throw new Exception("Os campos: nome, cpf, pis e data de admissão precisam ser preenchidos!");
			}
			daoCollab.updateById(id, collab);
			responseObj.put("Processamento realizado", "Colaborador atualizado no banco de dados");
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);
		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}

	/**
	 * This method is used to delete a Collaborator
	 * 
	 * @param id
	 * @return true if the Collaborator was deleted
	 * 			or an error if something went wrong
	 */
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> delete(@PathVariable("id") int id) {
		try {
			responseObj.put("success", "Deleted from database!");
			responseObj.put("id", String.valueOf(daoCollab.removeById(id)));
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	
	/**
	 * This method is used to get a Collaborator
	 * 
	 * @param id
	 * @return a collaborator's object that corresponds to the referred id
	 * 			if the id exists
	 */
	@GetMapping(value = "/vacation/{id}", produces = "application/json")
	@ResponseBody
	public  ResponseEntity<Object> getVacation(@PathVariable("id") int id) {
		try {
			dtoCollab = new CollaboratorDTO(daoCollab.findById(id), true);
			if (dtoCollab.equals(null)) {
				responseObj.put("Erro" , "ID não foi encontrado");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			} else {
				responseEntity = new ResponseEntity<>(dtoCollab, HttpStatus.OK);
			}
		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	
	
	/**
	 * This method should return all existing Collaborators
	 * <br>
	 * 
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com
	 * 
	 * @return an ArrayList of Collaborators
	 */
	@GetMapping(value="", produces = "application/json")
	public ResponseEntity<Object> getAll() {
		Map<String, String> responseObj = new HashMap<>();
		
		try {
			ArrayList<Collaborator> collabs = daoCollab.getAll();
			responseEntity = new ResponseEntity<>(collabs, HttpStatus.OK);

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}
	
}
