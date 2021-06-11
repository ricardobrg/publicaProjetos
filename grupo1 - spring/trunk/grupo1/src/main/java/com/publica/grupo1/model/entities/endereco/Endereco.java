package com.publica.grupo1.model.entities.endereco;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/***
 * Um objeto com informações de endere�o.
 * 
 * Este objeto contém informações sobre endereço, com um cep,
 * localidade(cidade), bairro, logradouro e um complemento, todos em String.
 * Extrai as demais informações através do objeto CEP. Também é possível definir
 * essas informações com os setters do objeto.
 * 
 * @version 1.2.3
 * 
 * @author Pedro Vinicius Hostert <pedrohostertt@gmail.com>
 */

@Entity
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	private CEP cep;
	
	private String localidade;
	private String bairro;
	private String logradouro;
	private String complemento;
	
	private int numero;

	public Endereco() {

	}
	
	/***
	 * Construtor que recebe somente o cep, e recebe as informa��es de
	 * ServicoCEP.java. Somente utilizado no momento do cadastro.
	 * 
	 * @param cep
	 */
	public Endereco(CEP cep) {
		atribuiPelosValoresDoCep(cep);
	}

	/***
	 * Construtor que recebe o cep e um complemento, completando as informa��es com
	 * o cep, e atribuindo o complemento. Somente utilizado no momento do cadastro.
	 * 
	 * @param cep
	 * @param complemento
	 */
	public Endereco(CEP cep, String complemento) {
		atribuiPelosValoresDoCep(cep);
		this.complemento = complemento;
	}

	public Endereco(int id, CEP cep, String localidade, String bairro, String logradouro, String complemento) {
		super();
		this.id = id;
		this.cep = cep;
		this.localidade = localidade;
		this.bairro = bairro;
		this.logradouro = logradouro;
		this.complemento = complemento;
		
	}
	
	/***
	 * Recebe um objeto CEP e atribui os valores do Endereco com os
	 * valores do CEP.
	 * 
	 * @param cep
	 */
	public void atribuiPelosValoresDoCep(CEP cep) {
		this.cep = cep;
		this.bairro = cep.getBairro();
		this.localidade = cep.getLocalidade();
		this.logradouro = cep.getLogradouro();
		this.complemento = cep.getComplemento();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public CEP getCep() {
		return cep;
	}

	public void setCep(CEP cep) {
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
	
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getComplemento() {
		return complemento;
	}
	
	@Override
	public String toString() {
		return "\ncep ___________ " + cep +
			   "\nlocalidade ____ " + localidade +
			   "\nbairro ________ " + bairro +
			   "\nlogradouro ____ " + logradouro +
			   "\nnumero ________ " + numero +
			   "\ncomplemento ___ " + complemento;
	}
}







