package com.publica.grupo2.testes;

import org.testng.annotations.Test;

import validate.ValidadorCEP;

import org.testng.Assert;

public class ValidadorCEPTest {

  @Test
  public void validarCEPTest() {
    
	  // CEP para teste seguindo o padrao de 8 numeros com o "-". 
	  String CEP = "89025-400";
	  
	  boolean resultado = ValidadorCEP.validarCEP(CEP);
	  Assert.assertEquals(resultado, true);
	  
  }
}
