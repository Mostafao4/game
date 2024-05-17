package game.dice;

import game.creatures.Realm;

public class ArcanePrism extends Dice{
    private boolean status;

    public ArcanePrism(int value){
        super(value);
        this.setRealm(Realm.WHITE);
    }

    public boolean status(){
        return status;
    }

}
