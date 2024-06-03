package game.engine;
import game.creatures.Dragon;
import game.creatures.Realm;
import game.creatures.Creature;
import game.dice.Dice;
import game.dice.RedDice;

public class Move implements Comparable<Move>{
    private Dice dice;
    private MoveType moveType;
    private Creature creature;
    private int dragonNumber;
    private boolean whiteMove;
    public Move(Dice dice, Creature creature) {
        this.dice = dice;
        this.creature = creature;
    }
    public Move(Dice dice, Creature creature, int dragonNumber) {
        this.dice = dice;
        this.creature = creature;
        this.dragonNumber = dragonNumber;
    }
    public Move(Dice dice, Creature creature, MoveType moveType) {
        this.dice = dice;
        this.moveType = moveType;
        this.creature = creature;
    }

    public Move(Dice dice, Creature creature, int dragonNumber,MoveType moveType) {
        this.dice = dice;
        this.moveType = moveType;
        this.creature = creature;
        this.dragonNumber = dragonNumber;
    }




    @Override
    public int compareTo(Move o) {
        int diceComparison = Integer.compare(this.dice.getValue(), o.dice.getValue());
        if (diceComparison != 0) {
            return diceComparison;
        }
    
        if (this.creature instanceof Dragon && o.creature instanceof Dragon) {
            int thisDragonNumber = ((RedDice) this.getDice()).getDragonNumber();
            int otherDragonNumber = ((RedDice) o.getDice()).getDragonNumber();
            return Integer.compare(thisDragonNumber, otherDragonNumber);
        }
    
        // Define additional comparison logic if necessary
        // Example: comparing by some other properties
        // return this.someOtherProperty.compareTo(o.someOtherProperty);
    
        // If creatures are not both Dragons or have no further distinguishing properties
        return 0; // Consider them equal if all other properties are equal
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public boolean isWhiteMove() {
        return whiteMove;
    }

    public void setWhiteMove(boolean whiteMove) {
        this.whiteMove = whiteMove;
    }

    public MoveType getMoveType() {
        return moveType;
    }

    public void setMoveType(MoveType moveType) {
        this.moveType = moveType;
    }

    public Creature getCreature() {
        return creature;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }
    public int getDragonNumber() {
        return dragonNumber;
    }
    public String toString(){
        return "The dice value " + this.getDice() + " , and the available dragon(s) to be attacked with this dice value is/are  " + this.dragonNumber;
    }
//        String s = "";
//        Move [] a = null;
//        try {
//            a = creature.getPossibleMovesForADie(dice);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        if (a.length == 2 ){
//            s = s + a[0].dice.getValue() + " " + a[0].dragonNumber + "\n" + a[1].dice.getValue() + " " + a[1].dragonNumber;
//        }
//        else {
//            if (a.length == 1 ){
//                s = s + a[1].dice.getValue() + " " + a[1].dragonNumber;
//            }
//            else {
//                s = "T";
//            }
//        }
//        return s;
//    }
}
