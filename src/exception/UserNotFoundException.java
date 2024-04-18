package exception;

/**
 * Exception thrown when a user is not found.
 */
public class UserNotFoundException extends LibraryException {
    /**
     * Default message for a user not found exception.
     */
    private static final String DEFAULT_MESSAGE = "This user doesn't exist";

    /**
     * Constructs a new UserNotFoundException with the default message.
     */
    public UserNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructs a new UserNotFoundException with a custom message.
     *
     * @param message the custom message describing the exception
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
