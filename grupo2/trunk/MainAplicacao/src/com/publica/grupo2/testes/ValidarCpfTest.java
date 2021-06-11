package com.publica.grupo2.testes;
import org.testng.Assert;
import org.testng.annotations.Test;




public class ValidarCpfTest {

  @Test
  public void validateCpfTest() {
    String cpf = "12419231910";
    boolean resultado = validate.ValidarCpf.validateCpf(cpf);
    Assert.assertEquals(resultado, true); 
    }
}
