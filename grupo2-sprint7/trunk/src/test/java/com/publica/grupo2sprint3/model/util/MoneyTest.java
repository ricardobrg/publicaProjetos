package com.publica.grupo2sprint3.model.util;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class MoneyTest {

  @Test
  public void ToStringTest() {
    assertEquals(Money.ToString(20.999), "21,00");
  }
}
