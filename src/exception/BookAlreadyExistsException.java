package exception;

/**
 * This exception is thrown when attempting to add a book with an existing ISBN.
 */
public class BookAlreadyExistsException extends LibraryException {
    public final static String DEFAULT_MESSAGE = "This book already exists (unique ISBN constraint)";

    /**
     * Constructs a new BookAlreadyExistsException with a default message.
     */
    public BookAlreadyExistsException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructs a new BookAlreadyExistsException with a specified message.
     *
     * @param message The detail message explaining the exception.
     */
    public BookAlreadyExistsException(String message) {
        super(message);
    }
}
