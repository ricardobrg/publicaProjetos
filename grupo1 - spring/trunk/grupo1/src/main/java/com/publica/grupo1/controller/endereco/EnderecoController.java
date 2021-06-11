package com.publica.grupo1.controller.endereco;

import java.util.List;

import com.publica.grupo1.controller.Controller;
import com.publica.grupo1.model.dao.endereco.CEPDAO;
import com.publica.grupo1.model.dao.endereco.EnderecoDAO;
import com.publica.grupo1.model.entities.endereco.CEP;
import com.publica.grupo1.model.entities.endereco.Endereco;

/***
 * 
 * Controller for Endereco. <br>
 * Communicates with the EnderecoDAO and contains aditional protocols.
 * 
 * @author Pedro Vinicius Hostert <pedrohostertt@gmail.com>
 *
 */
public class EnderecoController implements Controller<Endereco> {

	private EnderecoDAO dao;
	private CEPDAO cepDAO;
	
	/***
	 * Recieves an EnderecoDAO on the controller.
	 * 
	 * @param EnderecoDAO
	 */
	public EnderecoController(EnderecoDAO dao) {
		this.dao = dao;
		cepDAO = CEPDAO.getInstance(dao.getSession());
	}

	/***
	 * Selects a Endereco from the EnderecoDAO with <code>id</code> 
	 * passed as parameter.
	 * 
	 * @param id
	 */
	@Override
	public Endereco selectById(int id) {
		return dao.selectById(id);
	}

	/***
	 * Inserts the Endereco passed as parameter with the EnderecoDAO
	 * 
	 * @param Endereco
	 */
	@Override
	public int insert(Endereco object) {
		return dao.insert(object);
	}
	
	/***
	 * Inserts the Endereco passed as parameter with the EnderecoDAO,
	 * after creating a new CEP object, requesting values from the 
	 * buscacep api.
	 * 
	 * @param object
	 * @param cep
	 */
	public int insert(Endereco object, String cep) {
		CEP enderecoCEP = new CEP(cep);
		
		int id = cepDAO.insert(enderecoCEP);
		enderecoCEP.setId(id);
		object.atribuiPelosValoresDoCep(enderecoCEP);
		object.setCep(enderecoCEP);
		
		return dao.insert(object);
	}
	
	/***
	 * Deletes Endereco passed as parameter with EnderecoDAO.
	 */
	@Override
	public void delete(Endereco object) {
		dao.delete(object);
	}

	/***
	 * Updates Endereco passed as parameter with EnderecoDAO.
	 * Depends on the <code>id</code> set in Endereco to override object
	 * in database.
	 */
	@Override
	public void update(Endereco object) {
		dao.update(object);
	}

	/***
	 * @returns a List with all the Endereco objects registered in the database
	 * with EnderecoDAO.
	 */
	@Override
	public List<Endereco> getAll() {
		return dao.getAll();
	}
}
























