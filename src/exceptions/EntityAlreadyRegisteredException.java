package exceptions;

public class EntityAlreadyRegisteredException extends Exception {

    private static final long serialVersionUID = 1149241039409861914L;

    public EntityAlreadyRegisteredException(String msg){
        super(msg);
    }

    public EntityAlreadyRegisteredException(String msg, Throwable cause){
        super(msg, cause);
    }
    
}
