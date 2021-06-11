package com.publica.grupo1.restapi.routes.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.publica.grupo1.restapi.routes") //TODO talvez trocar dps
@EnableAutoConfiguration
public class Grupo1Application {

	public static void run(Class<ServletInitializer> applicationClass, String[] args) {
		SpringApplication.run(applicationClass, args);
	}

}
