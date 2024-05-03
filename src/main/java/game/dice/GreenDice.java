package game.dice;

public class GreenDice extends Dice{
    private boolean status;

    public GreenDice(int value){
        super(value);
        this.status = false;
    }

    public boolean status(){
        return status;
    }
}
