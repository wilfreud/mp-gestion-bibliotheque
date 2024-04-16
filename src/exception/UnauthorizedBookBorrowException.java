package exception;

public class UnauthorizedBookBorrowException extends LibraryException{
    private static final String DEFAULT_MESSAGE = "Umauthorized book borrowing";
    public UnauthorizedBookBorrowException(){
        super(DEFAULT_MESSAGE);
    }
    public UnauthorizedBookBorrowException(String message){
        super(message);
    }
}
