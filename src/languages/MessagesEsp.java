package languages;

/**
 * Spanish messages for the {@link Messages} class.
 * 
 * @author -$BOSS$-
 */
public class MessagesEsp implements Messages {
	public String getMatches(int matches) {
		return "Ocurrencias: " + matches;
	}

	public String getCongratulations() {
		return "Enhorabuena!";
	}

	public String getNoMatchesFound() {
		return "No han habido coincidencias :D";
	}

	public String getBadNews() {
		return "Malas noticias! :(";
	}

	public String getTimesPasswordFound(int times) {
		return "Tu contraseña se ha encontrado " + times + " veces.";
	}

	public String getComputingHash() {
		return "Calculando hash...";
	}

	public String getMakingRequestAndProcessingResponse() {
		return "Haciendo peticion y procesando respuesta...";
	}

	public String getMalformedUrlExceptionMessage() {
		return "La URL tiene un formato inesperado! :S";
	}

	public String getSha1HashingAlgorithmNotFound() {
		return "No se ha podido encontrar el algoritmo de hash SHA-1! :(";
	}

	public String getUnexpectedIOException() {
		return "Un error ineseperado de E/S ha ocurrido al procesar el resultado :(";
	}
}