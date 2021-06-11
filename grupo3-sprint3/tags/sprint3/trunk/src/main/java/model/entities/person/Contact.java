package model.entities.person;

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
public class Contact {
	private String email;
	private String telephone;
	
	public Contact (String email, String telephone) {
		if(EmailValidation.isEmailValid(email))
			this.email = email;
		if(TelephoneValidation.isPhoneValid(telephone))
			this.telephone = telephone;
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
}
