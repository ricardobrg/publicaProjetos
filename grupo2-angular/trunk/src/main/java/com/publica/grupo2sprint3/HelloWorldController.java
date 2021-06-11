package com.publica.grupo2sprint3;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/olamundo")
public class HelloWorldController {
	@GetMapping("/saudacao")
	public String olaMundo() {
		return "Olá, Mundo!";
	}
}
