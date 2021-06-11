package com.publica.grupo2.testes;

import org.testng.annotations.Test;

import validate.ValidadorEmail;

import org.testng.Assert;

public class ValidadorEmailTest {

  @Test
  public void validarEmailTest() {
	  
	  String email = "teste@publica.com.br";
	  String dominio = "publica.com.br";
	  
	  boolean resultado = ValidadorEmail.validarEmail(dominio,email);
	  
	  Assert.assertEquals(resultado, true);
  }
}
