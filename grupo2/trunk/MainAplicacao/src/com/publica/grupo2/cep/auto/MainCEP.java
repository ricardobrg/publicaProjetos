

/***
 * 
 * 
 * 
 * 
 */


package com.publica.grupo2.cep.auto;
import java.util.Scanner;

public class MainCEP {

	 public static void main(String[] args) throws Exception {
	        System.out.print("Informe seu CEP: ");
	        Scanner sc = new Scanner(System.in);
	        String cep = sc.nextLine();
	        RequisitaEndereco endereco = ServicoDeCEP.buscaEnderecoPelo(cep);

	        System.out.println("");
	        System.out.println("Rua/Av: " + endereco.getLogradouro());
	        System.out.println("Bairro: " + endereco.getBairro());
	        System.out.println("Cidade: " + endereco.getLocalidade());
	        System.out.println("Estado: " + endereco.getUf());
	        
	        sc.close();
	    }
	}
