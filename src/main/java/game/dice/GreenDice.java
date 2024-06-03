package game.dice;

import game.creatures.Realm;

public class GreenDice extends Dice{
    public GreenDice(int value){
        super(value);
        setRealm(Realm.GREEN);
    }




    public String toString(){
        return "Green: "+this.getValue();
    }

}
