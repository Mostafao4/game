package game.exceptions;

public class PlayerActionException extends Exception{
    public PlayerActionException (){
        super();
    }
    public PlayerActionException (String message){
        super(message);
    }
    public PlayerActionException (String message, Throwable cause){
        super(message, cause);
    }
    public PlayerActionException (Throwable cause){
        super(cause);
    }
}
