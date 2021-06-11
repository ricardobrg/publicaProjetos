package com.publica.grupo2.testes;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidarTelefoneTest {

  @Test
  public void validarTelefoneTest() {
	  String telefone = "47992505273"; 
	  boolean resultado; 
	  resultado = validate.ValidarTelefone.validarTelefone(telefone); 
	  Assert.assertEquals(resultado, true);
  }
}
