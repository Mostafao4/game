package game.dice;

import game.creatures.Realm;

public class YellowDice extends Dice{
    public YellowDice(int value) {
        super(value);
        this.setRealm(Realm.YELLOW);
    }
}
