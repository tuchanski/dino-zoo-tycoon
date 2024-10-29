package models.exceptions;

public class UserAlreadyRegisteredException extends Exception {

    private static final long serialVersionUID = 1149241039409861914L;

    public UserAlreadyRegisteredException(String msg){
        super(msg);
    }

    public UserAlreadyRegisteredException(String msg, Throwable cause){
        super(msg, cause);
    }
    
}
