package game.dice;

public class ArcanePrism extends Dice{
    private int value;
    private boolean status;

    public ArcanePrism(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public boolean status(){
        return status;
    }
}
