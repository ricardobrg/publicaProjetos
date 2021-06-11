package augusto.publica;

/***
 *	@author Augusto Kalahary <AuuKalaharyKW@gmail.com>
 *
 */

import java.util.ArrayList;
//import java.util.Scanner;

public class Implementacoes {

	public static void main(String[] args) throws Exception {

		/*
		 * Scanner input = new Scanner(System.in); int count = 1; do {
		 * System.out.print("Usu�rio: "); String usuario = input.next();
		 * System.out.print("Senha: "); String senha = input.next();
		 * 
		 * if (validarSenha(senha) == true) { System.out.println("senha válida");
		 * break; } else { System.out.println("Senha precisa ter 8 ou mais caracteres!"); count++; } } while
		 * (count <= 5); if (count == 5) { validaMaxTentativas(count); } if
		 * (validaMaxTentativas(count) == true) { String msg =
		 * "E-mail enviado para recupea��o de senha"; EnviarEmail.sendMail("prowaypublicateste@gmail.com"); System.out.println(msg); }
		 */

		/*
		 * System.out.println("========================================");
		 * System.out.print("Insira o CNPJ: "); String cnpj = input.next(); if
		 * (validarCNPJ(cnpj) == true) { System.out.print("CNPJ � v�lido"); } else {
		 * System.out.print("CNPJ � inv�lido"); }
		 */
		 
		
		
//		input.close();
	}

	
	/***
	 * Método para verificar se a senha é válida
	 *
	 * @param senha é o parâmetro
	 * @return true se a senha tiver pelo menos 8 caracteres, caso contrário, retorna false
	 * 
	 * TODO Nesse método faltou a implementação que verifica se a senha possui pelo menos uma letra maiúscula, um número e talvez um caracter especial
	 */
	public static boolean validarSenha(String senha) {
		if (senha.length() < 8 || senha.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/***
	 * M�todo para verificar se o CNPJ inserido � v�lido
	 *
	 * @param cnpj par�metro
	 * @return true caso seja v�lido, caso contr�rio, retorna false
	 */
	public static boolean validarCNPJ(String cnpj) {
		if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111") ||
				cnpj.equals("22222222222222") || cnpj.equals("33333333333333") ||
				cnpj.equals("44444444444444") || cnpj.equals("55555555555555") ||
				cnpj.equals("66666666666666") || cnpj.equals("77777777777777") ||
				cnpj.equals("88888888888888") || cnpj.equals("99999999999999") ||
				cnpj.length() != 14) {
			return false;
		} else {
			String cnpjSplit = cnpj.substring(0, 11);
			String cnpjSplit2 = cnpj.substring(0, 12);
			int resultado,
			soma = 0,
			result;
			int count = 2;
			ArrayList<Integer> listaDeNumeros = new ArrayList<>();
			
			for (int i = 12; i <= cnpjSplit.length()-1; i--) {
				resultado = Integer.parseInt(String.valueOf(cnpjSplit.charAt(i))) * count;
				listaDeNumeros.add(resultado);
				count++;
				if (count == 10) {
					count = 2;
				}
				
				for (int j = 0; j < listaDeNumeros.size(); j++) {
					soma += listaDeNumeros.get(j);
				}
				result = soma % 11;

				if (result == Integer.parseInt(String.valueOf(cnpj.charAt(12)))) {
					for (int j = 13; j < cnpjSplit2.length()-1; j--) {
						resultado = Integer.parseInt(String.valueOf(cnpjSplit2.charAt(i))) * count;
						listaDeNumeros.add(resultado);
						count++;
						if (count == 10) {
							count = 2;
						}
							for (int k= 0; k < listaDeNumeros.size(); k++) {
							soma += listaDeNumeros.get(j);
						}
						result = soma % 11;
							if (result == Integer.parseInt(String.valueOf(cnpjSplit2.charAt(13)))) {
							return true;
						}
					}
				}
			}
			return true;
		}
	}
	
	/***
	 * M�todo para verificar a quantidade de vezes que o usu�rio digitou a senha errada e enviar e-mail para recupera��o
	 * 
	 * @param count par�metro
	 */
	public static boolean validaMaxTentativas(int count) {
		if (count >= 5) {
			return true;
		}
		return false;
	}
}