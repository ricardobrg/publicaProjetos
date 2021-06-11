package com.publica.grupo2sprint3.model.util.cep;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.publica.grupo2sprint3.model.person.Cep;
import com.publica.grupo2sprint3.model.util.UtilNumbers;

/***
 * Contém um método para extrair informações da API "viacep.com.br"
 * retornando um objeto com.publica.grupo1.person.Endereco.
 * 
 * As informações são: Localidade, bairro e logradouro.
 * 
 * @version 1.2.0
 * @author Pedro Vinicius Hostert
 * @author Diego Borba <diegoborba25@gmail.com>
 */
public class ServicoCEP {
	static String webService = "http://viacep.com.br/ws/";
	static int codigoSucesso = 200;

	
	public static Cep pegaEnderecoPelo(String cep) throws Exception {
		String urlToCall = webService + UtilNumbers.onlyNumbers(cep) + "/json";
		
		try {
			URL url = new URL(urlToCall);
			HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
			
			if (conexao.getResponseCode() != codigoSucesso)
				throw new RuntimeException("HTTP error code: " + conexao.getResponseCode());

			BufferedReader resposta = new BufferedReader(new InputStreamReader(
										conexao.getInputStream(), "UTF-8"));
			String jsonEmString = JsonString.converteJsonEmString(resposta);
			Gson gson = new Gson();
			Cep out_cep = gson.fromJson(jsonEmString, Cep.class);
			return out_cep;
		}
		catch (Exception e) {
			throw new Exception();
		}
	}
}
