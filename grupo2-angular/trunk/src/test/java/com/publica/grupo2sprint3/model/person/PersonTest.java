package com.publica.grupo2sprint3.model.person;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PersonTest {
	
	Cep cep = Cep.getInstance("72322-108");

	Contact contact = new Contact("(91) 98181-8181", "carolsantos@publica.com");
	Address address = new Address(cep);
	Person person = new Person("Ana", contact, address);


	
	@Test
	public void getter_setter_contact_Test() {
		person.setContact(contact);
		Assert.assertEquals(person.getContact().getEmail(), "carolsantos@publica.com" );
		Assert.assertNotEquals(person.getContact().getEmail(), "faladev@gmail.com");
		Assert.assertNotNull(person.getContact().getEmail());
		Assert.assertEquals(person.getContact().getPhone(),"(91) 98181-8181" );
		Assert.assertNotEquals(person.getContact().getPhone(), "(23) 18754-6997");
		Assert.assertNotNull(person.getContact().getPhone());
	}
	
	@Test
	public void getter_setter_address_Test() {
		person.setAddress(address);
		Assert.assertEquals(person.getAddress().getCep().toString(),"72322-108");
		Assert.assertNotEquals(person.getAddress().getCep().toString(), "89052-010");
		Assert.assertNotNull(person.getAddress().getCep());
		Assert.assertEquals(person.getAddress().getLocality(), "Bras�lia");
	}
}
