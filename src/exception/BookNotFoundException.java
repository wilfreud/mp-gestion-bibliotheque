package exception;

public class BookNotFoundException extends LibraryException {
    private static final String DEFAULT_MESSAGE = "This book doesn't exist";

    public BookNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}
