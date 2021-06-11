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

import com.publica.grupo2sprint3.controller.ProviderController;
import com.publica.grupo2sprint3.model.person.legalperson.ServiceProvider;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;

/***
 * Class ServiceProviderResource (API Restfull) <br>
 * 
 * This class (ServiceProvider Resource) calls the controller (ProviderController)
 * for the methods bellow to be able to perform the necessary actions.
 * 
 * @version 1.0.0
 * 
 * @author Ana
 */
@CrossOrigin
@RestController
@RequestMapping("/providers")
public class ServiceProviderResource {
	ResponseEntity<Object> responseEntity;
	ProviderController controller = ProviderController.getInstance(new Collaborator());
	Map<String, String> responseObj = new HashMap<>();

	/**
	 * Adds a new Service Provider. <br>
	 * 
	 * If the provider is null, an exception will be thrown. Else, an OK message
	 * will be returned. <br> 
	 * 
	 *   {
    		"name": "LipTalk Idiomas",
    		"contact": {
      		"phone": "(47) 99304-0360",
      		"email": "liptalkidiomas@gmail.com"
    		},
    		"address": null,
    		"cnpj": "92.234.149/0001-53",
    		"socialReason": "LipTalk Idiomas Teste",
    		"description": null,
    		"price": 500.0
  		}
	 * 
	 * @param provider : Object
	 * 
	 * @author Ana
	 * 
	 * @return
	 */
	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> insert(@RequestBody ServiceProvider provider) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			if (provider == null)
				throw new Exception("Prestador de serviço nulo");

			boolean bool = controller.add(provider);

			responseObj.put("Processamento realizado", "Inserido no banco de dados");
			responseObj.put("True: ",String.valueOf(bool));
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

	/**
	 * Search for a provider by its id.<br>
	 * 
	 * If the provider is null, an exception will be thrown. Else, the department
	 * object found will be returned.
	 * 
	 * @author Ana
	 * 
	 * @param id int : provider's id.
	 * @return
	 */
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> find(@PathVariable("id") String id) {
		Map<String, String> responseObj = new HashMap<>();
		try {
			ServiceProvider provider = (ServiceProvider) controller.find(Integer.parseInt(id));

			if (provider == null) {
				responseObj.put("Erro", "Id Não encontrado");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			} else {
				responseEntity = new ResponseEntity<>(provider, HttpStatus.OK);
			}

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}


	/**
	 * Removes a provider by its id.<br>
	 * 
	 * @author Ana
	 * 
	 * @param id : int : provider's id.
	 * @return
	 */
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			controller.remove(Integer.parseInt(id));
			responseObj.put("Sucesso", "Deletado do banco de dados");
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
	 * Change the provider name by its id. <br>
	 * 
	 * @author Ana
	 * 
	 * @param id : int : Provider's id
	 * @param provider : Object
	 * @return
	 */
	@PatchMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody ServiceProvider provider) {
		Map<String, String> responseObj = new HashMap<>();
		
		try {
			ServiceProvider providerFound = (ServiceProvider) controller.find(Integer.parseInt(id));
			if (providerFound == null) {
				responseObj.put("NOT OK", "O ID informado não foi encontrado");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);

			} else if (providerFound.getName() == provider.getName()
					&& providerFound.getCnpj().equals(provider.getCnpj())) {

				responseObj.put("OK", "Sem alterações necessárias");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NO_CONTENT);

			} else {
				provider.setId(Integer.parseInt(id));
				controller.update(providerFound.getName(), provider);
				
				
				responseObj.put("OK", "Prestador de Serviço Atualizado");
				responseObj.put("ID", id);
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);

			}

		} catch (Exception e) {
			responseObj.put("Erro ", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

	/**
	 * This method should return all existing Providers. <br>
	 * 
	 * @author Ana
	 * 
	 * @return an ArrayList of Providers
	 */
	@GetMapping(produces = "application/json")
	public ResponseEntity<Object> getAll() {
		Map<String, String> responseObj = new HashMap<>();

		try {
			ArrayList<ServiceProvider> providers = controller.getProviders();
			responseEntity = new ResponseEntity<>(providers, HttpStatus.OK);

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}
}
