package model.entities.person;

import org.testng.Assert;
import org.testng.annotations.Test;

public class JuridicaPersonTest {
	JuridicaPerson juridicaperson = new JuridicaPerson("Ana", "89035070", "carolsantos@publica.com",
			"(47) 99468-6862", "NanaHamburguer233", "80.012.980/0001-81");

	@Test
	public void getter_setter_cpnj_Test() {
		juridicaperson.setCnpj("2.24.33/-24");
		Assert.assertEquals(juridicaperson.getCnpj(), "80.012.980/0001-81");
		Assert.assertNotEquals(juridicaperson.getCnpj(), "52.478.642/0001-02");
		Assert.assertNotNull(juridicaperson.getCnpj());
		
		juridicaperson.setCnpj("02.885.045/0001-01");
		Assert.assertNotNull(juridicaperson.getCnpj());
	}
}

