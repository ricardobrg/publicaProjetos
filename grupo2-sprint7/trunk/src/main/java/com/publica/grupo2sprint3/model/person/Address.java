package com.publica.grupo2sprint3.model.person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

import com.publica.grupo2sprint3.model.util.Validators;

/***
 * An object with address information.
 *
 * This object contains address information, with a zip code,
 * locality (city), neighborhood, street and a complement, all
 * in String. Account with another object(CEP)
 * to extract the other information, except complement. also
 * it is possible to define this information with the object's setters.
 * from the entered zip code,
 * 
 * @version 1.0.0
 * 
 * @author Diego Borba <diegoborba25@gmail.com>
 */
@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NonNull
	private String cep;
	private String locality;
	private String uf;
	private String district;
	private String street;
	private String complement;



	/***
	 * Builder that receives only the zip code, and receives the information
	 * from CEP class
	 * 
	 * @param cep
	 */
	public Address (Cep cep) {
		this.setCep(cep.getCep());
		this.setLocality(cep.getLocalidade());
		this.setUf(cep.getUf());
		this.setDistrict(cep.getBairro());
		this.setStreet(cep.getLogradouro());
		this.setComplement(cep.getComplemento());

	}
	
	public Address() {}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		if (Validators.isCepValid(cep) == true) {
			this.cep = cep;
		}
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getLocality() {
		return locality;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreet() {
		return street;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDistrict() {
		return district;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getComplement() {
		return complement;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/***
	 * Override of the toString method to inform all fields of this
	 * object.
	 */
	@Override
	public String toString() {
		String complement_ = complement == null ? "" : complement;

		return "Localidade: " + locality + "\nEstado: " + uf + 
				"\nBairro: " + district + "\nLogradouro: " + street + 
				"\nComplemento: " + complement_ + "\n";	
	}

}
