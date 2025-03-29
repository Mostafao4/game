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
        return "\u001B[31m"+dragon.toString()+"\u001B[0m"+
                "\u001B[32m"+gaia.toString()+"\u001B[0m"+
                "\u001B[34m"+hydra.toString()+"\u001B[0m"+
                "\u001B[35m"+phoenix.toString()+"\u001B[0m"+
                "\u001B[33m"+lion.toString()+"\u001B[0m";
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
