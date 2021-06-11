package augusto.publica;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ImplementacoesTest {

  @Test
  public void validarSenhaTest() {
    String senha = "admin123";
    boolean verifySenha = Implementacoes.validarSenha(senha);
    Assert.assertEquals(verifySenha, true);
  }
  
  @Test
  public void validarCNPJ() {
	  String cnpj = "14572457000144";
	  boolean verifyCNPJ = Implementacoes.validarCNPJ(cnpj);
	  Assert.assertEquals(verifyCNPJ, true);
  }
}
