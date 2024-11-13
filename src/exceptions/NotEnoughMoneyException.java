package exceptions;

public class NotEnoughMoneyException extends Exception {

    private static final long serialVersionUID = 1149241039409861914L;

    public NotEnoughMoneyException(String msg) {
        super(msg);
    }

}
