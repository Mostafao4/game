package game.engine;
import game.creatures.Realm;
import game.creatures.Creature;
import game.dice.Dice;

public class Move implements Comparable{
    private Dice dice;
    private Realm realm;
    public Move(Dice dice, Realm realm) {
        this.dice = dice;
        this.realm = realm;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public Realm getRealm() {
        return realm;
    }

    public void setRealm(Realm realm) {
        this.realm = realm;
    }
}
