package game.dice;

import game.creatures.Realm;
import game.engine.GameBoard;

public class GreenDice extends Dice{
    private int realValue;

    public GreenDice(int value){
        super(value);
        this.setRealm(Realm.GREEN);
        //realValue = value + ArcanePrism.getValue();
    }

    public int getRealValue(){
        return this.realValue;
    }

    private boolean checkAvailability(ArcanePrism die){
        if(die.getDiceStatus().equals(DiceStatus.AVAILABLE)){
            return true;
        }
        return false;
    }

}
