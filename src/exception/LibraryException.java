package exception;

public class LibraryException extends Exception{
    private static final String DEFAULT_MESSAGE = "";
    public LibraryException() {
        super(DEFAULT_MESSAGE);
    }
     public LibraryException(String message){
        super(message);
     }
}
