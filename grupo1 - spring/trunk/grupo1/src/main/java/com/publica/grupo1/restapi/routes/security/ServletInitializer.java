package com.publica.grupo1.restapi.routes.security;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {
	
	private static Class<ServletInitializer> applicationClass = ServletInitializer.class;

	public static void main(String[] args) {
		Grupo1Application.run(applicationClass, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Grupo1Application.class);
	}
}
