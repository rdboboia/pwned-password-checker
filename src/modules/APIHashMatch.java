package modules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Checks how many times a given password hash matches known leaked passwords using
 * the "Have I Been Pwned" API.
 * @author -$BOSS$-
 */
public class APIHashMatch {
	/**
	 * Site for the web request.
	 */
	private static final String API_URL = "https://api.pwnedpasswords.com/range/";
	
	/**
	 * Private constructor (no instances allowed).
	 */
	private APIHashMatch() {
	}
	
	/**
	 * Sends a GET request to the external API and gets how many times a password was
	 * leaked (based on its hash) from that response.
	 * @param hexHash the password's SHA-1 hash.
	 * @return how many times the password was leaked (based on its hash).
	 * @throws MalformedURLException if the URL has an unexpected format.
	 * @throws IOException if an unexpected error occurs during the connection opening
	 * or while reading the response.
	 */
	public static int getMatchCount(String hexHash) throws MalformedURLException, IOException {
		URL url = new URL(API_URL + hexHash.substring(0, 5));
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		con.addRequestProperty("User-Agent", "Java App");
		
		int matchCount = fetchMatch(con.getInputStream(), hexHash.substring(5));
		
		con.disconnect();
		hexHash = null;
		
		return matchCount;
	}
	
	/**
	 * Given the response of the API, it searches for the remaining hash characters.
	 * @param inStream the API's response stream.
	 * @param leftoverHexHashCars the remaining hash characters.
	 * @return given the external API used and its response format, returns how many
	 * times the password was leaked (based on its hash). It may return a "-1" if
	 * there was any error while parsing to {@code Integer}.
	 * @throws IOException if an unexpected error occurs while reading the response.
	 */
	private static int fetchMatch(InputStream inStream, String leftoverHexHashCars) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
		
		String line = in.readLine();
		while (line != null) {
			if (line.substring(0, 35).equals(leftoverHexHashCars)) {
				try {
					return Integer.parseInt(line.substring(36));
				} catch (NumberFormatException e) {
					return -1;
				}
			}
			else
				line = in.readLine();
		}
		
		in.close();
		leftoverHexHashCars = null;
		
		return 0;
	}
}