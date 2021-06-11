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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.publica.grupo2sprint3.model.dao.AddressDAO;
import com.publica.grupo2sprint3.model.person.Address;
@CrossOrigin
@RestController
@RequestMapping("/addresses")
public class AddressResource {
	ResponseEntity<Object> responseEntity;
	AddressDAO daoAddress = AddressDAO.getInstance();
	Map<String, String> responseObj = new HashMap<>();

	/**
	 * This method is used to create a new address
	 * 
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
	 * 
	 * @param address : an address object
	 * @return responseEntity
	 * 
	 */
	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> insert(@RequestBody Address address) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			if (address.getCep() == null)
				throw new Exception("Endereço nulo");

			AddressDAO.getInstance().add(address);

			responseObj.put("Processamento realizado", "Inserido no banco de dados");
			responseObj.put("id", String.valueOf(address.getId()));
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.OK);

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

	/**
	 * This method is used to find a address
	 * 
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
	 * 
	 * @param address : an address object
	 * @return responseEntity
	 * 
	 */
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> find(@PathVariable("id") String id) {

		Map<String, String> responseObj = new HashMap<>();
		try {
			Address result = (Address) AddressDAO.getInstance().findById(Integer.parseInt(id));

			if (result.equals(null)) {
				responseObj.put("Erro", "ID não foi encontrado");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			} else {
				responseEntity = new ResponseEntity<>(result, HttpStatus.OK);
			}

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

	/**
	 * This method is used to delete a specific address
	 * 
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
	 * 
	 * @param address : an address object
	 * @return responseEntity
	 * 
	 */
	@PersistenceContext
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		Map<String, String> responseObj = new HashMap<>();

		try {
			boolean bool = AddressDAO.getInstance().removeById(Integer.parseInt(id));

			responseObj.put("success", "Deleted from database!");
			responseObj.put("id", String.valueOf(bool));
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
	 * This method is used to update a specific address
	 * 
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
	 * 
	 * @param address : an address object
	 * @return responseEntity
	 * 
	 */
	@PatchMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> update(@PathVariable("id") int id, @RequestBody Address address) {
		try {
			Address addressFound = (Address) AddressDAO.getInstance().findById(id);
			if (addressFound == null) {
				responseObj.put("Erro", "Id n�o encontrado :(");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);

			} else if (addressFound.getComplement() == address.getComplement()) {
				responseObj.put("Sucesso", "Por�m, Complemento j� existente");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NO_CONTENT);

			} else {
				address.setId(id);

				Boolean result = AddressDAO.getInstance().updateById(id, (Address) address);
				if (result == false) {
					throw new Exception("Erro ao atualizar este complemento.");

				} else {
					responseObj.put("Sucesso", "Endereco atualizado");
					responseObj.put("id", String.valueOf(id));
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
	 * This method is used to update a specific address
	 * 
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
	 * 
	 * @param address : an address object
	 * @return responseEntity
	 * 
	 */
	@PutMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Object> replace(@PathVariable("id") int id, @RequestBody Address address) {
		try {
			Address addressFound = (Address) AddressDAO.getInstance().findById(id);
			if (addressFound == null) {
				responseObj.put("Erro", "Id nao encontrado :(");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);

			} else if (addressFound.getComplement() == address.getComplement()) {
				responseObj.put("Sucesso", "Por�m, Complemento j� existente");
				responseEntity = new ResponseEntity<>(responseObj, HttpStatus.NO_CONTENT);

			} else {
				address.setId(id);

				Boolean result = AddressDAO.getInstance().updateById(id, (Address) address);
				if (result == false) {
					throw new Exception("Erro ao atualizar este complemento.");

				} else {
					responseObj.put("Sucesso", "Endereco atualizado");
					responseObj.put("id", String.valueOf(id));
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
	 * This method is used to get all the addresses
	 * 
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
	 * 
	 * @param address : an address object
	 * @return responseEntity
	 * 
	 */
	@PersistenceContext
	@GetMapping(produces = "application/json")
	public ResponseEntity<Object> getAll() {
		Map<String, String> responseObj = new HashMap<>();

		try {
			ArrayList<Address> addresses = AddressDAO.getInstance().getAll();
			responseEntity = new ResponseEntity<>(addresses, HttpStatus.OK);

		} catch (Exception e) {
			responseObj.put("error", e.getMessage());
			responseEntity = new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}
}