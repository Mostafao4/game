# Project Skeleton

## Folder Structure

```
Dice-Realms/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── game/
│   │   │       ├── collectibles/
│   │   │       │   ├── Reward.java
│   │   │       │   ├── Power.java
│   │   │       │   ├── Bonus.java
│   │   │       │   ├── ElementalCrest.java
│   │   │       │   ├── ArcaneBoost.java
│   │   │       │   ├── TimeWarp.java
│   │   │       │   └── EssenceBonus.java
│   │   │       │ 
│   │   │       ├── creatures/
│   │   │       │   ├── Creature.java
│   │   │       │   ├── Dragon.java
│   │   │       │   ├── Gaia.java
│   │   │       │   ├── Hydra.java
│   │   │       │   ├── Phoenix.java
│   │   │       │   └── Lion.java
│   │   │       │ 
│   │   │       ├── dice/
│   │   │       │   ├── Dice.java
│   │   │       │   ├── RedDice.java
│   │   │       │   ├── GreenDice.java
│   │   │       │   ├── BlueDice.java
│   │   │       │   ├── MagentaDice.java
│   │   │       │   ├── YellowDice.java
│   │   │       │   └── ArcanePrism.java
│   │   │       │ 
│   │   │       ├── engine/
│   │   │       │   ├── GameController.java
│   │   │       │   ├── CLIGameController.java
│   │   │       │   ├── GameBoard.java
│   │   │       │   ├── Player.java
│   │   │       │   ├── ScoreSheet.java
│   │   │       │   ├── GameStatus.java
│   │   │       │   ├── GameScore.java
│   │   │       │   └── Move.java
│   │   │       │ 
│   │   │       ├── exceptions/
│   │   │       │   ├── RewardException.java
│   │   │       │   ├── PlayerActionException.java
│   │   │       │   ├── InvalidMoveException.java
│   │   │       │   ├── InvalidDiceSelectionException.java
│   │   │       │   ├── DiceRollException.java
│   │   │       │   ├── CommandFormatException.java
│   │   │       │   └── ExhaustedResourceException.java
│   │   │       ├── gui/
│   │   │       │
│   │   │       └── Main.java
│   │   │
│   │   └── resources/
│   │       ├── images/
│   │       ├── config/
│   │       │   ├── EmberfallDominionDiceValue.properties
│   │       │   ├── EmberfallDominionScore.properties
│   │       │   ├── EmberfallDominionRewards.properties
│   │       │   ├── RoundsRewards.properties
│   │       │   ├── MysticalSkyRewards.properties
│   │       │   ├── TerrasHeartlandRewards.properties
│   │       │   ├── TerrasHeartlandRewards.properties
│   │       │   ├── RadiantSvannaMultipliers.properties
│   │       │   ├── RadiantSvannaRewards.properties
│   │       │   ├── TideAbyssRewards.properties
│   │       │   └── TideAbyssScore.properties
│   │       └── EmptyScoreSheet.txt
│   │
│   └── test/
│       └── java/
│           └── game/
│               ├── collectibles/
│               ├── creatures/
│               ├── dice/
│               ├── engine/
│               ├── exceptions/
│               └── gui/
│
├── pom.xml
├── Grades.md
└── README.md
```

## Packages


### game.collectibles

This package includes classes for different types of game rewards and powers that players can collect:

- `Reward`: Abstract base class for all rewards.
- `Power`: Abstract base class for power-up abilities.
- `Bonus`: Base class for the bonus abilities.
- `ArcaneBoost`, `TimeWarp`, `EssenceBonus`: Various collectible items of power-ups and bonuses that provide advantages in gameplay.
- `ElementalCrest`: A unique collectible item, which represent the pinnacle achievement and it is the quest of the game to collect them all.

### game.creatures

This package houses classes that represent various mythical creatures in the game:

- `Creature`: Abstract base class for all creatures.
- `Dragon`, `Gaia`, `Hydra`, `Phoenix`, `Lion`: Specific creature classes with unique attributes and behaviors.

### game.dice

This package includes classes for different types of dice and their statuses:

- `Dice`: Abstract base class for all dice.
- `RedDice`, `GreenDice`, `BlueDice`, `MagentaDice`, `YellowDice`, `ArcanePrism`: Specific dice types used in the game, each with unique properties.


### game.engine

This package serves as the core for all game mechanics, containing both abstract and concrete classes that manage game flow, player interactions, and the game board:

- `GameController`: Abstract class that sets the foundational methods for game controllers.
- `CLIGameController`: Extends GameController, implementing the CLI (Command Line Interface) version of the game controller.
- `GameBoard`: Manages the layout and state of the game board, including dice and players.
- `Player`: Represents a player in the game, managing their status, score, and actions.
- `ScoreSheet`: Keeps track of a player's scores across different realms.
- `GameStatus`: Manages the current status of the game, including rounds and turns.
- `GameScore`: Details the scoring logic and current scores for players.
- `Move`: Represents a player's move, including dice and creature interactions.

### game.exceptions

This package defines custom exceptions to handle various error scenarios specifically related to game actions:

- `RewardException`
- `PlayerActionException`
- `InvalidMoveException`
- `InvalidDiceSelectionException`
- `DiceRollException`
- `CommandFormatException`
- `ExhaustedResourceException`

### game.gui

The `game.gui` package houses classes related to the graphical user interface (GUI) of the game. This includes components for rendering game graphics, handling user input, and managing the visual presentation of game elements.

## Classes

For each package, add the skeleton details for the class and duplicate as much as needed. As an example, the `GameController.java` skeleton is provided as guideline.

### `GameController` class

- **Package**: `game.engine`
- **Type**: Abstract Class
- **Description**: This abstract class represents the controller for the game. It defines the common blueprint for different controllers used in the game.

#### Methods:

1. `void startGame()`

    - **Description**: Initializes necessary components and starts the game loop.

2. `boolean switchPlayer()`

    - **Description**: Switches the role of the current active player to passive and vice versa, ensuring that the turn-taking mechanism functions correctly.
    - **Return Type**: `boolean`
        - `true` if the switch was successful,
        - `false` otherwise.

3. `Dice[] rollDice()`

    - **Description**: Rolls all available dice for the current turn, assigning each a random number from 1 to 6.
    - **Return Type**: Array of `Dice`
        - An array of the currently rolled dice.

4. `Dice[] getAvailableDice()`

    - **Description**: Gets the dice available for rolling or rerolling.
    - **Return Type**: Array of `Dice`
        - An array of dice available for the current turn.

5. `Dice[] getAllDice()`

    - **Description**: Gets all six dice, providing their current state and value within the game regardless of their location or status.
    - **Return Type**: Array of `Dice`
        - An array of all six dice, with each die's state and value.

6. `Dice[] getForgottenRealmDice()`

    - **Description**: Gets the dice currently available in the Forgotten Realm.
    - **Return Type**: Array of `Dice`
        - An array of dice that are currently in the Forgotten Realm.

7. `Move[] getAllPossibleMoves()`

    - **Description**: Gets all possible moves for all currently rolled dice for the active player.
    - **Return Type**: Array of `Move`
        - An array of all possible moves for all rolled dice.

8. `Move[] getPossibleMoves(Dice dice)`

    - **Description**: Gets all possible moves for a given dice for the active player.
    - **Parameters**:
        - `dice`: The dice to determine possible moves for.
    - **Return Type**: Array of `Move`
        - An array of possible moves for the given dice.

9. `GameBoard getGameBoard()`

    - **Description**: Gets the current game board, including all players and all score sheets.
    - **Return Type**: `GameBoard`
        - The current game board object.

10. `Player getPlayer()`

    - **Description**: Gets the current active player's information.
    - **Return Type**: `Player`
        - The active player object.

11. `ScoreSheet getScoreSheet()`

    - **Description**: Gets the score sheet for the current active player.
    - **Return Type**: `ScoreSheet`
        - The score sheet object for the current active player.

12. `GameStatus getGameStatus()`

    - **Description**: Gets the current game status, including round and turn information for the current active player.
    - **Return Type**: `GameStatus`
        - The current game status object.

13. `GameScore getGameScore()`

    - **Description**: Gets the current score of the game, including scores in each realm, number of elemental crests, and the total score for the current active player.
    - **Return Type**: `GameScore`
        - The current game score object.

14. `TimeWarp getTimeWarpPowers()`

    - **Description**: Gets the number of TimeWarp powers the active player has and their status.
    - **Return Type**: `TimeWarp`
        - The TimeWarp object for the current active player.

15. `ArcaneBoost getArcaneBoostPowers()`

    - **Description**: Gets the number of ArcaneBoost powers the active player has and their status.
    - **Return Type**: `ArcaneBoost`
        - The ArcaneBoost object for the current active player.

16. `boolean selectDice(Dice dice)`

    - **Description**: Selects a dice and adds it to the current turn of the active player, moving all other dice with less value to the Forgotten Realm.
    - **Parameters**:
        - `dice`: The dice to be selected.
    - **Return Type**: `boolean`
        - `true` if the selection was successful,
        - `false` otherwise.

17. `boolean makeMove(Dice dice, Creature creature)`
    - **Description**: Executes a move using the selected dice on a specified creature.
    - **Parameters**:
        - `dice`: The dice selected by the active player for the move.
        - `creature`: The target creature that the move is against.
    - **Return Type**: `boolean`
        - `true` if the move is successfully completed,
        - `false` otherwise.



### `Player` class

- **Package**: `game.engine`
- **Type**: Class
- **Description**: This class represents the 2 players' names, active or passive, and score.

#### Methods:

1. `PlayerStatus getPlayerStatus()`
    - **Description**: This method checks whether the player is currently active or passive.
    - **Return Type**: `PlayerStatus`
        - `ACTIVE` if the player is active
        - `PASSIVE` if the player is passive

2. `void setPlayerStatus(PlayerStatus playerStatus)`
    - **Description**: This method sets the status of the players wether active or passive.
    - **Return Type**: `void` does not return


3. `GameScore getGameScore()`
    - **Description**: This method gets the total game score of the player.
    - **Return Type**: `GameScore`
        

4. `void setGameScore()`
    - **Description**: This method sets the game score at the beginning of the game.
    - **Return Type**: `void` does not return
        

5. `String getPlayerName()`
    - **Description**: This method returns the name of the player.
    - **Return Type**: `String`
        - `String` Player name.
      

6. `void setPlayer_name(String playerName)`
    - **Description**: This method sets the name of the player when the game start.
    - **Return Type**: `void` does not return.
        

7. `int getTimeWarpCount()`
    - **Description**:  This method returns the number of TimeWarps the player has.
    - **Return Type**: `int`
        - `int` Number of TimeWarps.
            

8. `void subtractTimeWarpCount()`
    - **Description**: This method subtracts the number of TimeWarps the player has by 1.
    - **Return Type**: `void` does not return.


9. `void addTimeWarpCount()`
    - **Description**: This method adds the number of TimeWarps the player has by 1.
    - **Return Type**: `void` does not return.    


10. `int getArcaneBoostCount()`
    - **Description**: This method returns the number of Arcane Boost the player has.
    - **Return Type**: `int`
        - `int` Number of Arcane boosts.
       

11. `void addArcaneBoostCount()`
    - **Description**: This method adds the number of TimeWarps the player has by 1.
    - **Return Type**: `void` does not return.


12. `void subtractArcaneBoostCount()`
    - **Description**: This method subtracts the number of TimeWarps the player has by 1.
    - **Return Type**: `void` does not return.
        

13. `ElementalCrest[] getElementalCrest()`
    - **Description**: This method returns an array of the Elemental crests the player has.
    - **Return Type**: `ElementalCrest[]`
        - `ElementalCrest[]` array of Elemental crests.
    

14. `void addElementalCrest(Realm r)`
    - **Description**: This method takes the realm and adds the elemental Crest of this realm.
    - **Return Type**: `void` does not return.


15. `Dice getSelectedDice()`
    - **Description**: This method gets the dice the player choose.
    - **Return Type**: `Dice`
        - `Dice` The dice selected.

16. `void setSelectedDice(Dice selectedDice)`
    - **Description**: This method takes the dice the player selected and assign this dice.
    - **Return Type**: `void` does not return.
        

17. `ScoreSheet getScoreSheet()`
    - **Description**: This method returns the scoresheet of all realms for the player.
    - **Return Type**: `ScoreSheet`
        -`ScoreSheet` The scoresheets of the 5 realms.





### `Dice` class

- **Package**: `game.dice`
- **Type**: Class
- **Description**: This class represents the dice that will be rolled each turn.

#### Methods:

1. `int roll()`
    - **Description**: Rolls a die and returns a random int between 1 & 6, inclusive.
    - **Return Type**: `int`
        - Possible outcomes: 1 -> 6



### `Color` class

- **Package**: `game.engine`
- **Type**: enum
- **Description**: This class represents instance variable Color of various classes.





### `Move` class

- **Package**: `game.dice`
- **Type**: Class
- **Description**: This class represents the possible actions a player can take.



### `Gameboard` class

- **Package**: `game.engine`
- **Type**: Class
- **Description**: This class represents the current game board, including players, score-sheets, and creatures.

#### Methods:

1. `int remainingRounds()`
    - **Description**: Returns the number of rounds left to be played.
    - **Return Type**: `int`
        - number of rounds left

2.  `Player currentPlayer()`
    - **Description**: 


### `Scoresheet` class

- **Package**: `game.engine`
- **Type**: Class
- **Description**: This class represents the Scoresheet where all the scores and collectibles will be stored.

#### Methods:

1. `String toString()`
    - **Description**: returns all the scores and collectibles.
    - **Parameters**:
        - ``:
    - **Return Type**: `String`
        - `prints the data stored in the scoresheet.`






### `Gamestatus` class

- **Package**: `game.engine`
- **Type**: Class
- **Description**: This class represents the current game status including active player, round, and turn.

#### Methods:

1. `String toString()`
    - **Description**:returns game status, active player, current round and turn.
    - **Parameters**:
        - ``:
    - **Return Type**: `String`
        - `prints the games status.`







### `Gamescore` class

- **Package**: `game.engine`
- **Type**: Class
- **Description**: This class represents current score of the game, including scores in each realm, number of elemental crests, and the total score for the current active player.

#### Methods:

1. `String toString()`
    - **Description**: returns all the available scores

    - **Return Type**: `String`
        - ``



### `Creature` class

- **Package**: `game.creatures`
- **Type**: Abstract Class
- **Description**: This class represents all creatures of all realms.



### `Pyroclast_Dragon` class

- **Package**: `game.creatures`
- **Type**: Class
- **Description**: This class represents the Dragons of the Red Realm and their structure, hitpoints, and currently alive or dead.

#### Methods:


1. `Collectibles checkBonus(int[][] dragonParts) method`
    - **Description**: Checks whether the player gets the bonus or not after eliminating a part in all dragons.
    - **Parameters**: 2d array
        - An array that contains all hitpoints of the four dragons with their corresponding dice numbers.
    - **Return Type**: `Collectibles`
        - ``
2. `int getPoints(int[][] dragonParts) method`
    - **Description**: To check whether the player will get the points or not after killing a dragon.
    - **Parameters**: 2d array
        - ``:
    - **Return Type**: `int`
        - ``
3. `void changeScoretoZero(Player move) method`
    - **Description**: To change the hit area the player chose to zero to indicate that this place is already hit.
    - **Parameters**: 2d array
        - ``:
    - **Return Type**: `void`
        - ``


### `Gaia_Guardian` class

- **Package**: `game.creatures`
- **Type**: Class
- **Description**: This class represents the Guardians of the Green Realm and their structure, hit-points, and currently alive or dead.

#### Methods:

1. `int getPoints()`
    - **Description**: Gets the current score by looking for the number of Gaia defeated.
    - **Return Type**: `int`
        - Represents the score from the green realm.

2.  `Collectibles getVerticalBonus(int[][] guardians)`
    - **Description**: Checks whether the wizard finished all the guardians in one column.
    - **Parameters**:
        - `guardians`: Table that contains corresponding dice number for each Gaia Guardian.
    - **Return Type**: `Collectibles`
        - Object that represents the collectible that the player may earn after finishing a column.

3. `Collectibles getHorizontalBonus(int[][] guardians)`
    - **Description**: Checks whether the wizard finished all the guardians in one row.
    - **Parameters**:
        - `guardians`: Table that contains corresponding dice number for each Gaia Guardian.
    - **Return Type**: `Collectibles`
        - Object that represents the collectible that the player may earn after finishing a row.

4. `Collectibles checkBonusGaia()`
    - **Description**: Shows all collectibles that the wizard earned. Uses getVerticalBonus(int[][]) and getHorizontalBonus(int[][]).
    - **Return Type**: `Collectibles`
        - Returns the collectible that the player earned.

    
### `Hydra_Serpent` class

- **Package**: `game.creatures`
- **Type**: Class
- **Description**: This class represents the Serpents of the Blue Realm and their structure, hit-points, and currently alive or dead.


1. void Attack(int[] heads, int dice)
    - *Description*: This method compares the dice number with the head of serpent that is currently in the index if it is higher it hit the head if not the method breaks.
    - *Parameters*:
      -'1d array, int dice': It takes the array of the heads and the number of the die.

      
2. int getScore() method
    - *Description*: The method returns the points the player will get after killing a serpent head by looking for the number of heads killed.
    - *Return Type*: int
        - It returns the Score the player gets from the blue realm.



3. void SerpentStatus()
    - *Description*: Print the head that will be hit next by getting the index.



4. Collectibles checkBonus() method
    - *Description*: The method checks if the current head hit has bonus or not.
    - *Return Type*: Collectibles
        - It returns the Collectibles the player gets from the head attacked.




### `Majestic_Phoenix` class

- **Package**: `game.creatures`
- **Type**: Class
- **Description**: This class represents the Phoenixes of the Magenta Realm and their structure, hitpoints, and currently alive or dead.

#### Methods:

1. `int Attack (int[] array )`
    - **Description**:checks if the current dice is greater than previous and can be played and checks the "Rewards" method
    - **Parameters**:
        - `array of integers`:save hits on Phoenix
    - **Return Type**: ``
        - `int`

2.  `Collectibles Rewards(int index, Collectibles[] reward)`
    - **Description**:check array of reward using index to know which reward
    - **Parameters**:
        - `int index, Collectibles[] reward`:integer index and array of Collectibles
    - **Return Type**:
        - `Collectibles`
        - 
3. `int getPoints()`
      - **Description**:get score added from Attack method
      - **Return Type**: `int`




### `Solar_Lion` class

- **Package**: `game.creatures`
- **Type**: Class
- **Description**: This class represents the Lions of the Yellow Realm and their structure, hitpoints, and currently alive or dead.

#### Methods: 

1. `void editReward`

    - **Description**: Creates array rew type string and adds rewards from config to it.
    - **Return Type**: `void` does not return.
     
2. `void editMultipliers()`

    - **Description**: Creates array mult type string and adds multipliers from config to it and edits multiplier array type int.
    - **Return Type**: `void` does not return.
   
3. `boolean makeMove(Move move)`

    - **Description**: increments hitnum(counter) & adds dice value to array diceNum.
    - **Parameters**:
        - `move`: The move to be perfromed.
    - **Return Type**: `boolean`
        - `true` if the move was successful,
        - `false` otherwise.

4. `int getYellowRealmScore()`

    - **Description**: Uses multiplyScore & getScore methods to return total score of yellow realm.
    - **Return Type**: `int`
        - `int` total score of yellow realm.

5. `static int multiplyScore(int hitNum,int[] diceNum,int[] multipliers)`

    - **Description**: Gets the new score of the round after multiplying the score by the multiplier.
    - **Parameters**:
        - `hitNum`: number of hits on the lion.
        - `diceNum`: Array of all the dies used in the yellow realm.
        - `multipliers`: Array of the multipliers that should be multiplied by the hit score.
    - **Return Type**: `int`
        - `int` the new score of the round.

6. `static int getScore(int score, int totalScore)`

    - **Description**: calculates total score by adding the score to total score.
    - **Parameters**:
        - `score`: The score of this round.
        - `totalScore`: Total score of the previous rounds.
    - **Return Type**: `int`
        - `int` total score of all rounds.

7. `Reward getReward()`

    - **Description**: Checks rewards and returns the supposed reward & makes used reward an x.
    - **Return Type**: `Reward`
        - `Reward` The reward won after this hit.

8. ` Move[] getAllPossibleMoves()`

    - **Description**: gets an array type move with all the possible moves before rolling the dice.
    - **Return Type**: `Move[]`
        - `Move[]` all possible moves before rolling the dice.    

9. `Move[] getPossibleMovesForADie(Dice dice)`

    - **Description**: gets an array type move with all the possible moves after the dice is rolled.
    - **Parameters**:
        - `dice`: The dice after it is rolled.
    - **Return Type**: `Move[]`
        - `Move[]` all possible moves after rolling the dice.   

10. `String toString()`

    - **Description**: Returns the scoresheet.
    - **Return Type**: `String`
        - `String` scoresheet of yellow realm.




### `Forgotten_Realm` class

- **Package**: `game.engine`
- **Type**: Class
- **Description**: This class represents the Forgotten Realm and the dice contained in it.

#### Methods:

1. `void addDiceToForgottenRealm(Dice[] dice)`
    - **Description**: Compares selected die with the remaining and adds the lower valued dice to the forgotten realm.
    - **Parameters**: Dice[] dice
        -  Array of dice that was rolled at the beginning of the turn.
        -



### `Collectibles` class

- **Package**: `game.collectibles`
- **Type**: Abstract Class
- **Description**: This abstract class serves as a blueprint for all the bonuses and boosts.
- 
- #### Methods:
- 
1. `abstract int getCount()`
    - **Description**: returns number of item the player currently has.
    - **Return Type**: `int`
        - `number of items`

2.  `abstract void usePower()`
    - **Description**: activates the selected power.
    - **Parameters**:
        - ``:
    - **Return Type**: `void`
        - ``






### `Color_Bonus` class

- **Package**: `game.collectibles`
- **Type**: Class
- **Description**: This class represents the color bonus power and its status.






### `Essence_Bonus` class

- **Package**: `game.collectibles`
- **Type**: Class
- **Description**: This class represents




### `Arcane_Boost` class

- **Package**: `game.collectibles`
- **Type**: Class
- **Description**: This class represents the Arcane Boost power and how many the player has.




### `Timewarp` class

- **Package**: `game.collectibles`
- **Type**: Class
- **Description**: This class represents the Time Warp power and its status and how many the player has.




### `Elemental_Crests` class

- **Package**: `game.collectibles`
- **Type**: Class
- **Description**: This class represents the Elemental Crest collectibles and their status.

#### Methods:

1. `int getLowestScore(Scoresheet scoresheet)`
    - **Description**: Scans the scoresheet for the lowest score across the five realms.
    - **Parameters**:Scoresheet
        - ``: the scoresheet that stores all the scores.
    - **Return Type**: `int`
        - `the value of the score of the lowest realm.`

2.  `int multiplyScores(int score)`
    - **Description**: returns the value of the lowest score after applying the elemental crests multiplier.
    - **Parameters**: int score
        - `the lowest score across the realms`:
    - **Return Type**: `int`
        - `the value of the score after multiplying`




