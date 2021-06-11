package com.publica.grupo2sprint3.model.person.legalperson;

import javax.persistence.Entity;

import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.util.Validators;

/**
 * Class that extends JuridicaPerson. Represents a Service Provider.
 * 
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 * @author Pablo Mafessoli <mafessolip@gmail.com>
 *
 * Version: 1.0.0
 */
@Entity
public class ServiceProvider extends LegalPerson {
	
	private String description;
	private Double price;

	public ServiceProvider() {} // DEFAULT CONSTRUCTOR
	
	public ServiceProvider(String name, Contact contact, String socialReason,
			   String cnpj) {
		super(name, contact, null, socialReason, cnpj);
	}
	
	public ServiceProvider(String name, Contact contact, String socialReason,
			   String cnpj, Double price) {
		super(name, contact, null, socialReason, cnpj);
		this.price = price;
	}
	
	public ServiceProvider(String name, Contact contact,
						   Address address, String socialReason,
						   String cnpj, double price,
						   String description) {

		super(name, contact, address, socialReason, cnpj);
		this.price = price;
		this.description = description;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if (Validators.isPriceValid(price)) {
			this.price = price;	
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	/***
	 * Override of the toString method to inform all fields of this
	 * object.
	 */
	@Override
	public String toString() {
		return String.format(""
				+ "Nome: %s\n"
				+ "%s\n"
				+ "%s\n"
				+ "Razão Social: %s\n"
				+ "Cnpj: %s\n"
				+ "Custo: %.2f\n"
				+ "Descrição: %s\n",
				this.name, 
				this.contact.toString(),
				this.address != null ? this.address.toString() : "Endereço: Não Cadastrado!", 
				this.getSocialReason(),
				this.getCnpj() != null ? this.getCnpj() : "CNPJ: Não Cadastrado!", 
				this.price != null ? this.price : 0.00,
				this.description != null ? this.description : "Descrição: Não Cadastrada!");
		
	}
	

}
