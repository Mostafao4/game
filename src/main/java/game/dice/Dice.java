package game.dice;

public abstract class Dice {
    private int value;

    public Dice(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
    public abstract boolean status();
}
