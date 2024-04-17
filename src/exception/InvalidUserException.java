package exception;

public class InvalidUserException extends  LibraryException{
    private  static  final  String DEFAULT_MESSAGE = "Invalid fields for user";

    public InvalidUserException(){
        super(DEFAULT_MESSAGE);
    }

    public InvalidUserException(String message) {
        super(message);
    }
}
