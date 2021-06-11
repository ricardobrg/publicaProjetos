package com.publica.grupo2sprint3.model.util;

import org.testng.Assert;
import org.testng.annotations.Test;

//import sun.security.validator.Validator;

public class ValidatorsTest {

	@Test
	public void validatorCharacterTest() {
		Assert.assertEquals(Validators.isCharactersValid("Pablo"), true);
		Assert.assertEquals(Validators.isCharactersValid("123456"), false);
		Assert.assertEquals(Validators.isCharactersValid("Vinicius"), true);
		Assert.assertEquals(Validators.isCharactersValid(""), false);
		Assert.assertEquals(Validators.isCharactersValid("Vinicius_roosevelt"), false);
		Assert.assertEquals(Validators.isCharactersValid("Pablo1"), false);
		Assert.assertEquals(Validators.isCharactersValid("Pablo Mafessoli"), true);
		Assert.assertEquals(Validators.isCharactersValid("Pablo 1234"), false);
	}

	@Test
	public void validatorSalaryTest() {
		Assert.assertEquals(Validators.isSalaryValid(1000), true);
		Assert.assertEquals(Validators.isSalaryValid(3500), true);
		Assert.assertEquals(Validators.isSalaryValid(4000), true);
		Assert.assertEquals(Validators.isSalaryValid(100), true);
		Assert.assertEquals(Validators.isSalaryValid(-1000), false);
		Assert.assertEquals(Validators.isSalaryValid(-2), false);
		Assert.assertEquals(Validators.isSalaryValid(0), true);
	}

	@Test
	public void isCepValidTest() {
		Assert.assertTrue(Validators.isCepValid("00000-000"));
	}
	
	@Test
	public void isPisValidTest() {}
	
	@Test
	public void isPhoneValidTest() {
		Assert.assertTrue(Validators.isPhoneValid("(15) 99926-3568"));
	}
	
	@Test
	public void isCnpjValidTest() {
		Assert.assertTrue(Validators.isCnpjValid("95.718.016/0001-40"));
	}
}