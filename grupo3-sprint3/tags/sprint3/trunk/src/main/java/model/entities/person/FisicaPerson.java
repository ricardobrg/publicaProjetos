package model.entities.person;

import utils.UtilsNumber;
import utils.validations.number.CPFValidation;
import utils.validations.number.PISValidation;

/**
 *	Class that extends Person. Represents a FisicaPerson.
 *
 * @Version: 1.1.1
 * 
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 * @author Pedro Vinicius Hostert <pedrohostertt@gmail.com>
 */
public class FisicaPerson extends Person {
	
	private String cpf, pis;
	
	public FisicaPerson(String name, String cep,
						String email, String phone, 
						String cpf, String pis) {

		super(name, cep, email, phone);
		
		setCpf(cpf);
		setPis(pis);
	}
	
	public FisicaPerson(String name, Endereco endereco, Contact contact, String cpf) {
		super(name, endereco, contact);
		this.cpf = cpf;
	}

	public FisicaPerson(String name, String cpf) {
		super(name);
		this.cpf = cpf;
	}
	
	public FisicaPerson() {
		super();
	} 

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		if (CPFValidation.isCpfValid(cpf)) 
			this.cpf = UtilsNumber.onlyNumbers(cpf);
	}
	

	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		if (PISValidation.isPisValid(pis)) 
			this.pis = pis;
	}

}
