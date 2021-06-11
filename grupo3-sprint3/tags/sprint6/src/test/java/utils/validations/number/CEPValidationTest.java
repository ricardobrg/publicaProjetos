package utils.validations.number;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CEPValidationTest {

	@Test
	public void isCepValidTest() {
		Assert.assertTrue(CEPValidation.isCepValid("89012-500"));
		
		Assert.assertFalse(CEPValidation.isCepValid("12345-1233"));
		Assert.assertFalse(CEPValidation.isCepValid("1234-123"));
		Assert.assertFalse(CEPValidation.isCepValid("abcde-abc"));
		Assert.assertFalse(CEPValidation.isCepValid("12345-abc"));
	}
}
