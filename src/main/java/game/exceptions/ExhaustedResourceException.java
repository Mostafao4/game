package game.exceptions;

public class ExhaustedResourceException extends Exception{
    public ExhaustedResourceException (){
        super();
    }
    public ExhaustedResourceException (String message){
        super(message);
    }
    public ExhaustedResourceException (String message, Throwable cause){
        super(message, cause);
    }
    public ExhaustedResourceException (Throwable cause){
        super(cause);
    }
}
