package com.publica.grupo1.model.entities.endereco;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CEPTest {

	@Test
	public void cepTest() {
		Assert.assertEquals(new CEP("89035070").getBairro(), "Vila Nova");
	}
}
