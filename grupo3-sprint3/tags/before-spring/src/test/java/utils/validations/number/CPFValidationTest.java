package utils.validations.number;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CPFValidationTest {

	@Test
	public void isCpfValidTest() {
		Assert.assertTrue(CPFValidation.isCpfValid("529.982.247-25"));
		Assert.assertTrue(CPFValidation.isCpfValid("52998224725"));
		
		Assert.assertFalse(CPFValidation.isCpfValid("123.456.789-12"));
		Assert.assertFalse(CPFValidation.isCpfValid("777.777.777-77"));
		Assert.assertFalse(CPFValidation.isCpfValid("529,982.24725"));
		Assert.assertFalse(CPFValidation.isCpfValid("529.982.24-25"));
	}
}
