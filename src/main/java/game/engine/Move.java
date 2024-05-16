package game.engine;
import game.creatures.Dragon;
import game.creatures.Realm;
import game.creatures.Creature;
import game.dice.Dice;

public class Move implements Comparable<Move>{
    private Dice dice;
    private Creature creature;
    private int dragonNumber;
    public Move(Dice dice, Creature creature) {
        this.dice = dice;
        this.creature = creature;
    }
    public Move(Dice dice, Creature creature, int dragonNumber) {
        this.dice = dice;
        this.creature = creature;
        this.dragonNumber = dragonNumber;
    }

    @Override
    public int compareTo(Move o) {
        if(o.creature instanceof Dragon){
            if(this.dice.getValue() == o.getDice().getValue() && this.dice.getRealm() == o.getDice().getRealm() ){
                return 0;
            }
        }
        return 1;
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public Creature getCreature() {
        return creature;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }
}
