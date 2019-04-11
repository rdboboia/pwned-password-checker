package simpleapp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;

import languages.Language;
import languages.LanguageManager;
import languages.LanguageParser;
import languages.Messages;
import modules.APIHashMatch;
import modules.HashSHA1;

/**
 * Main driver. It performs an one time check on the provided password and
 * returns how many times the given password was leaked making use of the
 * {@code System.exit()} method. It also prints some informative messages,
 * but nothing essential.
 * <br>
 * Note: if there is any error a "-1" will be returned.
 * 
 * @author -$BOSS$-
 */
public class SimpleApp {
	/**
	 * Private constructor. No instances allowed.
	 */
	private SimpleApp() {
	}
	
	/**
	 * Performs the password check and keeps the user informed of the current
	 * status of the execution. It also prints the value in addition to
	 * returning it.
	 * <br>
	 * The messages can be translated by extending the {@link Messages} class
	 * and setting the desired messages for each part of the message.
	 * 
	 * To check all the available languages check {@link Language} {@code enum}.
	 * 
	 * @param password the password to be checked.
	 * @param language the language of the console messages.
	 */
	public static void execute(String password, String language) {
		// Language selection
		Messages m;
		Language lang = LanguageParser.parse(language);
		if (lang == null) {
			System.out.println(Messages.INVALID_LANGUAGE);
			m = LanguageManager.getMessages(Language.ENG);
		} else {
			m = LanguageManager.getMessages(lang);
		}
		
		try {
			// Computing hash
			System.out.println(m.getComputingHash());
			String hash = HashSHA1.getHexHash(password);
			
			// Making request and processing response
			System.out.println(m.getMakingRequestAndProcessingResponse());
			int result = APIHashMatch.getMatchCount(hash);
			
			// Clearing the hash
			hash = null;
			Runtime.getRuntime().gc();
			
			// Printing result
			System.out.println(m.getMatches(result));
			if (result >= 0) {
				System.exit(result);
			} else {
				System.out.println(m.getMakingRequestAndProcessingResponse());
			}
		} catch (MalformedURLException e) {
			System.out.println(m.getMalformedUrlExceptionMessage());
		} catch (NoSuchAlgorithmException e) {
			System.out.println(m.getSha1HashingAlgorithmNotFound());
		} catch (IOException e) {
			System.out.println(m.getUnexpectedIOException());
		}
		
		// Return -1 in case of any error.
		System.exit(-1);
	}
	
	/**
	 * Starts a new {@link SimpleApp} with the default language (English).
	 * @param password the password to be checked.
	 */
	public static void execute(String password) {
		execute(password, "english");
	}
}