package com.publica.grupo2sprint3.model.person;

import org.testng.Assert;
import org.testng.annotations.Test;


public class AddressTest {

	@Test
	public void AddressTestString() {
		Cep cep = Cep.getInstance("89107-000");
		Address ed = new Address(cep);
		Assert.assertEquals(ed.getCep(), cep.getCep());
		Assert.assertEquals(ed.getLocality(), "Pomerode");
		Assert.assertEquals(ed.getUf(), "SC");
		Assert.assertEquals(ed.getDistrict(), "");
		Assert.assertEquals(ed.getStreet(), "");
		Assert.assertEquals(ed.getComplement(), "");
	}

	@Test
	public void AddressTestStringString() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void atribuiPeloCEPTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void toStringTest() {
		Cep cep = Cep.getInstance("89107-000");
		Address ed = new Address(cep);
		
		String result = "Localidade: Pomerode\n"
				+ "Estado: SC\n"
				+ "Bairro: \n"
				+ "Logradouro: \n"
				+ "Complemento: \n";
		
		Assert.assertEquals(ed.toString(), result);


	}
}
