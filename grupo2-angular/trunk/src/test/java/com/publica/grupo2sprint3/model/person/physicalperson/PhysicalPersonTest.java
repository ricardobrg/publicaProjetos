package com.publica.grupo2sprint3.model.person.physicalperson;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Cep;
import com.publica.grupo2sprint3.model.person.Contact;


public class PhysicalPersonTest {
	
	Cep cep = Cep.getInstance("72322-108");

	Contact contact = new Contact("(47) 99468-6862", "carolsantos@publica.com");
	Address address = new Address(cep);
	PhysicalPerson physicalPerson = new PhysicalPerson("Ana", contact, address, "613.306.180-41", "981.70710.15-8");

	@Test
	public void isPhoneValid() {
		physicalPerson.setContact(contact);
		Assert.assertEquals(physicalPerson.getContact().getPhone(),"(47) 99468-6862" );
		Assert.assertNotEquals(physicalPerson.getContact().getPhone(), "(23) 18754-6997");
		Assert.assertNotNull(physicalPerson.getContact().getPhone());
	}

	@Test
	public void isEmailValidTest() {
		physicalPerson.setContact(contact);
		Assert.assertEquals(physicalPerson.getContact().getEmail(),"carolsantos@publica.com");
		Assert.assertNotEquals(physicalPerson.getContact().getEmail(), "faladev@gmail.com");
		Assert.assertNotNull(physicalPerson.getContact().getEmail());
	}

	@Test
	public void isCepValidTest() {
		physicalPerson.setAddress(address);
		Assert.assertEquals(physicalPerson.getAddress().getCep().toString(), "72322-108");
		Assert.assertNotEquals(physicalPerson.getAddress().getCep(), "64710-970");
		Assert.assertNotNull(physicalPerson.getAddress().getCep());
	}

	@Test
	public void isPisValidTest() {
		Assert.assertEquals(physicalPerson.getPis(),"981.70710.15-8");
		Assert.assertNotEquals(physicalPerson.getPis(), "470.43427.99-1");
		Assert.assertNotNull(physicalPerson.getPis(), "553.60258.60-0");
	}
	
	@Test
	public void setCpfTest() {
		Assert.assertEquals(physicalPerson.getCpf(), "613.306.180-41");
		Assert.assertNotEquals(physicalPerson.getCpf(), "456.633.640-99");
		Assert.assertNotNull(physicalPerson.getCpf(), "456.633.640-990");
	}
	
	
	/*
	@Test
	public void isSalaryValid() {
		fisicaperstwo.setSalary(-5000.00);
		Assert.assertEquals(fisicaperstwo.getSalary(), 3000.00);
		Assert.assertNotEquals(fisicaperstwo.getSalary(), 5000.00);
		Assert.assertNotEquals(fisicaperstwo.getSalary(), -5000.00);
		Assert.assertNotEquals(fisicaperstwo.getSalary(), 0);
		fisicaperstwo.setSalary(3000.00);
		Assert.assertNotNull(fisicaperstwo.getSalary(), null);
	}
	
	@Test
	public void isWorkHoursValid() {
		fisicaperstwo.setWorkHours(161);
		Assert.assertEquals(fisicaperstwo.getWorkHours(), 160);
		Assert.assertNotEquals(fisicaperstwo.getWorkHours(), 5000);
		Assert.assertNotNull(fisicaperstwo.getWorkHours(), null);
		fisicaperstwo.setWorkHours(44);
		Assert.assertNotNull(fisicaperstwo.getWorkHours(), null);
	}
	*/	
}
