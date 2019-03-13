package thirdpartycode;

/*
 * Copyright notice
 * 
 * THIS CODE was take from Stack Overflow. I do not own any right on it and this
 * class is published under the same license as found in stack overflow, which
 * is the Creative Commons Attribution Share Alike license.
 * 
 * Original post: https://stackoverflow.com/a/9855338
 * 
 * Author name: maybeWeCouldStealAVan
 * Author profile: https://stackoverflow.com/users/1284661/maybewecouldstealavan
 */

public class BytesToHexString {
	private BytesToHexString() {
		// Private constructor
	}
	
	// THIS CODE
	public static String getHexString(byte[] bytes) {
		final char[] hexArray = "0123456789ABCDEF".toCharArray();
		char[] hexChars = new char[bytes.length * 2];
		
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		
		return new String(hexChars);
	}
}