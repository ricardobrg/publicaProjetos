package model.entities.person;

import utils.validations.number.CNPJValidation;

/**
 * Class that extends Person. Represents a JuridicaPerson.
 * @version 1.0.0
 * 
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 * @author Pedro Vinicius Hostert <pedrohostertt@gmail.com>
 */
public class JuridicaPerson extends Person {

	private String socialReason, cnpj;  

	public JuridicaPerson(String name, /* read as 'fantasyName' */
			String cep, String email, String phone,
			String socialReason, String cnpj){

		super(name, cep, email, phone);
		this.socialReason = socialReason; 
		
		setCnpj(cnpj);
	}
	
	public JuridicaPerson(String name, Endereco endereco, Contact contact) {
		super(name, endereco, contact);
	}

	public JuridicaPerson(String name, String cnpj) {
		super(name);
		
		setCnpj(cnpj);
	}

	public JuridicaPerson() {

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
		if (CNPJValidation.isCnpjValid(cnpj))
			this.cnpj = cnpj;
	}

}
