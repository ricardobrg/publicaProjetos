/*
 
	M�todo para valida��o de endere�o de email com Regex.
	Manual do Regex - https://regexr.com/
	
	Inicialmente todos os emails devem ser @publica.com.br
	Por isso est� sendo utilizada a variavel "dominio". 
 
 */

package validate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorEmail {

    public static void main(String[] args) {
    	validarEmail("publica.com.br", "teste@publica.com.br");
    }
 
    public static boolean validarEmail(String dominio, String email) {

    	boolean emailValido;
        if (email != null && email.length() > 0) {
        	String expression = "^[\\w\\-.]+@"+dominio+"$";
            Pattern pattern = Pattern.compile(expression);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
            	emailValido = true;
            	System.out.println("O endere�o de email � V�LIDO!!");
            	return emailValido;
            } else {
            	
            	System.out.println("Erro. O endere�o informado � INV�LIDO!");
            	emailValido = false;
            	return emailValido;
            }
        }
        return emailValido = false; 
       
    }
}
