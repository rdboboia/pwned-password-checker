package com.rdboboia.modules;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.rdboboia.converter.BytesToHexString;

/**
 * Calculates the SHA-1 hash of the given String.
 * @author -$BOSS$-
 */
public class HashSHA1 {
	/**
	 * Private constructor (no instances allowed).
	 */
	private HashSHA1() {
	}
	
	/**
	 * Calculates the SHA-1 hash of the given String.
	 * @param password the string whose hash will be calculated.
	 * @return the hexadecimal representation of the hashed bytes of the input.
	 * @throws NoSuchAlgorithmException if the SHA-1 algorithm could not be found.
	 */
	public static String getHexHash(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		byte[] hashedBytes = md.digest(password.getBytes());
		
		password = null;
		return BytesToHexString.getHexString(hashedBytes);
	}
}