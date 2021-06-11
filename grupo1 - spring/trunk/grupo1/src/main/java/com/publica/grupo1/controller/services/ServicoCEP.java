package com.publica.grupo1.controller.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.publica.grupo1.model.entities.endereco.CEP;

/***
 * Cont�m um método para extrair informações da API "viacep.com.br"
 * retornando um objeto com.publica.grupo1.person.Endereco.
 * 
 * As informações são: Localidade, bairro e logradouro.
 * 
 * @version 1.0.1
 * @author Pedro Vinicius HOstert
 */
public class ServicoCEP {
	static final String webService = "http://viacep.com.br/ws/";
	static final int codigoSucesso = 200;
	
	public static CEP pegaCEP(String cep) throws Exception {	
		
		String urlToCall = webService + cep + "/json";
		
		try {
			URL url = new URL(urlToCall);
			HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
			
			if (conexao.getResponseCode() != codigoSucesso)
				throw new RuntimeException("HTTP error code: " + conexao.getResponseCode());

			BufferedReader resposta = new BufferedReader(new InputStreamReader(
										conexao.getInputStream(), "UTF-8"));
			String jsonEmString = JsonString.parseJsonToString(resposta);
			Gson gson = new Gson();
			CEP objCep = gson.fromJson(jsonEmString, CEP.class);
			
			return objCep;
		}
		catch (JsonSyntaxException e) {
			throw new Exception();
		}
	}
}
