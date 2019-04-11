package languages;

/**
 * Takes a {@link Language} and returns a new {@link Messages} instance which
 * corresponds to the given {@code language}.
 * 
 * @author -$BOSS$-
 */
public class LanguageManager {
	/**
	 * Private constructor. No instances allowed.
	 */
	private LanguageManager() {
	}
	
	/**
	 * Based on the given {@link Language}, it returns a new {@link Messages}
	 * instance corresponding to that language which should contain all the
	 * messages for that language.
	 * @param language the desires language for the messages.
	 * @return a new {@link Messages} object.
	 */
	public static Messages getMessages(Language language) {
		switch (language) {
			case ESP:
				return new MessagesEsp();
			default:
				return new MessagesEng();
		}
	}
}
