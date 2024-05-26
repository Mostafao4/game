package game.creatures;

import game.collectibles.Reward;
import game.dice.Dice;
import game.engine.Move;
import game.exceptions.InvalidDiceSelectionException;
import game.exceptions.InvalidMoveException;
import game.exceptions.PlayerActionException;

public abstract class Creature {
    public abstract boolean makeMove(Move move) throws InvalidMoveException, PlayerActionException;
    public abstract Move[] getAllPossibleMoves() ;
    public abstract Move[] getPossibleMovesForADie(Dice dice) throws InvalidDiceSelectionException;
    public abstract Reward[] checkReward();
}
