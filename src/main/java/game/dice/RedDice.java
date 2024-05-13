package game.dice;

public class RedDice extends Dice{
    int [] dragonNumber;


    public RedDice(int value) {
        super(value);
        dragonNumber = new int[]{1, 2, 3, 4};
    }

    public int selectsDragon (int i){
        return dragonNumber[i];
    }
}
