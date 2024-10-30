package exceptions;

public class UserNotFoundException extends Exception {

    private static final long serialVersionUID = 1149241039409861914L;

    public UserNotFoundException(String msg){
        super(msg);
    }

    public UserNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }
    
}
