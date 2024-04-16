package exception;

public class AlreadyBorrowedBookExeption extends LibraryException {
    private final static String DEFAULT_MESSAGE = "Book has already been borrowed";

    public AlreadyBorrowedBookExeption() {
        super(DEFAULT_MESSAGE);
    }

    public AlreadyBorrowedBookExeption(String message) {
        super(message);
    }
}
