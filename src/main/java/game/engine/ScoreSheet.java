package game.engine;

import game.creatures.*;
import game.dice.Dice;

public class ScoreSheet {
     private Dragon dragon;
     private Gaia gaia;
     private Hydra hydra;
     private Phoenix phoenix;
     private Lion lion;

    public ScoreSheet() {
        this.dragon = new Dragon();
        this.gaia = new Gaia();
        this.hydra = new Hydra();
        this.phoenix = new Phoenix();
        this.lion = new Lion();
    }

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

    public Dragon getDragon() {
        return dragon;
    }
    public Gaia getGaia() {
        return gaia;
    }
    public Hydra getHydra() {
        return hydra;
    }
    public Phoenix getPhoenix() {
        return phoenix;
    }
    public Lion getLion() {
        return lion;
    }



}
