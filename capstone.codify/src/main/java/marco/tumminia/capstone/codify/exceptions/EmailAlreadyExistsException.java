package marco.tumminia.capstone.codify.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super("L'email " + email + " inserita è già stata utilizzata!");
    }
}