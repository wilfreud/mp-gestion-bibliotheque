package exception;

/**
 * Exception thrown when attempting unauthorized book borrowing.
 */
public class UnauthorizedBookBorrowException extends LibraryException {
    /**
     * Default message for an unauthorized book borrowing exception.
     */
    private static final String DEFAULT_MESSAGE = "Umauthorized book borrowing";

    /**
     * Constructs a new UnauthorizedBookBorrowException with the default message.
     */
    public UnauthorizedBookBorrowException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructs a new UnauthorizedBookBorrowException with a custom message.
     *
     * @param message the custom message describing the exception
     */
    public UnauthorizedBookBorrowException(String message) {
        super(message);
    }
}
