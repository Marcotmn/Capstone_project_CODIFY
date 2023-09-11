package marco.tumminia.capstone.codify.exceptions;

@SuppressWarnings("serial")
public class UnauthorizedException extends RuntimeException {

	public UnauthorizedException(String message) {
		super(message);
	}
}
