package game.engine;

import game.dice.Dice;

public interface Player {
    GameScore gameScore;
    PlayerStatus playerStatus;
    Dice selectedDice;
    int timeWarpCount;
    int arcaneBoostCount;
    boolean testFlag;
}
