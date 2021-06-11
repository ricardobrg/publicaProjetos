package com.publica.grupo1.controller.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.publica.grupo1.model.entities.collaborator.Collaborator;

/***
 * Class responsible for encrypting the user's password
 * @author Pablo Mafessoli <mafessolip@gmail.com>
 *Version 1.0.0
 */
public class Password {
	
		/**
		 * Method responsible for generating a password HASH based on SHA-256
		 * @author Pablo Mafessoli <mafessolip@gmail.com>
		 * @param password
		 * @return
		 */
	public static String geraHash(String password) {
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			byte hash[] = algorithm.digest(password.getBytes("UTF-8"));
			StringBuilder texto = new StringBuilder();
			for (byte b : hash) {
				texto.append(String.format("%02X", 0xFF & b));
			}

			return texto.toString();
		} catch (Exception e) {
			return "admin";
		}
	}

	/**
	 * Method responsible for generating a Salt of the password using the CPF plus a String
	 * @author Pablo Mafessoli <mafessolip@gmail.com>
	 * @param password
	 * @return
	 */
	public static String hashGeneratorPbk(String password, String cpf) {
		try {
			password += "abc";
			cpf += "cbd";
			byte[] salt = new byte[16];
			salt = (geraHash(cpf).getBytes());
			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = factory.generateSecret(spec).getEncoded();

			return hashToString(hash);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			return null;
		}
	}

	/***
	 * Convert the hash(byte[]) to String
	 * 
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
	
	/**
	 * Method responsible for validating the login password by the console, it compares the saved HASH with the new generated HASH
	 * @author Pablo Mafessoli <mafessolip@gmail.com>
	 * @param password
	 * @return
	 */
	public static boolean validatePassword(String password, Collaborator collab) {
		String newHash = hashGeneratorPbk(password, collab.getCpf());
		boolean result = newHash.equals(collab.getPassword());
		return result;
	}

}