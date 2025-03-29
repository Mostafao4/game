package game.exceptions;

public class CommandFormatException extends Exception{
    public CommandFormatException (){
        super();
    }
    public CommandFormatException (String message){
        super(message);
    }
    public CommandFormatException (String message, Throwable cause){
        super(message, cause);
    }
    public CommandFormatException (Throwable cause){
        super(cause);
    }
}
