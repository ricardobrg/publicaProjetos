package com.publica.grupo1.util.convertion;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/***
 * Class responsible for generate hash's(PBK or SHA3-256)
 *
 * All classes returns one String, and revives one password.
 * but, PBK type recives one user too, for build an salt
 * and give more security for the user.
 * 
 * @version 0.0.1
 * @author Diego Borba <diegoborba25@gmail.com>
 */
public class ToHash{
	/***
	 * Basic criptografy method, using SHA256
	 * @param key
	 * @return byte[] hash 
	 */
	public static String hashGeneratorSha(String key){
		try {
			key+="picoca";
			MessageDigest md;
			md = MessageDigest.getInstance("SHA3-256");
			
			md.update(key.getBytes()); 
			return hashToString(md.digest());
		} 
		catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	/***
	 * Advanced criptografy method
	 * Generates a random salt, which is criptografed in SHA512, for more safety
	 * @param user
	 * @param password
	 * @return String hash
	 */
	public static String hashGeneratorPbk(String user, String password){
		try {
			password += "picoca";
			user += "salgada";
			byte[] salt = new byte[16];
			salt = (hashGeneratorSha(user).getBytes());
			
			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = factory.generateSecret(spec).getEncoded();
			
			return hashToString(hash);
		} 
		catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			return null;
		}
	}
	/***
	 * Convert the hash(byte[]) to String
	 * @param bytes
	 * @return String hash
	 */
	private static String hashToString(byte[] bytes) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			int highPart = ((bytes[i] >> 4) & 0xf) << 4;
			int lowPart = bytes[i] & 0xf;
			
			if (highPart == 0) 
				s.append('0');
			
			s.append(Integer.toHexString(highPart | lowPart));
		}
		
		return s.toString();
	}
}

