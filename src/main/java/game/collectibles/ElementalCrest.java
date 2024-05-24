package game.collectibles;

import game.creatures.Realm;

public class ElementalCrest extends Reward{
    private Realm realm;
    public ElementalCrest(Realm realm) {
        this.realm = realm;
    }

    public Realm getRealm() {
        return realm;
    }
}
