package game.exceptions;

public class DiceRollException extends Exception{
    public DiceRollException (){
        super();
    }
    public DiceRollException (String message){
        super(message);
    }
    public DiceRollException (String message, Throwable cause){
        super(message, cause);
    }
    public DiceRollException (Throwable cause){
        super(cause);
    }
}
