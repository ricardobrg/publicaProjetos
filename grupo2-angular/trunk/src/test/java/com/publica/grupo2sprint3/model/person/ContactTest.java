package com.publica.grupo2sprint3.model.person;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactTest {

	@Test
	public void getEmailTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void getPhoneTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void setEmailTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void setPhoneTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void toStringTest() {
		Contact contact = new Contact("(47) 99999-8999", "carolsantos@publica.com");
		
		String result = ""
				+ "Email: carolsantos@publica.com\n"
				+ "Telefone: (47) 99999-8999\n";
		
		Assert.assertEquals(contact.toString(), result);

	}
}


















