package game.collectibles;

import game.creatures.Realm;

public class ElementalCrest extends Reward{
    private Realm realm;
    private RewardType rewardType;

    public ElementalCrest(Realm realm) {
        this.realm = realm;
        rewardType = RewardType.CREST;
    }

    public Realm getRealm() {
        return realm;
    }
}
