package utils.validations.datetime;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DateValidationTest {

	@Test
	public void isDateValidTest() {
		Assert.assertTrue(DateValidation.isDateValid("31/10/1998"));
		
		Assert.assertFalse(DateValidation.isDateValid("10/31/1998"));
		Assert.assertFalse(DateValidation.isDateValid("1998/10/31"));
		Assert.assertFalse(DateValidation.isDateValid("1998/31/10"));
	}
}
