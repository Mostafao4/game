package game.creatures;

import game.collectibles.*;
import game.dice.Dice;
import game.dice.RedDice;
import game.engine.Move;
import game.exceptions.PlayerActionException;

import java.io.FileInputStream;
import java. io.IOException;
import java.util.Properties;

public class Dragon extends Creature {

    private int[][] dragonParts;
    private int[] score;
    private String[] reward;
    private boolean[] bonusBoolean;
    private boolean[] scoringBoolean;
    private int [] possibleDragonNumbers;


    public Dragon() {
        bonusBoolean = new boolean[5];
        scoringBoolean = new boolean[4];
        score = new int[4];
        reward = new String[5];
        dragonParts = new int[4][4];

        // Load properties from configuration files
        Properties prop = new Properties();

        try {
            // Load scores
            prop.load(new FileInputStream("src/main/resources/config/EmberfallDominionScore.properties"));
            for (int i = 0; i < 4; i++) {
                score[i] = Integer.parseInt(prop.getProperty("col" + (i + 1) + "Score"));
            }

            // Load rewards
            prop.load(new FileInputStream("src/main/resources/config/EmberfallDominionRewards.properties"));
            for (int i = 0; i < 4; i++) {
                reward[i] = prop.getProperty("row" + (i + 1) + "Reward");
            }
            reward[4] = prop.getProperty("diagonalReward");

            // Load dice values
            prop.load(new FileInputStream("src/main/resources/config/EmberfallDominionDiceValue.properties"));
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    dragonParts[row][col] = Integer.parseInt(prop.getProperty("row" + (row + 1) + "col" + (col + 1) + "DiceValue"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Reward[] checkReward() {
        Reward[] bonus = new Reward[2];
        int x = 0;
        for (int i = 0; i < dragonParts.length; i++) {
            int c = 0;
            for (int j = 0; j < dragonParts.length; j++) {
                if (dragonParts[i][j] == 0) {
                    c++;
                }
                if (dragonParts[i][j] == 0 && i == j) {
                    x++;
                }
            }
            if (c == dragonParts.length) {
                switch (i) {
                    case 0:
                        if (bonusBoolean[0] == false) {
                            bonus[0] = checkBonusHelper(reward[0]);
                            bonusBoolean[0] = true;
                        }
                        break;
                    case 1:
                        if (bonusBoolean[1] == false) {
                            bonus[0] = checkBonusHelper(reward[1]);
                            bonusBoolean[1] = true;
                        }
                        break;
                    case 2:
                        if (bonusBoolean[2] == false) {
                            bonus[0] = checkBonusHelper(reward[2]);
                            bonusBoolean[2] = true;
                        }
                        break;
                    case 3:
                        if (bonusBoolean[3] == false) {
                            bonus[0] = checkBonusHelper(reward[3]);
                            bonusBoolean[3] = true;
                        }
                        break;
                    default:

                }
            }
        }
        if (x == 4 && bonusBoolean[4] == false) {
            bonus[1] = checkBonusHelper(reward[4]);
            bonusBoolean[4] = true;
        }
        return bonus;
    }

    private Reward checkBonusHelper(String s) {
        switch (s) {
            case "GreenBonus":
                return new Bonus(Realm.GREEN);
            case "YellowBonus":
                return new Bonus(Realm.YELLOW);
            case "BlueBonus":
                return new Bonus(Realm.BLUE);
            case "ElementalCrest":
                return new ElementalCrest(Realm.RED);
            case "ArcaneBoost":
                return new ArcaneBoost();
            default:
                return null;
        }
    }

    public int getPoints() {
        int sum = 0;
        for (int j = 0; j < dragonParts.length; j++) {
            int c = 0;
            for (int i = 0; i < dragonParts.length; i++) {
                if (dragonParts[i][j] == 0) {
                    c++;
                }
            }
            if (c == dragonParts[j].length) {
                switch (j) {
                    case 0:
                        sum = sum + score[0];
                        scoringBoolean[0] = true;
                        break;
                    case 1:
                        sum = sum + score[1];
                        scoringBoolean[1] = true;
                        break;
                    case 2:
                        sum = sum + score[2];
                        scoringBoolean[2] = true;
                        break;
                    case 3:
                        sum = sum + score[3];
                        scoringBoolean[3] = true;
                        break;
                }
            }
        }
        return sum;
    }

    public Move[] getAllPossibleMoves() {
        int c = 0;
        for (int i = 0; i < dragonParts.length; i++) {
            for (int j = 0; j < dragonParts.length; j++) {
                if (dragonParts[i][j] != 0)
                    c++;
            }
        }
        Move[] moves = new Move[c];
        int x = 0;
        for (int i = 0; i < dragonParts.length; i++) {
            for (int j = 0; j < dragonParts.length; j++) {
                if (dragonParts[i][j] != 0) {
                    Dice dice = new RedDice(dragonParts[i][j]);
                    moves[x] = new Move(dice, this, j);
                    x++;
                }
            }
        }
        return moves;
    }

    public Move[] getPossibleMovesForADie(Dice dice) {
        int c = 0;
        for (int i = 0; i < dragonParts.length; i++) {
            for (int j = 0; j < dragonParts.length; j++) {
                if (dragonParts[i][j] != 0 && dragonParts[i][j] == dice.getValue())
                    c++;
            }
        }
        Move[] moves = new Move[c];
        int x = 0;
        for (int j = 0; j < dragonParts.length && x != c; j++) {
            for (int i = 0; i < dragonParts.length && x != c; i++) {
                if (dice.getValue() == dragonParts[i][j]) {
                    moves[x] = new Move(dice, this);
                    x++;
                }
            }
        }
        return moves;
    }
    public int [] getPossibleDragonNumbers (Dice die){
        Move[] b = getPossibleMovesForADie(die);
        possibleDragonNumbers = new int[b.length];
        int i = 0;
        for (int w = 0; w < dragonParts.length; w++) {
            for (int j = 0; j < dragonParts.length; j++) {
                if (dragonParts[j][w] != 0 && dragonParts[j][w] == die.getValue()) {
                    possibleDragonNumbers[i] = w + 1;
                    i++;
                }
            }
        }
        return possibleDragonNumbers;
    }

    public boolean makeMove(Move a) throws PlayerActionException {
        RedDice rd = (RedDice) a.getDice();
        int y = a.getDice().getValue();
        int z = rd.getDragonNumber();
        Move[] b = getPossibleMovesForADie(a.getDice());
        possibleDragonNumbers = new int[b.length];
        int i = 0;
        for (int w = 0; w < dragonParts.length; w++) {
            for (int j = 0; j < dragonParts.length; j++) {
                if (dragonParts[j][w] != 0 && dragonParts[j][w] == y) {
                    possibleDragonNumbers[i] = w + 1;
                    i++;
                }
                if (dragonParts[j][w] == y && z == w + 1) {
                    dragonParts[j][w] = 0;
                    System.out.println("You have successfully attacked Pyroclast Dragon number " + z);
                    return true;
                }
            }
        }
        if (possibleDragonNumbers.length == 1) {
            if (z != possibleDragonNumbers[0]) {
                throw new PlayerActionException("Invalid number, the only dragon that is valid to be attacked is " + possibleDragonNumbers[0]);
            }
        } else {
            if (z != possibleDragonNumbers[0] || z != possibleDragonNumbers[1]) {
                throw new PlayerActionException("Invalid number, the dragons that are valid to be attacked are " + possibleDragonNumbers[0] + " & " + possibleDragonNumbers[1]);
            }
        }

        return false;
    }


    public String toString() {
        return "\n\nScoreSheet\n\n" +
                "Emberfall Dominion: Pyroclast Dragon (RED REALM):\n" +
                "+-----------------------------------+\n" +
                "|  #  |D1   |D2   |D3   |D4   |R    |\n" +
                "+-----------------------------------+\n" +
                "|  F  |" + scoreSheetHelperDiceValue(0, 0) + "|" + scoreSheetHelperDiceValue(0, 1) + "|" + scoreSheetHelperDiceValue(0, 2) + "|" + scoreSheetHelperDiceValue(0, 3) + "|" + scoreSheetHelperBonus(0) + "|\n" +
                "|  W  |" + scoreSheetHelperDiceValue(1, 0) + "|" + scoreSheetHelperDiceValue(1, 1) + "|" + scoreSheetHelperDiceValue(1, 2) + "|" + scoreSheetHelperDiceValue(1, 3) + "|" + scoreSheetHelperBonus(1) + "|\n" +
                "|  T  |" + scoreSheetHelperDiceValue(2, 0) + "|" + scoreSheetHelperDiceValue(2, 1) + "|" + scoreSheetHelperDiceValue(2, 2) + "|" + scoreSheetHelperDiceValue(2, 3) + "|" + scoreSheetHelperBonus(2) + "|\n" +
                "|  H  |" + scoreSheetHelperDiceValue(3, 0) + "|" + scoreSheetHelperDiceValue(3, 1) + "|" + scoreSheetHelperDiceValue(3, 2) + "|" + scoreSheetHelperDiceValue(3, 3) + "|" + scoreSheetHelperBonus(3) + "|\n" +
                "+-----------------------------------+\n" +
                "|  S  |" + scoreSheetHelperScore(0) + "|" + scoreSheetHelperScore(1) + "|" + scoreSheetHelperScore(2) + "|" + scoreSheetHelperScore(3) + "|" + scoreSheetHelperBonus(4) + "|\n" +
                "+-----------------------------------+\n\n" +
                "\n";
    }

    private String scoreSheetHelperDiceValue(int i, int j) {
        if (dragonParts[i][j] == 0) {
            return "X    ";
        } else {
            return dragonParts[i][j] + "    ";
        }
    }

    private String scoreSheetHelperScore(int j) {
        this.getPoints();
        if (scoringBoolean[j] == false) {
            if (score[j] < 9)
                return score[j] + "    ";
            return score[j] + "   ";
        } else {
            return "X    ";
        }
    }

    private String scoreSheetHelperBonus(int i) {
        checkReward();
        switch (reward[i]) {
            case "GreenBonus":
                if (bonusBoolean[i] == true) {
                    return "X    ";
                }
                return "GB   ";
            case "YellowBonus":
                if (bonusBoolean[i] == true) {
                    return "X    ";
                }
                return "YB   ";
            case "BlueBonus":
                if (bonusBoolean[i] == true) {
                    return "X    ";
                }
                return "BB   ";
            case "ElementalCrest":
                if (bonusBoolean[i] == true) {
                    return "X    ";
                }
                return "EC   ";
            case "ArcaneBoost":
                if (bonusBoolean[i] == true) {
                    return "X    ";
                }
                return "AB   ";
            default:
                return "";
        }
    }
}