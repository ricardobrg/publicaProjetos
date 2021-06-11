package com.publica.grupo1.model.entities.endereco;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.publica.grupo1.controller.services.ServicoCEP;
import com.publica.grupo1.util.UtilsNumber;

/***
 * Responsável por fazer a conexão com a API <code>viacep.com.br/ws</code>.
 * Atribui os valores de cep, localidade, bairro, logradouro e complemento
 * informados da API.
 * 
 * @version 1.0.0
 * 
 * @author Pedro Vinicius Hostert <pedrohostertt@gmail.com>
 */
@Entity
public class CEP {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String cep;
	private String localidade;
	private String bairro;
	private String logradouro;
	private String complemento;

	public CEP() {

	}	

	/***
	 * Recebe seus atributos da API viacep, a partir do String cep passado
	 * como parametro.
	 * 
	 * @param cep
	 */
	public CEP(String cep) {
		this.cep = UtilsNumber.onlyDigits(cep);
		getFromAPI();
	}

	/***
	 * Construtor que não depende da api, atribuindo os valores
	 * individualmente.
	 * 
	 * @param id
	 * @param cep
	 * @param localidade
	 * @param bairro
	 * @param logradouro
	 * @param complemento
	 */
	public CEP(int id, String cep, String localidade, String bairro, String logradouro, String complemento) {
		super();
		this.id = id;
		this.cep = cep;
		this.localidade = localidade;
		this.bairro = bairro;
		this.logradouro = logradouro;
		this.complemento = complemento;
	}

	private void getFromAPI() {
		try {
			CEP objCep = ServicoCEP.pegaCEP(cep);

			setLocalidade(objCep.getLocalidade());
			setBairro(objCep.getBairro());
			setLogradouro(objCep.getLogradouro());
			setComplemento(objCep.getComplemento());		
		} 
		catch (Exception e) {
			System.out.println("Esse é o erro: " + e);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@Override
	public String toString() {
		return cep;
	}
}
