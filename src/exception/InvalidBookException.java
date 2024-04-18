package exception;

/**
 * Exception thrown when attempting to use an invalid book.
 */
public class InvalidBookException extends LibraryException {
    /**
     * Default message for an invalid book exception.
     */
    private static final String DEFAULT_MESSAGE = "Invalid book; make sure all required fields are not empty";

    /**
     * Constructs a new InvalidBookException with the default message.
     */
    public InvalidBookException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructs a new InvalidBookException with a custom message.
     *
     * @param message the custom message describing the exception
     */
    public InvalidBookException(String message) {
        super(message);
    }

}
