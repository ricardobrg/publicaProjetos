package com.publica.grupo1.model.entities.endereco;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EnderecoTest {

	private final String VALID_CEP = "89070-900";

	@Test
	public void EnderecoTestCEP() {
		Assert.assertNotNull(new Endereco(new CEP(VALID_CEP)));
	}

	@Test
	public void EnderecoTestCepEComplemento() {
		Assert.assertNotNull(new Endereco(new CEP(VALID_CEP), "apto. 301").getComplemento());
	}
}
