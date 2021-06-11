package com.publica.grupo2sprint3.model.person.legalperson;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Cep;
import com.publica.grupo2sprint3.model.person.Contact;

public class LegalPersonTest {
	
	Cep cep = Cep.getInstance("72322-108");
	Contact contact = new Contact("(47) 99468-686", "carolsantos@publica.com");
	Address address = new Address(cep);
	LegalPerson juridicaperson = new LegalPerson("Ana", contact, address, "NanaHamburguer233", "80.012.980/0001-81");

	@Test
	public void getter_setter_cpnj_Test() {
		juridicaperson.setCnpj("2.24.33/-24");
		Assert.assertEquals(juridicaperson.getCnpj(), "80.012.980/0001-81");
		Assert.assertNotEquals(juridicaperson.getCnpj(), "52.478.642/0001-02");
		Assert.assertNotNull(juridicaperson.getCnpj(), "01.885.005/0001-01");
		juridicaperson.setCnpj("02.885.045/0001-01");
		Assert.assertNotNull(juridicaperson.getCnpj(), null);
	}
}
