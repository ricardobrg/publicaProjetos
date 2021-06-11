package utils.validations.number;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CNPJValidationTest {

	@Test
	public void isCnpjValidTest() {
		Assert.assertTrue(CNPJValidation.isCnpjValid("11.222.333/0001-81"));
		Assert.assertTrue(CNPJValidation.isCnpjValid("11222333000181"));
		
		Assert.assertFalse(CNPJValidation.isCnpjValid("11.222.333/0001-88"));
		Assert.assertFalse(CNPJValidation.isCnpjValid("88.888.888/8888-88"));
		Assert.assertFalse(CNPJValidation.isCnpjValid("11.222.3330001-81"));
		Assert.assertFalse(CNPJValidation.isCnpjValid("11.222.3330001-81"));
	}
}
