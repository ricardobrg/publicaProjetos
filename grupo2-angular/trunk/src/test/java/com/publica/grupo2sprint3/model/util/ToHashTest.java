package com.publica.grupo2sprint3.model.util;

import org.testng.Assert;
import org.testng.annotations.Test;


public class ToHashTest {
	private final String USUARIO_SHA = "db99febe79076858e1c5f7502b7039955de7de3d2adfa380f2403e826ac18eb78243fdf2f091c5cc5a445769712c6a6b3ba73149ee972a2fee0f55cfd8ee224a";
	private final String USUARIO_SENHA_PBK = "12720119c570afa085d9e08949ceda2c";
	
	

	@Test
	public void hashGeneratorShaTest() {
		Assert.assertEquals(ToHash.hashGeneratorSha("usuario"), USUARIO_SHA);
	}

	@Test
	public void hashGeneratorPbkTest() {
		Assert.assertEquals(ToHash.hashGeneratorPbk("usuario", "senha"), USUARIO_SENHA_PBK);
	}

}
