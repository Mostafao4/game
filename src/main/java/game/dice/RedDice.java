package game.dice;

import game.creatures.Realm;

public class RedDice extends Dice{
    int [] dragonNumber;


    public RedDice(int value) {
        super(value);
        dragonNumber = new int[]{1, 2, 3, 4};
        this.setRealm(Realm.RED);
    }

    public int selectsDragon (int i){
        return dragonNumber[i];
    }
    public String toString(){
        return "Red: "+this.getValue();
    }
}
