package game.dice;

import game.creatures.Realm;

public abstract class Dice {
    private int value;
    private DiceStatus diceStatus;
    private Realm realm;

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

    public Realm getRealm() {
        return realm;
    }
    public void setRealm(Realm realm) {
        this.realm = realm;
    }

    public DiceStatus getDiceStatus() {
        return diceStatus;
    }
    public void setDiceStatus(DiceStatus diceStatus) {
        this.diceStatus = diceStatus;
    }
}
