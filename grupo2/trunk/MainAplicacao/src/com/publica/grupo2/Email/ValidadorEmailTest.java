package com.publica.grupo2.Email;

import org.testng.annotations.Test;
import org.testng.Assert;

public class ValidadorEmailTest {

  @Test
  public void validarEmailTest() {
	  
	  /**
	   * Testa o validador de Email. 
	   * 
	   * Com base no email válido informado na string email, 
	   * testa se o validador está funcionando de forma correta. 
	   * 
	   * Para o email ser válido, antes do @ pode haver letras, 
	   * números e pontos e após o @, deve utilizar publica.com.br.
	   * 
	   * @author Jessé Amaro <jesse.amaro7@gmail.com>
	   */
	  
	  String email = "teste@publica.com.br";
	  
	  boolean resultado = ValidadorEmail.validarEmail(email);
	  
	  Assert.assertEquals(resultado, true);
  }
}
