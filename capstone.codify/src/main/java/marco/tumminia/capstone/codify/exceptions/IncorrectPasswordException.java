package marco.tumminia.capstone.codify.exceptions;

@SuppressWarnings("serial")
public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException(String message) {
        super(message);
    }
}
