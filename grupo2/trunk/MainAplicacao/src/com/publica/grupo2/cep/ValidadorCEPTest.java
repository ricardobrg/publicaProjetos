package com.publica.grupo2.cep;

import org.testng.annotations.Test;
import org.testng.Assert;

public class ValidadorCEPTest {

  @Test
  public void validarCEPTest() {
	  
	  /**
	   * Testa o Validador de CEP.
	   * 
	   * Com base em um CEP válido, testa se o validador de CEP 
	   * está funcionando conforme o esperado. Havendo um CEP válido
	   * informado na variável CEP, o resultado deve ser true. 
	   * 
	   * O CEP deve ser um número de 8 digitos separados por hifen.
	   * 
	   * @author Jessé Amaro <jesse.amaro7@gmail.com>
	   */
	  
	  String CEP = "89025-000";
	  
	  boolean resultado = ValidadorCEP.validarCEP(CEP);
	  Assert.assertEquals(resultado, true);
  }
}
