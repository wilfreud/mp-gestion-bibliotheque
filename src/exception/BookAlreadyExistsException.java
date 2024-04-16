package exception;

public class BookAlreadyExistsException extends LibraryException {
    public final static String DEFAULT_MESSAGE = "This book already exists (unique ISBN constraint)";

    public BookAlreadyExistsException() {
        super(DEFAULT_MESSAGE);
    }

    public BookAlreadyExistsException(String message) {
        super(message);
    }
}
