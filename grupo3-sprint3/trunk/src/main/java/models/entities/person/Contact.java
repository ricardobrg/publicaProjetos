package models.entities.person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import utils.UtilsNumber;
import utils.validations.number.TelephoneValidation;
import utils.validations.string.EmailValidation;

/***
 * This object contains information commonly used for contact,
 * a String for email and telephone number.
 * 
 * Currently used for the com.publica.grupo1.person.Person class 
 * and its subclasses.
 * 
 * @version 0.0.1
 * 
 * @author Pedro Vinicius Hostert <pedrohostertt@gmail.com>
 */

@Entity
@Table(name="contacts")
public class Contact {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(unique=true)
	private String email;
	
	@Column(unique=true)
	private String telephone;
	
	public Contact (String email, String telephone) {
		if(EmailValidation.isEmailValid(email))
			this.email = email;
		if(TelephoneValidation.isPhoneValid(telephone))
			this.telephone = telephone;
	}
	
	public Contact() {
		
	}

	public String getEmail() {
		return email;
	}	
	
	public void setEmail(String email) {
		if(EmailValidation.isEmailValid(email))
			this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		if(TelephoneValidation.isPhoneValid(telephone))
			this.telephone = UtilsNumber.onlyNumbers(telephone);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
}
