package languages;

/**
 * English messages for the {@link Messages} class.
 * 
 * @author -$BOSS$-
 */
public class MessagesEng implements Messages {
	public String getMatches(int matches) {
		return "Matches: " + matches;
	}

	public String getCongratulations() {
		return "Congratulations!";
	}

	public String getNoMatchesFound() {
		return "No matches were found! :D";
	}

	public String getBadNews() {
		return "Bad news :(";
	}

	public String getTimesPasswordFound(int times) {
		return "Your password was found " + times + " times.";
	}

	public String getComputingHash() {
		return "Computing hash...";
	}

	public String getMakingRequestAndProcessingResponse() {
		return "Making request and processing response...";
	}

	public String getMalformedUrlExceptionMessage() {
		return "The URL has an unexpected format! :S";
	}

	public String getSha1HashingAlgorithmNotFound() {
		return "The SHA-1 hashing algorithm could not be found! :(";
	}

	public String getUnexpectedIOException() {
		return "An unexpected I/O error ocurred while processing the response! :(";
	}
}