package validate;

import java.util.Scanner;

public class Implementacoes {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.print("Usu�rio: ");
		String usuario = input.next();
		System.out.print("Senha: ");
		String senha = input.next();
		
		if (validarSenha(senha) == true) {
			System.out.println(usuario + " logado!");
		} else {
			System.out.println("Senha incorreta!");
		}
		
		input.close();
	}

	public static boolean validarSenha(String senha) {
		if (senha.equals("admin123")) {
			return true;
		} else {
			return false;
		}
	}
}
