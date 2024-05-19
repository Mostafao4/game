package game.dice;

import game.creatures.Realm;

public class GreenDice extends Dice{

    public GreenDice(int value){
        super(value);
        this.setRealm(Realm.GREEN);
        //realValue = value + ArcanePrism.getValue();
    }


    public String toString(){
        return "Green: "+this.getValue();
    }

}
