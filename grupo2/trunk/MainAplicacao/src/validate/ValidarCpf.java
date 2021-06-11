package validate;
import java.util.ArrayList;

public class ValidarCpf {	
	public static void main(String[] args) {
		String cpf = "12419231910";
		validateCpf(cpf);
		if (validateCpf(cpf) == true) {
			System.out.println(cpf + " é válido");
		} else {
			System.out.println("Não é válido");
		}
	}
	
	/***
	 * M�todo que valida CPF 
	 * 
	 * A valida��o � feita com o CPF inserido pelo usu�rio, pegando os 9 primeiros d�gitos do CPF e multiplicando-os
	 * com uma vari�vel contadora "contador" que � desincrementada a cada repeti��o. A soma desse for ser� multiplicada e 
	 * dividida por 11, ent�o, s�o feitas as verifica��es para validar o primeiro d�gito. O segundo d�gito � verificado com o 
	 * mesmo processo, por�m a vari�vel contador muda o seu valor. 
	 * 
	 * @param cpf  
	 * @return
	 */
	public static boolean validateCpf(String cpf) {  
		int[] verificarCpf = new int[11];
			
		boolean resultadobool= false;
			
		Double.parseDouble(cpf);
		ArrayList<Integer> lista = new ArrayList<>();
		String cpfSplit = cpf.substring(0,9);
		String[] vetor = new String[9];
		int contador = 10;
		int soma = 0;
			
		ArrayList<Integer> lista2 = new ArrayList<>();
		String cpfSplit2 = cpf.substring(0,10);
		String[] vetor2 = new String[10];
		int contador2 = 11;
		int soma2 = 0;
		
		for (int i = 0; i < cpfSplit.length(); i++) {
			int resultado = Integer.parseInt(String.valueOf(cpfSplit.charAt(i)))*contador;
			lista.add(resultado);
			contador--;
		
		}
			
		for (int j = 0; j<lista.size(); j++) {
			soma +=lista.get(j);
		}
		int result = soma * 10 % 11;
			
		if (cpf.equals("00000000000") || cpf.equals("11111111111") ||
	       cpf.equals("22222222222") || cpf.equals("33333333333") ||
	       cpf.equals("44444444444") || cpf.equals("55555555555") ||
	       cpf.equals("66666666666") || cpf.equals("77777777777") ||
	       cpf.equals("88888888888") || cpf.equals("99999999999") ||
	      (cpf.length() != 11)){
	      }
		else {
			if(result == (Integer.parseInt(String.valueOf(cpf.charAt(9))))) {
				for (int i = 0; i < cpfSplit2.length(); i++) {
					int resultado = Integer.parseInt(String.valueOf(cpfSplit2.charAt(i)))*contador2;
					lista2.add(resultado);
					contador2--;
				}
					
				for (int j = 0; j<lista2.size(); j++) {
					soma2 +=lista2.get(j);
				}
		
				int result2 = soma2 * 10 % 11;
		
					
				if(result2 == (Integer.parseInt(String.valueOf(cpf.charAt(10))))) {
					resultadobool= true;
					return resultadobool;
						
				}
			
			}
			else {
				resultadobool= false;
				return resultadobool;
					
			}
			return resultadobool;

	}
		return resultadobool;

}
}
