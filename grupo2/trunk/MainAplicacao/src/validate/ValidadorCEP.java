/*
 
	M�todo para valida��o de CEP utilizando Regex.
	Manual do Regex - https://regexr.com/
	
	O CEP deve ser um n�mero de 8 d�gitos separados por "-".
	Exemplo: 89025-000 
 
 */

package validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorCEP {

	public static void main(String[] args) {
		
		validarCEP("89025-000");
	}
	
    public static boolean validarCEP(String cep) {
    	
    	boolean cepValido = false;
    	
    	String expression = ("[0-9]{5}-[0-9]{3}");
    	Pattern pattern = Pattern.compile(expression);
    	Matcher matcher = pattern.matcher(cep);
    	
    	if (matcher.matches()){
    		cepValido = true;
    		System.out.println("o CEP informado est� correto!");
    	} else {
    		
    		System.out.println("o CEP informado � inv�lido");
    	}
    	
		return cepValido;

     }   	
}