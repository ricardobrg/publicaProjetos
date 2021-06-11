package com.publica.grupo1.restapi.routes.serviceprovider;

import org.hibernate.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.model.dao.serviceprovider.ServiceProviderDAO;
import com.publica.grupo1.model.entities.serviceprovider.ServiceProvider;
import com.publica.grupo1.restapi.routes.GenericRouter;
import com.publica.grupo1.restapi.routes.message.MessageGenerator;
import com.publica.grupo1.restapi.routes.security.SecurityAPI;

/***
 * Endpoint of ServiceProvider. Contains <code>GET</code>, <code>POST</code>,
 * <code>PUT</code> and <code>DELETE</code> methods.
 * 
 * @author Pedro Vinicius Hostert <pedrohostertt@gmail.com>
 *
 */
@RestController
@RequestMapping("/serviceprovider")
public class ServiceProviderRoutes {

	Session session = DBConnection.getSession();
	SecurityAPI securityApi = new SecurityAPI();
	MessageGenerator messageGenerator = new MessageGenerator();
	GenericRouter<ServiceProvider> router = new GenericRouter<ServiceProvider>();

	/**
	 * This Route returns a JSON containing all objects from database
	 * 
	 * @return JSON containing all object registers in database
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> getAll(@RequestHeader("Authorization") String token) {
		return router.getAll(token, ServiceProviderDAO.getInstance(session));
	}

	/**
	 * This method finds an object by its ID and then returns it.
	 * 
	 * @param id    String: Number indicating the ID of the object
	 * @param token String: logged user token that will be validated
	 * @return A JSON of the found object
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> selectById(@PathVariable("id") int id, @RequestHeader("Authorization") String token) {
		return router.selectById(id, token, ServiceProviderDAO.getInstance(session));
	}

	/**
	 * This method creates a new object.
	 * 
	 * The example below shows how the JSON must be: <br>
	 * <code>
	 * <pre>
	{
	"id": 1,
	"nameFantasia": "Fantasy",
	"razaoSocial": "Fantasy LTDA.",
	"endereco": {
	"id": 1,
	"cep": {
	  "id": 1,
	  "cep": "12345000",
	  "localidade": "Localidade",
	  "bairro": "Bairro",
	  "logradouro": "Ribeirinho",
	  "complemento": "Do lado do bar"
	},
	"localidade": "Blumenau",
	"bairro": "Vila Nova",
	"logradouro": "Casa do zé",
	"complemento": "Limoeiro",
	"numero": 0
	},
	"contact": {
	"id": 1,
	"email": "josue@joestar.com",
	"phoneList": []
	},
	"cnpj": "80103725000144",
	"inscEstadual": null,
	"inscMunicipal": null,
	"serviceType": "Seguradoria"
	}
	 * </pre>
	 * </code>
	 * 
	 * @param serviceProvider
	 * @return Object's ID on database
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody ServiceProvider serviceProvider,
			@RequestHeader("Authorization") String token) {
		return router.create(serviceProvider, token, ServiceProviderDAO.getInstance(session));
	}

	/***
	 * Replaces object on database with <code>id</code> in the URL request with a
	 * object in the request body
	 * 
	 * @param serviceProvider
	 * @param id
	 * @return The new ServiceProvider
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@RequestBody ServiceProvider serviceProvider, @PathVariable("id") int id,
			@RequestHeader("Authorization") String token) {
		return router.update(serviceProvider, id, token, ServiceProviderDAO.getInstance(session));
	}

	/***
	 * Deletes a object from the database with the <code>id</code> in the URL
	 * request.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") int id, @RequestHeader("Authorization") String token) {
		return router.delete(id, token, ServiceProviderDAO.getInstance(session));
	}
}
