package exception;

/**
 * Exception thrown when attempting to use an invalid user.
 */
public class InvalidUserException extends LibraryException {
    /**
     * Default message for an invalid user exception.
     */
    private static final String DEFAULT_MESSAGE = "Invalid fields for user";

    /**
     * Constructs a new InvalidUserException with the default message.
     */
    public InvalidUserException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructs a new InvalidUserException with a custom message.
     *
     * @param message the custom message describing the exception
     */
    public InvalidUserException(String message) {
        super(message);
    }
}
