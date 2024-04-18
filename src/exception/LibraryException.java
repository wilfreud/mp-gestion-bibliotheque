package exception;

/**
 * Base exception class for library-related exceptions.
 */
public class LibraryException extends Exception {
    /**
     * Default message for a library exception.
     */
    private static final String DEFAULT_MESSAGE = "";

    /**
     * Constructs a new LibraryException with the default message.
     */
    public LibraryException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructs a new LibraryException with a custom message.
     *
     * @param message the custom message describing the exception
     */
    public LibraryException(String message) {
        super(message);
    }
}
