package game.dice;

public class GreenDice extends Dice{
    private int value;
    private boolean status;

    public GreenDice(int value){
        this.value = value;
        this.status = false;
    }

    public int getValue(){
        return this.value;
    }

    public boolean status(){
        return status;
    }
}
