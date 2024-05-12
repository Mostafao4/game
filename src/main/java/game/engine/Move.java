package game.engine;

import game.creatures.Creature;
import game.dice.Dice;

public class Move implements Comparable{
    private Dice dice;
    private Creature creature;



    @Override
    public int compareTo(Object o) {
        return 0;
    }
    public Dice[] getDice() {
        return new Dice[0];
    }
}
