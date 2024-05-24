package game.collectibles;

import game.creatures.Realm;

public class Bonus extends Reward{
    private Realm realm;
    private RewardType rewardType;
    public Bonus(Realm realm){
        rewardType = RewardType.BONUS;
        this.realm = realm;
    }

    public Realm getRealm() {
        return realm;
    }

}
