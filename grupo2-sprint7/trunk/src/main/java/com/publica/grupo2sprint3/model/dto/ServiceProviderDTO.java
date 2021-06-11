package com.publica.grupo2sprint3.model.dto;

import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.legalperson.ServiceProvider;

/***
 * Class responsible to create an ServiceProviderDTO's object that is composed 
 * by a ServiceProvider's object, but it's limited with the informations that we want to show.
 * 
 * @author Augusto Kalahary <AuuKalaharyKW@gmail.com>
 *
 */
public class ServiceProviderDTO {
	
	private String socialReason,
				   cnpj,
				   description;
	private Contact contact;
	private double price;
	
	public ServiceProviderDTO(ServiceProvider sProvider) {
		this.setSocialReason(sProvider.getSocialReason());
		this.setCnpj(sProvider.getCnpj());
		this.setDescription(sProvider.getDescription());
		this.setContato(sProvider.getContact());
		this.setPrice(sProvider.getPrice());
	}
	
	public Contact getContato() {
		return contact;
	}

	public void setContato(Contact contact) {
		this.contact = contact;
	}

	public String getSocialReason() {
		return socialReason;
	}

	public void setSocialReason(String socialReason) {
		this.socialReason = socialReason;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
