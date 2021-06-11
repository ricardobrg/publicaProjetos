package utils.validations.number;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TelephoneValidationTest {
	private final String VALID_NUMBER = "(47) 9 9102-9444";
	private final String VALID_NUMBER2 = "(47)99102-9444";
	private final String VALID_NUMBER3 = "47 9 9102-9444";
	private final String VALID_NUMBER4 = "47 991029444";
	private final String VALID_NUMBER5 = "47 8888-8888";

	private final String INVALID_NUMBER = "10 991029444";
	private final String INVALID_NUMBER2 = "11 8888--8888";
	private final String INVALID_NUMBER3 = "11 8888-888";

	@Test
	public void isPhoneValidTest() {
		Assert.assertTrue(TelephoneValidation.isPhoneValid(VALID_NUMBER));
		Assert.assertTrue(TelephoneValidation.isPhoneValid(VALID_NUMBER2));
		Assert.assertTrue(TelephoneValidation.isPhoneValid(VALID_NUMBER3));
		Assert.assertTrue(TelephoneValidation.isPhoneValid(VALID_NUMBER4));
		Assert.assertTrue(TelephoneValidation.isPhoneValid(VALID_NUMBER5));

		Assert.assertFalse(TelephoneValidation.isPhoneValid(INVALID_NUMBER));
		Assert.assertFalse(TelephoneValidation.isPhoneValid(INVALID_NUMBER2));
		Assert.assertFalse(TelephoneValidation.isPhoneValid(INVALID_NUMBER3));
	}
}
