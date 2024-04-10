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
│   │   │       │
│   │   │       ├── creatures/
│   │   │       │
│   │   │       ├── dice/
│   │   │       │
│   │   │       ├── engine/
│   │   │       │   ├── GameController.java
│   │   │       │
│   │   │       ├── exceptions/
│   │   │       │
│   │   │       ├── gui/
│   │   │       │
│   │   │       └── Main.java
│   │   │
│   │   └── resources/
│   │       ├── images/
│   │       └── config/
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
└── README.md
```

## Packages

### game.collectibles

The `game.collectibles` package contains classes for the various collectible items within the game; such as power-ups, elemental crest, color bonus, or the essence bonus.

### game.creatures

In the `game.creatures` package, you'll find classes representing creatures in their corresponding realms; including all necessary features about how to attack them or their current status to update the score sheet accordingly.

### game.dice

The `game.dice` package encompasses classes related to dice functionality within the game. It includes implementations for rolling dice, managing dice states, and handling dice-related actions and interactions.

### game.engine

This package contains the core engine components of the game, including the abstract classes and interfaces that define the game's structure and functionality. It serves as the foundation for implementing various game controllers and managing game logic. Additional classes related to game mechanics and control can be added to this package as needed.

### game.exceptions

The `game.exceptions` package provides classes for defining custom exceptions specific to the game. These exceptions help handle error conditions and unexpected situations, providing meaningful feedback to the player or developer.

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

1. `boolean isActive()`
    - **Description**: Checks whether the player is currently active or passive.
    - **Return Type**: `boolean`
        - `true` if the player is active
        - `false` if the player is passive

    

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
 

1. int multiplyScore(int hitNum, int diceNum, int[] multipliers)
    - *Description*: finds the multiplier by checking the number of hits and an array of multipliers then multiplies the dice number by the chosen multiplier. Returns the new score.
    - *Parameters*: multipliers
        - int hitNum: number of times the lion was hit.
        - int DiceNum: the number on the dice after it was rolled.
        - int[] multipliers: array that shows how much the score should be multiplied by.
    - *Return Type*: int

2. int TotalScore(int score, int totalScore)
    - *Description*: takes the score from method multiplyScore and adds it total score. returns the new total score.
    - *Parameters*:
        - int score: score from method multiplyScore.
        - int totalScore: total score of points
    - *Return Type*: int
        - `

3. object getReward(int Hitnum, object[] rewards)
    - *Description*: compares hit number with an array of rewards and returns the chosen reward
    - *Parameters*:int hitNum, object[] rewards
        - int Hitnum: number of times the lion was hit.
        - object[] rewards: array that shows the reward of the given turn.
    - *Return Type*:object







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




