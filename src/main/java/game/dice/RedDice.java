package game.dice;

import game.creatures.Realm;

public class RedDice extends Dice{
    int dragonNumber;
    public RedDice(int value) {
        super(value);
        dragonNumber = 0;
        this.setRealm(Realm.RED);
    }

    public void selectsDragon (int i){
        dragonNumber = i;
    }
    public int getDragonNumber (){
        return dragonNumber;
    }
    public String toString(){
        return "Red: "+this.getValue();
    }
}
