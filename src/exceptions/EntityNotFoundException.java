package exceptions;

public class EntityNotFoundException extends Exception {

    private static final long serialVersionUID = 1149241039409861914L;

    public EntityNotFoundException(String msg){
        super(msg);
    }

    public EntityNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }
    
}
