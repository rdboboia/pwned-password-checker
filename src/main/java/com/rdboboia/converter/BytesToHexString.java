package com.rdboboia.converter;

public class BytesToHexString {
	/**
	 * Private constructor. No instances allowed.
	 */
	private BytesToHexString() {
	}
	
	
	/**
	 * Calculates the hexadecimal representation of the given byte array.
	 * @param bytes the input bytes.
	 * @return a String with the hexadecimal representation of the given bytes.
	 */
	public static String getHexString(byte[] bytes) {
		StringBuilder s = new StringBuilder();
		
		String temp;
		for (byte elem : bytes) {
			temp = Integer.toHexString(Byte.toUnsignedInt(elem));
			if (temp.length() == 1)
				temp = "0" + temp;
			
			s.append(temp);
		}
		
		return s.toString().toUpperCase();
	}
	
	
	
	/**
	 * Calculates the hexadecimal representation of the given byte array.
	 * @param bytes the input bytes.
	 * @return a String with the hexadecimal representation of the given bytes.
	 */
	public static String getHexStringV2(byte[] bytes) {
		char[] chars = "0123456789ABCDEF".toCharArray();
		StringBuilder hex = new StringBuilder();
		
		for (byte elem : bytes) {
			int num = Byte.toUnsignedInt(elem);
			int firstNumber = 0;
			while(num >= 16) {
				firstNumber++;
				num -= 16;
			}
			
			hex.append(chars[firstNumber] + "" + chars[num]);
		}
		
		return hex.toString().toUpperCase();
	}
}