package validate;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


public class VerificarHash {
	
	
	final static String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
	public static final int SALT_BYTES = 24; 
	public static final int HASH_BYTES = 24; 
	
	public static final int PBKDF2_ITERATIONS = 1000;

	public static final int ITERATION_INDEX = 0;
	public static final int SALT_INDEX = 1;
	public static final int PBKDF2_INDEX = 2;

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
			System.out.println( createHash("senha123".toCharArray(), "ricard"));;
	}
	
	/***
	 * Método que cria o hash 
	 * 
	 * A senha digitada é transformada em um array de char para poder fazer o hash 
	 * Esse método possui um salt definido fixamente na linha 41, ele vai misturar o password digitado 
	 * (ou inserido manualmente no programa) para tornar o hash mais complexo. 
	 * Depois é feito uma conversão de byte para hexadecimal, e depois para String, para poder comparar duas hashs
	 * 
	 * @param password
	 * @param username
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	
	public static String createHash(char[] password, String username)
	        throws NoSuchAlgorithmException, InvalidKeySpecException
	    {
			byte[] salt = username.getBytes();//new byte[24];
			PBEKeySpec spec = new PBEKeySpec(password, salt, PBKDF2_ITERATIONS, HASH_BYTES * 8);
			SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
			byte[] hash = skf.generateSecret(spec).getEncoded();
			return  Base64.getEncoder().encodeToString(hash);
    }

}	