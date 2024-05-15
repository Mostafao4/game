package game.creatures;

import game.dice.Dice;
import game.engine.Move;

public abstract class Creature {
    public abstract void makeMove();
    public abstract Move[] getAllPossibleMoves();
    public abstract Move[] getPossibleMovesForADie(Dice dice);

}
