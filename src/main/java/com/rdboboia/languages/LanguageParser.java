package com.rdboboia.languages;

/**
 * A simple class with only one static method to parse a language from a
 * {@link String} format to a {@link Language} enum. It is expected to be used
 * for working with the {@link LanguageManager}.
 * 
 * @author -$BOSS$-
 */
public class LanguageParser {
	/**
	 * Private constructor. No instances allowed.
	 */
	private LanguageParser() {
	}
	
	/**
	 * Checks if the given {@code String} matches any defines language, like "eng"
	 * or "english" for English, ignoring case.
	 * 
	 * @param language the language to be looked for.
	 * @return a {@link Language} enum value or null if no languages could be
	 * found from the given {@code String}.
	 */
	public static Language parse(String language) {
		String lang = language.toLowerCase();
		if (lang.equals("spanish") || lang.equals("espa�ol") || lang.startsWith("esp"))
			return Language.ESP;
		else if (lang.equals("english") || lang.startsWith("eng"))
			return Language.ENG;
		else
			return null;
	}
}