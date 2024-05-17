package game.engine;

import game.creatures.*;
import game.dice.Dice;

public class ScoreSheet {
     Dragon dragon = new Dragon();
     Gaia gaia = new Gaia();
     Hydra hydra = new Hydra();
     Phoenix phoenix = new Phoenix();
     Lion lion = new Lion();
     public void print(){
        System.out.println(dragon);
        System.out.println(gaia);
        System.out.println(hydra);
        System.out.println(phoenix);
        System.out.println(lion);
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
