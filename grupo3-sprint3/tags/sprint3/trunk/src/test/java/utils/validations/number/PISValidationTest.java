package utils.validations.number;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PISValidationTest {

	@Test
	public void isPisValidTest() {
		Assert.assertTrue(PISValidation.isPisValid("780.30054.85-7"));
		Assert.assertTrue(PISValidation.isPisValid("78030054857"));
		Assert.assertFalse(PISValidation.isPisValid("780.30054.85-9"));
		Assert.assertFalse(PISValidation.isPisValid("999.99999.99-9"));
		Assert.assertFalse(PISValidation.isPisValid("780.3005485-7"));
		Assert.assertFalse(PISValidation.isPisValid("780.3004.85-7"));
	}
}
