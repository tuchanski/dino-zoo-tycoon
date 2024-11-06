package exceptions;

public class EntitySpeciesNotFound extends Exception {

    private static final long serialVersionUID = 1149241039409861914L;

    public EntitySpeciesNotFound(String msg){
        super(msg);
    }

    public EntitySpeciesNotFound(String msg, Throwable cause){
        super(msg, cause);
    }

}