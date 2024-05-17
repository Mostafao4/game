package game.dice;

import game.creatures.Realm;

public class BlueDice extends Dice{
    public BlueDice(int value) {
        super(value);
        this.setRealm(Realm.BLUE);
    }
}
