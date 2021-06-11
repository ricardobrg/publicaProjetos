package utils.validations.string;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TextValidationTest {

	@Test
	public void isCharactersValidTest() {
		Assert.assertTrue(TextValidation.isCharactersValid("test"));
		
		Assert.assertFalse(TextValidation.isCharactersValid("InValidText!"));
		Assert.assertTrue(TextValidation.isCharactersValid("Valid text"));
		Assert.assertTrue(TextValidation.isCharactersValid("Válid text"));
		Assert.assertFalse(TextValidation.isCharactersValid("Hi, this is a reaaaaalllyyyy long test so i can verify that it is considering the max length."));	
	}
}
