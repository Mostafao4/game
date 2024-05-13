package game.collectibles;

import game.creatures.Realm;

public class Bonus extends Reward{
    private Realm realm;
    public Bonus(Realm realm){
        this.realm = realm;
    }

    public Realm getRealm() {
        return realm;
    }

}
