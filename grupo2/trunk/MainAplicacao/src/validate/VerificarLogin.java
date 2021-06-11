package validate;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/***
 * 
 * @author publica
 *
 */
public class VerificarLogin {

	public static void main(String[] args)  throws NoSuchAlgorithmException,
    UnsupportedEncodingException {

        String usuario = "admin";
        String senha = "1234";

        MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
        byte mensagemDigestUsuario[] = algoritmo.digest(usuario.getBytes("UTF-8"));
        byte mensagemDigestSenha[] = algoritmo.digest(usuario.getBytes("UTF-8"));
   
        Scanner scan = new Scanner(System.in);
        String usuario2 = scan.nextLine();
        String senha2 = scan.nextLine(); 
        
        byte mensagemDigestUsuario2[] = algoritmo.digest(usuario2.getBytes("UTF-8"));
        byte mensagemDigestSenha2[] = algoritmo.digest(senha2.getBytes("UTF-8"));
        
        
        
        
        StringBuilder hexadecStringUsuario = new StringBuilder();
        for (byte b : mensagemDigestUsuario) {
                 hexadecStringUsuario.append(String.format("%02X", 0xFF & b));
        }
        String senhahexUsuario = hexadecStringUsuario.toString();
        

        StringBuilder hexadecStringSenhaUsuario2 = new StringBuilder();
        for (byte b : mensagemDigestUsuario2) {
                 hexadecStringSenhaUsuario2.append(String.format("%02X", 0xFF & b));
        }
        String senhahexUsuario2 = hexadecStringSenhaUsuario2.toString();

        
        
        
        
        System.out.println(senhahexUsuario.equals(senhahexUsuario2));
        
        
        

        scan.close();
        
        
        
        
        
		

       
	}
}

