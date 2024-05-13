package game.collectibles;

import game.creatures.Realm;

public class ElementalCrest extends Reward{
    private Realm realm;
    private boolean status;
    public ElementalCrest(Realm realm) {
        this.realm = realm;
        this.status = false;
    }

    public Realm getRealm() {
        return realm;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus() {
        this.status = true;
    }
}
