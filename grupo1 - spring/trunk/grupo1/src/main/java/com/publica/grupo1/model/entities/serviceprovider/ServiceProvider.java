package com.publica.grupo1.model.entities.serviceprovider;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.publica.grupo1.model.entities.contact.Contact;
import com.publica.grupo1.model.entities.endereco.Endereco;
import com.publica.grupo1.util.UtilsNumber;
import com.publica.grupo1.util.validation.number.CNPJValidation;
import com.publica.grupo1.util.validation.text.TextValidator;


/***
 * Class responsible for the Service Provider object,
 * and keep his data.
 *
 * This class have the atributes: cnpj(Strings),
 * serviceType(Strings), cost(Strings) and 
 * payment(Payment)
 * 
 * @version 0.0.1
 * @author Diego Borba <diegoborba25@gmail.com>
 */
@Entity
public class ServiceProvider {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nameFantasia;
	private String razaoSocial;
	
	@OneToOne
	private Endereco endereco;
	
	@OneToOne
	private Contact contact;
	
	@Column(unique = true, nullable = false)
	private String cnpj;
	
	private String inscEstadual;
	private String inscMunicipal;
	private String serviceType; //TODO: Pr� definir


	/***
	 * Starts the Service Provider class, assigning its attributes
	 * from the parameters passed
	 * @author Diego Borba <diegoborba25@gmail.com>
	 * @param String Service Provider Cnpj
	 * @param String Service Type
	 * @param String Service cost(Money)
	 * @param PaymentRoutes type var
	 */
	public ServiceProvider(String nameFantasia, String razaoSocial, String cnpj, String serviceType){
		this.nameFantasia = nameFantasia;
		this.razaoSocial = razaoSocial;
		
		cnpj = UtilsNumber.onlyDigits(cnpj);		
		if(CNPJValidation.cnpjValidation(cnpj))
			this.cnpj = cnpj;
		
	}
	
	public ServiceProvider(String nameFantasia, String razaoSocial, String cnpj, String serviceType, String inscEstadual, String inscMunicipal, String cost, Endereco endereco, Contact contact) {
		this.nameFantasia = nameFantasia;
		this.razaoSocial = razaoSocial;
		
		setCnpj(cnpj);
	
		this.inscEstadual = inscEstadual;
		this.inscMunicipal = inscMunicipal;
		this.endereco = endereco;
		this.contact = contact;
	}
	
	public ServiceProvider() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameFantasia() {
		return nameFantasia;
	}

	public void setNameFantasia(String nameFantasia) {
		this.nameFantasia = nameFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		cnpj = UtilsNumber.onlyDigits(cnpj);
		if(!CNPJValidation.cnpjValidation(cnpj))
			return;
		this.cnpj = cnpj;
	}

	public String getInscEstadual() {
		return inscEstadual;
	}

	public void setInscEstadual(String inscEstadual) {
		this.inscEstadual = inscEstadual;
	}

	public String getInscMunicipal() {
		return inscMunicipal;
	}

	public void setInscMunicipal(String inscMunicipal) {
		this.inscMunicipal = inscMunicipal;
	}
	
	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		if(TextValidator.caracterValidation(serviceType, 0, 100, false))
			this.serviceType = serviceType;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
}










