package game.exceptions;

public class RewardException extends Exception{
    public RewardException (){
        super();
    }
    public RewardException (String message){
        super(message);
    }
    public RewardException (String message, Throwable cause){
        super(message, cause);
    }
    public RewardException (Throwable cause){
        super(cause);
    }
}
