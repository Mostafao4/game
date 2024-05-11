package game.dice;

public abstract class Dice {
    private int value;
    private DiceStatus diceStatus;
    public Dice(int value){
        this.value = value;
    }
    public final void roll(){
        value = (int) (Math.random() * 6) + 1;
    }

    public int getValue(){
        return this.value;
    }
    public void setValue(int value) {
        this.value = value;
    }

    public DiceStatus getDiceStatus() {
        return diceStatus;
    }
    public void setDiceStatus(DiceStatus diceStatus) {
        this.diceStatus = diceStatus;
    }
}
