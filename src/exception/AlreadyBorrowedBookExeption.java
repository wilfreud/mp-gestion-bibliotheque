package exception;

/**
 * This exception is thrown when a book has already been borrowed.
 */
public class AlreadyBorrowedBookExeption extends LibraryException {
    private final static String DEFAULT_MESSAGE = "Book has already been borrowed";

    /**
     * Constructs a new AlreadyBorrowedBookExeption with a default message.
     */
    public AlreadyBorrowedBookExeption() {
        super(DEFAULT_MESSAGE);
    }


    /**
     * Constructs a new AlreadyBorrowedBookExeption with a specified message.
     *
     * @param message The detail message explaining the exception.
     */
    public AlreadyBorrowedBookExeption(String message) {
        super(message);
    }
}
