package languages;

/**
 * Defines all needed messages for the application with methods so that any class
 * (language) that implements this interface must define it's own messages. Check
 * the {@link MessagesTemplate} template.
 * 
 * @author -$BOSS$-
 */
public interface Messages {
	public static final String INVALID_NUMBER_OF_PARAMETERS = "Invalid number of parameters. Check readme.";
	public static final String INVALID_LANGUAGE = "No matching language was found! Using default language, English.";
	public static final String MESSAGE_NOT_FOUND = "<MESSAGE NOT FOUND>";
	
	String getMatches(int matches);
	String getCongratulations();
	
	String getNoMatchesFound();
	String getBadNews();
	String getTimesPasswordFound(int times);
	
	String getComputingHash();
	String getMakingRequestAndProcessingResponse();
	
	String getMalformedUrlExceptionMessage();
	String getSha1HashingAlgorithmNotFound();
	String getUnexpectedIOException();
}