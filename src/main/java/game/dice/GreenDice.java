package game.dice;

import game.creatures.Realm;

public class GreenDice extends Dice{
    private boolean bonus;

    public GreenDice(int value){
        super(value);
        setRealm(Realm.GREEN);
    }

    public GreenDice(int value, boolean bonus) {
        super(value);
        this.bonus = bonus;
    }

    public boolean isBonus() {
        return bonus;
    }

    public String toString(){
        return "Green: "+this.getValue();
    }

}
