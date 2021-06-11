package model.entities.security;

import org.testng.Assert;
import org.testng.annotations.Test;

import models.entities.security.AccessLevel;

public class AccessLevelTest {

  @Test
  public void accessLevelTest() {
	  AccessLevel testObject = AccessLevel.BASIC;
	  Assert.assertEquals(testObject.getValue(), 1);
	  Assert.assertNotEquals(testObject.getValue(), 2);
	  Assert.assertNotEquals(testObject.getValue(), 3);
	  Assert.assertNotEquals(testObject.getValue(), 4);
	  Assert.assertNotEquals(testObject.getValue(), 5);
	  Assert.assertNotEquals(testObject.getValue(), 6);
	  Assert.assertNotNull(testObject);
  }
}
