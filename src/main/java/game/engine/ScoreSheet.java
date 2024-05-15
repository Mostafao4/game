package game.engine;

import game.creatures.*;
import game.dice.Dice;

public class ScoreSheet {
     Dragon dragon;
     Gaia gaia;
     Hydra hydra;
     Phoenix phoenix;
     Lion lion;
     public String toString(){
        return "";
     }
    public Creature getCreatureByRealm(Dice dice) {
        switch (dice.getRealm()) {
            case RED:
                return dragon;
            case GREEN:
                return gaia;
            case BLUE:
                return hydra;
            case MAGENTA:
                return phoenix;
            case YELLOW:
                return lion;
            default:
                return null;
        }
    }
}
