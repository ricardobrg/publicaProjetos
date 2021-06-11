package com.publica.grupo2sprint3.auth.model;

import java.io.Serializable;

public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 3738268055919281821L;
	private String cpf;
	private String password;

	public JwtRequest() {
	}

	public JwtRequest(String username, String password) {
		this.setCpf(username);
		this.setPassword(password);
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String username) {
		this.cpf = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}