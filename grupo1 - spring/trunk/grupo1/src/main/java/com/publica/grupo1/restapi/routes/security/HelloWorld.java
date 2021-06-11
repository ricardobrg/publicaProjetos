package com.publica.grupo1.restapi.routes.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
	
	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello World!";
	}
}
