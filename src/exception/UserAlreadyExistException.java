package exception;

/**
 * Exception thrown when attempting to add a user that already exists.
 */
public class UserAlreadyExistException extends LibraryException {
    /**
     * Default message for a user already exists exception.
     */
    public final static String DEFAULT_MESSAGE = "This user already exists";

    /**
     * Constructs a new UserAlreadyExistException with the default message.
     */
    public UserAlreadyExistException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructs a new UserAlreadyExistException with a custom message.
     *
     * @param message the custom message describing the exception
     */
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
