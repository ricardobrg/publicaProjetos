package model.entities.security;

import org.testng.annotations.Test;

import models.entities.security.AccessLevel;
import models.entities.security.Role;

public class RoleTest {
	@Test
	public void f() {
		Role fa = new Role();
		fa.setAccessLevel(AccessLevel.MEDIUM);
		String x = fa.getAccessLevel().toString();
		System.out.println(x);
	}
}
