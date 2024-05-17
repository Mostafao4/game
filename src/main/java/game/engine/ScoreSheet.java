package game.engine;

import game.creatures.*;
import game.dice.Dice;

public class ScoreSheet {
     Dragon dragon = new Dragon();
     Gaia gaia = new Gaia();
     Hydra hydra = new Hydra();
     Phoenix phoenix = new Phoenix();
     Lion lion = new Lion();
     public String toString(){
        return (dragon.toString()+
        gaia.toString()+
        hydra.toString()+
        phoenix.toString()+
        lion.toString());
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
