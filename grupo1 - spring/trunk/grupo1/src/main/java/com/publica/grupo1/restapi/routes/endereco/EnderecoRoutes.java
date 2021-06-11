package com.publica.grupo1.restapi.routes.endereco;

import java.util.List;

import org.hibernate.Session;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.publica.grupo1.controller.endereco.EnderecoController;
import com.publica.grupo1.database.DBConnection;
import com.publica.grupo1.model.dao.endereco.EnderecoDAO;
import com.publica.grupo1.model.entities.endereco.Endereco;

/***
 * @author Pedro Vinicius Hostert <pedrohostertt@gmail.com>
 */
@RestController
@RequestMapping("/endereco")
public class EnderecoRoutes {
	
	Session session = DBConnection.getSession();
	EnderecoDAO dao = EnderecoDAO.getInstance(session);
	EnderecoController controller = new EnderecoController(dao);

	/**
	 * This Route returns a JSON containing all objects from database
	 * 
	 * @return JSON containing all object registers in database
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Endereco> getAll() {
		return controller.getAll();
	}
	
	/**
	 * This method finds an object by its ID and then returns it.
	 * 
	 * @param id    String: Number indicating the ID of the object
	 * @param token String: logged user token that will be validated
	 * @return A JSON of the found object
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Endereco selectById(@PathVariable("id") String id) {
		return controller.selectById(Integer.parseInt(id));
	}
	
	/**
	 * This method insert a new CEP informed in URL request in database,
	 * and creates a new Endereco object in with this CEP.
	 * 
	 * @param endereco
	 * @param cep
	 *  The example below shows how the JSON must be:
	 * <br>
	 * <code>
	 * <pre>
{
   "cep": "89035070",
   "localidade": "Blumenau",
   "bairro": "Vila Nova",
   "logradouro": "Tobias Barreto",
   "complemento": "Na rua do posto"
}
	 * </pre>
	 * </code>
	 * @return Object's ID on database
	 */
	@RequestMapping(value = "/{cep}", method = RequestMethod.POST)
	public String create(@RequestBody Endereco endereco, @PathVariable("cep") String cep) {
		int id = controller.insert(endereco, cep);
		return "{id : "+id+"}";
	}
	
	/**
	 * This method insert a new Endereco object in database
	 * 
	 * @param endereco
	 *  The example below shows how the JSON must be:
	 * <br>
	 * <code>
	 * <pre>
{
   "cep": "89035070",
   "localidade": "Blumenau",
   "bairro": "Vila Nova",
   "logradouro": "Tobias Barreto",
   "complemento": "Na rua do posto"
}
	 * </pre>
	 * </code>
	 * @return Object's ID on database
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(@RequestBody Endereco endereco) {
		int id = controller.insert(endereco);
		return "{id : "+id+"}";
	}
	
	/***
	 * Replaces object on database with <code>id</code> in the URL request
	 * with a object in the request body
	 * 
	 * @param endereco
	 * @param id
	 * @return The new Endereco
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Endereco update(@RequestBody Endereco endereco, @PathVariable("id") int id) {
		Endereco foundEndereco = controller.selectById(id);
		dao.getSession().clear();
		endereco.setId(id);
		foundEndereco = endereco;
		dao.update(foundEndereco);
		return foundEndereco;
	}
	
	/***
	 * Deletes a object from the database with the <code>id</code> in the 
	 * URL request.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") int id) {
		Endereco foundEndereco = controller.selectById(id);
		dao.delete(foundEndereco);
		return "{msg: \"deleted\"}";
	}	
}




















