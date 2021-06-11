package utils.validations.string;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TextValidationTest {

	@Test
	public void isCharactersValidTest() {
		Assert.assertTrue(TextValidation.isCharactersValid("test"));
		
		Assert.assertFalse(TextValidation.isCharactersValid("ValidText!"));
		Assert.assertFalse(TextValidation.isCharactersValid("invalid text"));
		Assert.assertFalse(TextValidation.isCharactersValid("Hi, this is a reaaaaalllyyyy long test so i can verify that it is considering the max lenght."));	
	}
}
