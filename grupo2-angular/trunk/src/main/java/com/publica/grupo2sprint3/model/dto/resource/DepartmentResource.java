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

import com.publica.grupo2sprint3.model.dao.DepartmentDAO;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;

/***
 * Class DepartmentResource (API Restfull) <br>
 * 
 * This class (Department Resource) calls the controller (DepartmentController)
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
@RequestMapping("/departments")
public class DepartmentResource {
	ResponseEntity<Object> responseEntity;
	DepartmentDAO dao = DepartmentDAO.getInstance();
	Map<String, String> responseObj = new HashMap<>();

	/**
	 * Adds a new Department. <br>
	 * 
	 * If the department is null, an exception will be thrown. Else, an OK message
	 * will be returned. <br> 
	 * 
	 * {"name": "Exemplo", "manager": "null"}
	 * 
	 * @param department : Object
	 * 
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 * 
	 * @return responseEntity
	 */
	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> insert(@RequestBody Department department) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			if (department.getName() == null)
				throw new Exception("Departamento nulo");

			dao.add(department);

			responseObj.put("OK", "Departamento cadastro com sucesso");
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

	/**
	 * Search for a department by its id.<br>
	 * 
	 * If the department is null, an exception will be thrown. Else, the department
	 * object found will be returned.
	 * 
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 * 
	 * @param id int : department's id.
	 * @return
	 */
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> find(@PathVariable("id") String id) {
		Map<String, String> responseObj = new HashMap<>();
		try {
			Department department = (Department) dao.findById(Integer.parseInt(id));

			if (department == null) {
				responseObj.put("NOT OK", "O ID informado não foi encontrado");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			} else {
				responseEntity = new ResponseEntity<>(department, HttpStatus.OK);
			}
		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}


	/**
	 * Removes a department by its id.<br>
	 * 
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 * 
	 * @param id : int : department's id.
	 * @return
	 */
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			boolean bool = dao.removeById(Integer.parseInt(id));
			
			if(bool) {
				responseObj.put("OK", "Departamento deletado com sucesso");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);
			}
			else {
				responseObj.put("ERRO", "Não foi possível deletar.");
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

	/**
	 * Change the department name by its id. <br>
	 * 
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 * 
	 * @param id : int : Departments' id
	 * @param department : Object
	 * @return
	 */
	@PatchMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody Department department) {
		Map<String, String> responseObj = new HashMap<>();
		
		try {
			Department departmentFound = (Department) dao.findById(Integer.parseInt(id));
			if (departmentFound == null) {
				responseObj.put("error", "O ID informado não foi encontrado");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			} else {
				department.setId(Integer.parseInt(id));
				dao.updateById(0, department);
								
				responseObj.put("OK", "Departamento Atualizado");
				responseObj.put("ID", id);
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);
			}

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

	/**
	 * This method should return all existing Departments
	 * <br>
	 * 
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 * 
	 * @return an ArrayList of Departments
	 */
	@GetMapping(produces = "application/json")
	public ResponseEntity<Object> getAll() {
		Map<String, String> responseObj = new HashMap<>();
		try {
			ArrayList<Department> departments = dao.getAll();
			responseEntity = new ResponseEntity<>(departments, HttpStatus.OK);
		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
}
