package utils.validations;

import org.testng.Assert;
import org.testng.annotations.Test;

import utils.UtilsNumber;

public class UtilsNumberTest {

	private final String VALID_NUMBER = "1";	
	private final String VALID_NUMBER2 = "-1";
	private final String VALID_NUMBER3 = "0";
	private final String VALID_NUMBER4 = "0123456789876543210";	

	private final String INVALID_NUMBER = "abc";
	private final String INVALID_NUMBER2 = "--1";
	private final String INVALID_NUMBER3 = ":";

	private final String NOT_NUMBER = "abc!!@#$-*/+.,\\";
	private final String HAS_NUMBER = "abc123!@#";
	private final String ONLY_NUMBER = "123456789102030";


	@Test
	public void isNumberTest() {
		Assert.assertTrue(UtilsNumber.isOnlyNumbers((VALID_NUMBER)));
		Assert.assertTrue(UtilsNumber.isOnlyNumbers(VALID_NUMBER2));
		Assert.assertTrue(UtilsNumber.isOnlyNumbers(VALID_NUMBER3));
		Assert.assertTrue(UtilsNumber.isOnlyNumbers(VALID_NUMBER4));

		Assert.assertFalse(UtilsNumber.isOnlyNumbers(INVALID_NUMBER));
		Assert.assertFalse(UtilsNumber.isOnlyNumbers(INVALID_NUMBER2));
		Assert.assertFalse(UtilsNumber.isOnlyNumbers(INVALID_NUMBER3));		
	}

	@Test
	public void isNumberInBoundsTest() {
		Assert.assertTrue(UtilsNumber.isValidNumber(1, 1, 1));
		Assert.assertTrue(UtilsNumber.isValidNumber(2, -100, 99999999));
		Assert.assertTrue(UtilsNumber.isValidNumber(-1, -2, 0));
	}

	@Test
	public void onlyNumbersTest() {
		Assert.assertEquals(UtilsNumber.onlyNumbers(NOT_NUMBER), "");
		Assert.assertEquals(UtilsNumber.onlyNumbers(HAS_NUMBER), "123");
		Assert.assertEquals(UtilsNumber.onlyNumbers(ONLY_NUMBER), ONLY_NUMBER);
	}
}
