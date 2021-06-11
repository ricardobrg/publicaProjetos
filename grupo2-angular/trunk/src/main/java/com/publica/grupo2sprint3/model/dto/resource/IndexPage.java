package com.publica.grupo2sprint3.model.dto.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.publica.grupo2sprint3.model.dao.CollaboratorDAO;
import com.publica.grupo2sprint3.model.dao.DepartmentDAO;
import com.publica.grupo2sprint3.model.dao.RoleDAO;
import com.publica.grupo2sprint3.model.dao.ServiceProviderDAO;
import com.publica.grupo2sprint3.model.person.legalperson.ServiceProvider;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;
import com.publica.grupo2sprint3.model.person.physicalperson.Role;

/**
 * This class defines an endpoint for providing informations to
 * index page. Those informations will fill the graphs and cards.
 * 
 * @author Vinicius 
 */

@CrossOrigin
@RestController
@RequestMapping("/indexpage")
public class IndexPage {

	ResponseEntity<Object> responseEntity;

	@GetMapping(produces = "application/json")
	public ResponseEntity<Object> indexContent() {
		Map<String, String> responseObj = new HashMap<>();
		
		ArrayList<Collaborator> collabs = CollaboratorDAO.getInstance().getAll();
		ArrayList<ServiceProvider> serviceProviders = ServiceProviderDAO.getInstance().getAll();
		ArrayList<Department> departments = DepartmentDAO.getInstance().getAll();
		ArrayList<Role> roles = RoleDAO.getInstance().getAll();
		
		responseObj.put("collabAmount", String.valueOf(collabs.size()));
		responseObj.put("serviceProvidersAmount", String.valueOf(serviceProviders.size()));
		responseObj.put("departmentsAmount", String.valueOf(departments.size()));
		responseObj.put("rolesAmount", String.valueOf(roles.size()));
		
		responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);

		return responseEntity;
	}
}
