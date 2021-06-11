package validate;

public class ValidarNumeroTexto {

	public static void main(String[] args) {
		validarNum("1247895");
	}
	
	/***
	 * M�todo que valida N�mero e Texto 
	 * 
	 * Ele vai validar se em um input tem apenas texto ou apenas n�mero, 
	 * muito importante no momento de inserir endere�os, etc. 
	 * Ele ser� modificado futuramente para dois m�todos - isNum e isText 
	 * @param string
	 * @return
	 */
		
	public static boolean validarNum(String string) {
		//String string = "124ddd57895";
        boolean boolnum = true;

        try {
            Double numero = Double.parseDouble(string);
        } catch (NumberFormatException e) {
            boolnum = false;
        }

        if(boolnum) {
            System.out.println(string + " é um número");

        	return boolnum;
        }
        else {
            System.out.println(string + " é um texto");
            boolnum = false;
            return boolnum;
        }
	}


}


