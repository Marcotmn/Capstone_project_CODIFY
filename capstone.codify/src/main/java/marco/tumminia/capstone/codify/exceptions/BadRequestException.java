package marco.tumminia.capstone.codify.exceptions;

@SuppressWarnings("serial")
public class BadRequestException extends RuntimeException {

	//ERROR 400
	public BadRequestException(String message) {
		super(message);
	}

}
