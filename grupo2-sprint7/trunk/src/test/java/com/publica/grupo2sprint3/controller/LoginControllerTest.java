package com.publica.grupo2sprint3.controller;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginControllerTest {

	@Test
	public void getCollabTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void goToHomeTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void loginTest() {
		String user = "admin";
		String password = "12345dD!";
		Assert.assertEquals(LoginController.login(user, password), true);
	}

	@Test
	public void logoutTest() {
		throw new RuntimeException("Test not implemented");
	}
}
