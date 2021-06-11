package validate;

public class ValidarTelefone {

	public static void main(String[] args) {
		validarTelefone("47992505273");

	}
	
	/***
	 * M�todo de validar telefone 
	 * 
	 * Atualmente, o m�todo apenas valida o tamanho dos caracteres string inseridos
	 *
	 * @param telefone
	 * @return
	 */
		
	public static boolean validarTelefone(String telefone) {
		boolean resultado = false; 
		//String telefone = "47992505273";
			
		if (telefone.length() != 11) {
			System.out.println("Telefone inv�lido ");
			resultado = false;
			return resultado; 
		}
		else {
			System.out.println("Telefone v�lido");
			resultado = true;
			return resultado;
		}
			
	}

}


