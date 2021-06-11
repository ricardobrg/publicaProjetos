package com.publica.grupo2.cep;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorCEP {

	public static void main(String[] args) {
		validarCEP("89025-000");
	}
	
	/**
	 * Método para válidar CEP.
	 * 
	 * Havendo um CEP informado como parametro, o método verifica se
	 * o CEP corresponde a um número de 8 digitos separados por hifen, 
	 * conforme o padrão 00000-000.
	 * 
	 * @param cep
	 * 
	 * Deve ser um número de 8 digitos separados por hifen.
	 * 
	 * @return 
	 * 
	 * Se o retorno for true, o CEP é válido.
     * Se o retorno for false, o CEP é inválido.
     * 
     * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 */
	
    public static boolean validarCEP(String cep) {
    	
    	boolean cepValido = false;
    	
    	String expression = ("[0-9]{5}-[0-9]{3}");
    	Pattern pattern = Pattern.compile(expression);
    	Matcher matcher = pattern.matcher(cep);
    	
    	if (matcher.matches()){
    		cepValido = true;
    		System.out.println("o CEP informado está correto!");
    	} else {
    		
    		System.out.println("o CEP informado é inválido");
    	}	
		return cepValido;

     }   	
}