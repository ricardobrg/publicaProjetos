package com.publica.grupo2.Email;

public class ExecutaValidadorEmail {

	/**
	 * Realiza a chamada para o método ValidadorEmail.
	 * 
	 * @author @author Jessé Amaro <jesse.amaro7@gmail.com>
	 */
	
	public static void main(String[] args) {
		boolean valor = ValidadorEmail.validarEmail("nome.sobrenome@publica.com.br");

		if (valor) {
			System.out.println("O email informado é válido!");
		} else {
			System.out.println("Erro. O email informado é inválido!");
		}
	}
}