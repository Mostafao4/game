package game.dice;

public abstract class Dice {
    private int value;
    private DiceStatus diceStatus;
    public Dice(int value){
        this.value = value;
    }
    public void roll(){
        value = (int) (Math.random() * 6) + 1;
    }
    public int getValue(){
        return this.value;
    }
    public abstract boolean status();
}
