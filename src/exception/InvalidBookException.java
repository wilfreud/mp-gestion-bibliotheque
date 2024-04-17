package exception;

public class InvalidBookException extends LibraryException{
    private static final String DEFAULT_MESSAGE = "Invalid book; make sure all required fields are not empty";
    public InvalidBookException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidBookException(String message) {
        super(message);
    }

}
