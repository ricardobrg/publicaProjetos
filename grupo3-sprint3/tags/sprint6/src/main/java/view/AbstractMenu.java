package view;

import com.google.gson.JsonObject;

import model.entities.security.AccessLevel;

public abstract class AbstractMenu {
	private AccessLevel acc;
	private String cpf;
	private JsonObject dataLogin;
	
	public AbstractMenu(JsonObject dataLogin) {
		
		String cpf = dataLogin.get("cpf").getAsString();
		String accessLevel = dataLogin.get("accessLevel").getAsString();
		this.cpf = cpf;
		this.dataLogin = dataLogin;
		
		if(accessLevel.equals("BASIC")) {
			this.acc = AccessLevel.BASIC;
		} else if (accessLevel.equals("MEDIUM")) {
			this.acc = AccessLevel.MEDIUM;
		}else if (accessLevel.equals("TOTAL")) {
			this.acc = AccessLevel.TOTAL;
		}
	}
	
	public String getCpf() {
		return this.cpf;
	}
	
	public AccessLevel getAccessLevel() {
		return this.acc;
	}

	public JsonObject getDataLogin() {
		return this.dataLogin;
	}
	
}
