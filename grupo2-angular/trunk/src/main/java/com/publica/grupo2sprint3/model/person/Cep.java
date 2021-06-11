package com.publica.grupo2sprint3.model.person;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.publica.grupo2sprint3.model.util.Validators;
import com.publica.grupo2sprint3.model.util.cep.ServicoCEP;

/***
 * An object with address information.
 *
 * This object contains address information, with a zip code,
 * locality (city), neighborhood, street and a complement, all
 * in String. Account with an API (com.publica.grupo2sprint3.model.util.cep.ServicoCEP)
 * to extract the other information, except complement. also
 * it is possible to define this information with the object's setters.
 * from the entered zip code,
 * 
 * @version 2.1.4
 * 
 * @author Pedro Vinicius Hostert <pedrohostertt@gmail.com>
 * @author Diego Borba <diegoborba25@gmail.com>
 */

@Entity
public class Cep {
	@Id
	private String cep;
	private String localidade;
	private String uf;
	private String bairro;
	private String logradouro;
	private String complemento;
	
	
	public Cep() {}
	/***
	 * Builder that receives only the zip code, and receives the information
	 * from ServicoCEP.java.
	 * 
	 * @param cep
	 */
	public Cep (String cep) {
		if(Validators.isCepValid(cep)) {
			setCep(cep);
			String[] list = atribuiPeloCEP();

			setLocalidade(list[1]);
			setLogradouro(list[2]);
			setBairro(list[3]);
			setUf(list[4]);
			setComplemento(list[5]);
		}
		//else
		//TODO System.out.println("CEP inválido!");
	}

	private String[] atribuiPeloCEP() {
		try {
			Cep endereco = ServicoCEP.pegaEnderecoPelo(cep);
			String[] list = new String[]{cep, endereco.getLocalidade(), endereco.getLogradouro(), 
					endereco.getBairro(), endereco.getUf(), endereco.getComplemento()};
			return list;
		}
		catch (Exception e) {
			//TODO: error message, invalid cep;
			e.printStackTrace();
			return null;
		}

	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		if (Validators.isCepValid(cep) == true) {
			this.cep = cep;
		}
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String estado) {
		this.uf = estado;
	}

	/***
	 * Override of the toString method to inform all fields of this
	 * object.
	 */
	@Override
	public String toString() {
		String complement = complemento == null ? "" : complemento;
		
		return "Localidade: " + localidade + "\nEstado: " + uf + 
				"\nBairro: " + bairro + "\nLogradouro: " + logradouro + 
				"\nComplemento: " + complement + "\n";	
	}

	public static Cep getInstance(String cep) {
		return new Cep(cep);
	}

}