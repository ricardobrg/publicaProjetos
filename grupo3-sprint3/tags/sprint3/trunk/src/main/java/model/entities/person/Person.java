package model.entities.person;

import utils.validations.number.CEPValidation;
import utils.validations.number.TelephoneValidation;
import utils.validations.string.EmailValidation;

/**
 * Superclass that allows inheritance from Person to FisicaPerson and JuridicaPerson.
 * 
 * @version 1.1.2
 * 
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 * @author Pedro Vinicius Hostert <pedrohostertt@gmail.com>
 */
public abstract class Person {
	
	private String name, cep, email, phone;
	private Endereco endereco;
	private Contact contact;

	public Person(String name, String cep, String email, String phone) {
		this.name = name;
		
		setCep(cep);		
		setEmail(email);		
		setPhone(phone);
		
		this.endereco = new Endereco(cep);
		this.contact = new Contact(email, phone);
	}
	
	public Person(String name, Endereco endereco, Contact contact) {
		this.name = name;
		this.endereco = endereco;
		this.contact = contact;
	}
	
	public Person(String name) {
		this.name = name;
	}
	
	public Person() {
		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setCep(String cep) {
		if(CEPValidation.isCepValid(cep))
			this.cep = cep;
			///this.cep = UtilsNumber.onlyNumbers(cep); //TODO ta dando erro resolver
			
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setEmail(String email) {
		if(EmailValidation.isEmailValid(email))
			this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPhone(String phone) {
		if(TelephoneValidation.isPhoneValid(phone))
			this.phone = phone;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	public Contact getContact() {
		return contact;
	}
}









