package controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import models.entities.person.Endereco;
import utils.convertions.JsonString;

/***
 * Contém um método para extrair informações da API "viacep.com.br"
 * retornando um objeto.
 * 
 * As informações são: Localidade, bairro e logradouro.
 * 
 * @version 0.0.1
 * @author Pedro Vinicius Hostert
 */
public class ServicoCEP {
	static String webService = "http://viacep.com.br/ws/";
	static int codigoSucesso = 200;
	
	public static Endereco pegaEnderecoPelo(String cep) throws Exception {
		String urlToCall = webService + cep + "/json";
		
		try {
			URL url = new URL(urlToCall);
			HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
			
			if (conexao.getResponseCode() != codigoSucesso)
				throw new RuntimeException("HTTP error code: " + conexao.getResponseCode());

			BufferedReader resposta = new BufferedReader(new InputStreamReader(
										conexao.getInputStream(), "UTF-8"));
			String jsonEmString = JsonString.converteJsonEmString(resposta);
			
			Gson gson = new Gson();
			Endereco endereco = gson.fromJson(jsonEmString, Endereco.class);
			
			return endereco;
		}
		catch (Exception e) {
			throw new Exception();
		}
	}
}
