package com.publica.grupo2sprint3.model.auth;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {

	@Test
	public void loginTest() {
		Assert.assertTrue(Login.login("admin", "admin"));
		Assert.assertFalse(Login.login("abc", "123"));
		Assert.assertFalse(Login.login("adm", "admin"));
		Assert.assertFalse(Login.login("admin", "123"));
		Assert.assertFalse(Login.login("admin", "12345678"));
		Assert.assertFalse(Login.login("picoca", "salgada"));
		Assert.assertFalse(Login.login("vaidar", "false"));
	}
}
