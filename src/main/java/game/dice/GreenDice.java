package game.dice;

import game.engine.GameBoard;

public class GreenDice extends Dice{
    int realValue;

    public GreenDice(int value){
        super(value);
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
