package com.publica.grupo2.testes;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidarNumeroTextoTest {

  @Test
  public void validarNumTest() {
	  String string = "23455332"; 
	  boolean resultado = validate.ValidarNumeroTexto.validarNum(string); 
	  Assert.assertEquals(resultado, true);
  }
}
