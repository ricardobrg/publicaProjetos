package com.publica.grupo1.model.entities.contact;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactTest {

	private final String VALID_EMAIL = "pedroca@publica.com";
	private final String INVALID_EMAIL = "a@a.a.com";

	private Contact contactValid;
	private Contact contactInvalid;

	@BeforeMethod
	public void beforeEach() {
		// contactValid = new Contact(VALID_EMAIL, VALID_TELEPHONE);
		contactValid = new Contact(VALID_EMAIL);
		contactInvalid = new Contact(INVALID_EMAIL);
		// contactInvalid = new Contact(INVALID_EMAIL INVALID_TELEPHONE);
	}

	@Test
	public void contactTest() {
		Assert.assertNotNull(contactValid.getEmail());
		Assert.assertNull(contactInvalid.getEmail());

//		Assert.assertNotNull(contactValid.getTelephone());
//		Assert.assertNull(contactInvalid.getTelephone());
	}

	@Test
	public void setEmailTest() {
		contactValid.setEmail(INVALID_EMAIL);
		Assert.assertEquals(contactValid.getEmail(), VALID_EMAIL);
	}
}

//	@Test
//	public void setTelephoneTest() {
//		contactValid.setTelephone(INVALID_TELEPHONE);
//		Assert.assertEquals(contactValid.getTelephone(), VALID_TELEPHONE);
//	}
//}
