package game.exceptions;

public class InvalidDiceSelectionException extends Exception{
    public InvalidDiceSelectionException (){
        super();
    }
    public InvalidDiceSelectionException (String message){
        super(message);
    }
    public InvalidDiceSelectionException (String message, Throwable cause){
        super(message, cause);
    }
    public InvalidDiceSelectionException (Throwable cause){
        super(cause);
    }
}
