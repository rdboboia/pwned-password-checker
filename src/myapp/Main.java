package myapp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;

import modules.APIHashMatch;
import modules.HashSHA1;

public class Main {

	public static void main(String[] args) throws MalformedURLException, NoSuchAlgorithmException, IOException {
		if (args.length == 0) {
			// For testing purposes.
			// FIXME: remove once done with testing.
			System.out.println(APIHashMatch.getMatchCount(HashSHA1.getHexHash("1234")));
		}
		if (args.length == 1) {
			System.out.println(APIHashMatch.getMatchCount(HashSHA1.getHexHash(args[0])));
		}
	}
}