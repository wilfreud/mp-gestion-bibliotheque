package exception;

public class UserAlreadyExistException extends LibraryException {
    public final static String DEFAULT_MESSAGE = "This user already exists";

    public UserAlreadyExistException() {
        super(DEFAULT_MESSAGE);
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
