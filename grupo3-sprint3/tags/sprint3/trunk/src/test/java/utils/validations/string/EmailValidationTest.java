package utils.validations.string;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EmailValidationTest {
	
	private final String VALID_EMAIL = "abc@abc.com";
	private final String VALID_EMAIL2 = "pedro@fiscal.com";
	private final String VALID_EMAIL3 = "pedro@pedrones.com.br";

	private final String INVALID_EMAIL = "a@a.c";
	private final String INVALID_EMAIL2 = "c@abc.com";
	private final String INVALID_EMAIL3 = "abc@abc.abcabcabc";
	private final String INVALID_EMAIL4 = "issoeumemail@muitomuitomuitogrande.com";
	
	@Test
	public void isEmailValidTest() {
		Assert.assertTrue(EmailValidation.isEmailValid(VALID_EMAIL));
		Assert.assertTrue(EmailValidation.isEmailValid(VALID_EMAIL2));
		Assert.assertTrue(EmailValidation.isEmailValid(VALID_EMAIL3));
		
		Assert.assertFalse(EmailValidation.isEmailValid(INVALID_EMAIL));
		Assert.assertFalse(EmailValidation.isEmailValid(INVALID_EMAIL2));
		Assert.assertFalse(EmailValidation.isEmailValid(INVALID_EMAIL3));
		Assert.assertFalse(EmailValidation.isEmailValid(INVALID_EMAIL4));
	}
}
