package exception;

/**
 * This exception is thrown when a requested book cannot be found in the library.
 */
public class BookNotFoundException extends LibraryException {
    private static final String DEFAULT_MESSAGE = "This book doesn't exist";

    /**
     * Constructs a new BookNotFoundException with a default message.
     */
    public BookNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructs a new BookNotFoundException with a specified message.
     *
     * @param message The detail message explaining the exception.
     */
    public BookNotFoundException(String message) {
        super(message);
    }
}
