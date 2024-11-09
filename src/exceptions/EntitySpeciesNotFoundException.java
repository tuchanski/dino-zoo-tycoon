package exceptions;

public class EntitySpeciesNotFoundException extends Exception {

    private static final long serialVersionUID = 1149241039409861914L;

    public EntitySpeciesNotFoundException(String msg){
        super(msg);
    }

    public EntitySpeciesNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }

}