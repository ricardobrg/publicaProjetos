package com.publica.grupo2sprint3.model.person;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CepTest {

	@Test
	public void atribuiPeloCEPTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void getBairroTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void getCepTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void getComplementoTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void getInstanceTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void getLocalidadeTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void getLogradouroTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void getUfTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void setBairroTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void setCepTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void setComplementoTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void setLocalidadeTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void setLogradouroTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void setUfTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void toStringTest() {
		Cep cep = Cep.getInstance("89107-000");
		
		String result = "Localidade: Pomerode\n"
				+ "Estado: SC\n"
				+ "Bairro: \n"
				+ "Logradouro: \n"
				+ "Complemento: \n";
		
		Assert.assertEquals(cep.toString(), result);


	}
}























