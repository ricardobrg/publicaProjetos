package com.publica.grupo1.model.entities.collaborator;

import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CollaboratorTest {

  @Test
  public void setAdmissionDateTest() {
	  Collaborator testObject = new Collaborator();
	  testObject.setAdmissionDateByAmericanPattern("2020-12-08");
	  Assert.assertEquals(testObject.getAdmissionDate(), LocalDate.of(2020, 12, 8));
  }
}
