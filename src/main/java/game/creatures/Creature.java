package game.creatures;

import game.dice.Dice;
import game.engine.Move;

public abstract class Creature {
    public abstract boolean makeMove(Move move) throws Exception;
    public abstract Move[] getAllPossibleMoves();
    public abstract Move[] getPossibleMovesForADie(Dice dice) throws Exception;

}
