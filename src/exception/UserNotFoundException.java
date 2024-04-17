package exception;

public class UserNotFoundException extends LibraryException {
    private static final String DEFAULT_MESSAGE = "This user doesn't exist";

    public UserNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
