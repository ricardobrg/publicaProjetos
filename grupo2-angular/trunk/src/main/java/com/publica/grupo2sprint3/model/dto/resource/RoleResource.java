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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.publica.grupo2sprint3.auth.util.JwtTokenUtil;
import com.publica.grupo2sprint3.controller.RoleController;
import com.publica.grupo2sprint3.model.dao.RoleDAO;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;
import com.publica.grupo2sprint3.model.person.physicalperson.Role;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;

/***
 * Class RoleResource (API Restfull) <br>
 * 
 * This class (Role Resource) calls the controller (RoleController)
 * for the methods bellow to be able to perform the necessary actions.
 * 
 * @version 1.0.0
 * 
 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com
 * @author Jessé Amaro <jesse.amaro7@gmail.com>
 * 
 */
@CrossOrigin
@RestController
@RequestMapping("/roles")
public class RoleResource {
	ResponseEntity<Object> responseEntity;
	Department department = new Department("Departamento");
	Role role = new Role("Role", department, 2000.00, AccessLevel.TOTAL);
	RoleController controller = RoleController.getInstance(new Collaborator("Carlos", role));
	RoleDAO dao = RoleDAO.getInstance();
	Map<String, String> responseObj = new HashMap<>();
	JwtTokenUtil utilToken = new JwtTokenUtil();
	
	
	/**
	 * Adds a new Role. <br>
	 * 
	 * If the role is null, an exception will be thrown. Else, an OK message
	 * will be returned. <br> 
	 * 
	 * 
	 * @param role : Object
	 * 
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 * 
	 * @return responseEntity
	 */
	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> insert(@RequestBody Role role) {
		Map<String, String> responseObj = new HashMap<>();
		
		try {
			if (role.getName() == null || role.getAccessLevel() == null)
				throw new Exception("Objeto nulo");

			boolean bool = dao.add(role);

			if (bool) {
				responseObj.put("OK", "Cargo adicionado");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);
			} else {
				responseObj.put("NOT OK", "Cargo não adicionado");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}

	/**
	 * Search for a role by its id.<br>
	 * 
	 * If the role is null, an exception will be thrown. Else, the role
	 * object found will be returned.
	 * 
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 * 
	 * @param id int : role's id.
	 * @return
	 */
	@PersistenceContext
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> find(@PathVariable("id") String id) {
		Map<String, String> responseObj = new HashMap<>();
		try {
			Role role = controller.find(Integer.parseInt(id));

			if (role == null) {
				responseObj.put("error", "ID Not Found");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			} else {
				responseEntity = new ResponseEntity<>(role, HttpStatus.OK);
			}

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

	/**
	 * Removes a role by its id.<br>
	 * 
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 * 
	 * @param id : int : role's id.
	 * @return
	 */
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			controller.remove(Integer.parseInt(id));

			responseObj.put("OK", "Cargo deletado");
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

	
	/**
	 * Change the role name by its id. <br>
	 * 
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 * 
	 * @param id : int : Role's id
	 * @param role : Object
	 * @return
	 */
	@PutMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody Role role) {
		try {
			Role roleFound = (Role) controller.find(Integer.parseInt(id));
			if (roleFound == null) {
				responseObj.put("NOT OK", "Id não encontrado :(");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);

			} else if (roleFound.getName() == role.getName()) {
				responseObj.put("OK", "Cargo já existente");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NO_CONTENT);

			} else {
				role.setId(Integer.parseInt(id));
				boolean result = controller.update(roleFound.getName(), role);
				if (result == false) {
					throw new Exception("Erro ao atualizar este Cargo.");

				} else {
					responseObj.put("OK", "Cargo atualizado");
					responseObj.put("id", id);
					responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);
				}
			}

		} catch (Exception e) {
			responseObj.put("Erro: ", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

	/**
	 * This method should return all existing Roles <br>
	 * 
	 * @author Ana
	 * @author Jessé
	 * 
	 * @return an ArrayList of Roles.
	 */
	@GetMapping(produces = "application/json")
	public ResponseEntity<Object> getAll(@RequestHeader("Authorization") String token) {
		Collaborator loggedCollab = utilToken.getCollaboratorWithToken(token);
		Map<String, String> responseObj = new HashMap<>();
		
		
		if (loggedCollab.checkAccessLevel(AccessLevel.BASIC)) {
			try {
				ArrayList<Role> roles = controller.getRoles();
				responseEntity = new ResponseEntity<>(roles, HttpStatus.OK);

			} catch (Exception e) {
				responseObj.put("error", e.getMessage());
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
			}
		} else {
			responseObj.put("error", "Permission Denied");
			responseEntity = new ResponseEntity<Object>(responseObj, HttpStatus.UNAUTHORIZED);
		}

		return responseEntity;
	}
}
