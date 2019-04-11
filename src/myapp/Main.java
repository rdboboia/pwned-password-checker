package myapp;

import languages.LanguageParser;
import languages.Messages;
import simpleapp.SimpleApp;
import standalone.SantaloneApp;

/**
 * The app's main.
 * Using args to predict user's needs. Based on the number of args:<br>
 * - 0: it will launch the GUI.<br>
 * - 1: a password is expected. It will launch a one time check which is expected
 * to be used from the command line. It will give some messages but, more
 * importantly, it will return the number of times the password was leaked or,
 * in case of any error, a "-1". Again, if it's launched from the command line
 * it will print the error message in addition to the return value. It uses the
 * default language for the messages which is English.<br>
 * - 2: same as with 1 parameter but it takes a second parameter which is the
 * desired language for the messages. If there is no language match it will
 * use the default one which is English. Check {@link LanguageParser} for mode
 * details on the currently supported languages and their expected {@code String}
 * format.
 * 
 * @author -$BOSS$-
 */
public class Main {
	
	/**
	 * The app's main.
	 * 
	 * @param args different launch methods based on the args. See {@link Main}.
	 */
	public static void main(String[] args) {
		switch (args.length) {
			case 0:
				// TODO: implement the new GUI. Using the old one for now.
				SantaloneApp.main(args);
				break;
			case 1:
				// Launch console app with default language (English).
				SimpleApp.execute(args[0]);
				break;
			case 2:
				// Launch console app and try to use the language given in the second param.
				SimpleApp.execute(args[0], args[1]);
				break;
			case 3:
				// Launch an interactive console app.
				System.out.println("Not implemented yet! In the future... maybe :'D");
				break;
			default:
				System.out.println(Messages.INVALID_NUMBER_OF_PARAMETERS);
				break;
		}
	}
}