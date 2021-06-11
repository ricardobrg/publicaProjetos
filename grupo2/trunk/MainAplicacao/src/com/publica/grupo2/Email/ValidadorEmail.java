package com.publica.grupo2.Email;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorEmail {

	public static String dominioPadrao = "publica.com.br";

	/**
	 * Método para validação de Email.
	 * 
	 * @param email
	 * 
	 * Utilizando Regex, verifica se um endereço de email é válido.
	 * Antes do @ pode haver letras, números e pontos. Após o @, deve
	 * utilizar o dominio publica.com.br.
	 * 
	 * @return
	 * 
	 * Se o retorno for true, o email é válido. Se o retorno for false, o
	 * email é inválido.
	 * 
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 */

	public static boolean validarEmail(String email) {

		if (email != null && email.length() > 0) {
			// Regex de validaçao do email.
			String expression = "^[\\w\\.]+@" + dominioPadrao + "$";

			Pattern pattern = Pattern.compile(expression);
			Matcher matcher = pattern.matcher(email);

			return matcher.matches();
		}

		return false;
	}
}
