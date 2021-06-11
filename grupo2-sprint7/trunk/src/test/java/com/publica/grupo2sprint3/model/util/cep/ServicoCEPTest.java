package com.publica.grupo2sprint3.model.util.cep;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Cep;

public class ServicoCEPTest {

	@Test
	public void pegaEnderecoPeloTest() throws Exception {
		Cep cep = Cep.getInstance("89107-000");
		Address ed = new Address(cep);
		Assert.assertEquals(ed.getCep(), cep);
		Assert.assertEquals(ed.getLocality(), "Pomerode");
		Assert.assertEquals(ed.getUf(), "SC");
		Assert.assertEquals(ed.getDistrict(), "");
		Assert.assertEquals(ed.getStreet(), "");
		Assert.assertEquals(ed.getComplement(), "");
	}
}
