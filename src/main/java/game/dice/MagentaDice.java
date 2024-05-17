package game.dice;

import game.creatures.Realm;

public class MagentaDice extends Dice{
    public MagentaDice(int value) {
        super(value);
        this.setRealm(Realm.MAGENTA);
    }
}
