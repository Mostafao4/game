package game.creatures;

import game.collectibles.Reward;
import game.dice.Dice;
import game.engine.Move;
import game.exceptions.InvalidDiceSelectionException;
import game.exceptions.InvalidMoveException;

public abstract class Creature {
    public abstract boolean makeMove(Move move) throws InvalidMoveException;
    public abstract Move[] getAllPossibleMoves() throws InvalidMoveException;
    public abstract Move[] getPossibleMovesForADie(Dice dice) throws InvalidDiceSelectionException;
    public abstract Reward[] checkReward();
}
