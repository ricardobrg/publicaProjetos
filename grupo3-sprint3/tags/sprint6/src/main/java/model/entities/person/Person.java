package model.entities.person;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

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

@MappedSuperclass
public abstract class Person {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@ManyToOne
	private Endereco endereco;
	
	@OneToOne
	private Contact contact;
	
	public Person(String name, String cep, String email, String phone) {
		this.name = name;
		
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
		this.endereco = new Endereco();
		this.contact = new Contact();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}









